package br.edu.ufam.icomp.ru_digital.features.usuario;

import br.edu.ufam.icomp.ru_digital.entities.usuario.model.Usuario;
import br.edu.ufam.icomp.ru_digital.shared.exceptions.RecursoNaoEncontradoException;
import br.edu.ufam.icomp.ru_digital.shared.exceptions.SaldoInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> findByNome(String nome) {
        return usuarioRepository.findByNome(nome);
    }

    public Usuario registrarUsuario(Usuario novoUsuario) {

        // 1. Pega a senha em texto puro que veio da requisição
        String senhaEmTextoPuro = novoUsuario.getPassword();

        // 2. CRIPTOGRAFA a senha
        String senhaHasheada = passwordEncoder.encode(senhaEmTextoPuro);

        // 3. Define o HASH na entidade antes de salvar
        novoUsuario.setPassword(senhaHasheada);

        // As definições de saldo (0L) e permissão ("ROLE_ALUNO") já estão no Model, o que é ótimo!

        return usuarioRepository.save(novoUsuario);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> update(Long id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setCpf(usuarioAtualizado.getCpf());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setMatricula(usuarioAtualizado.getMatricula());
            usuario.setSaldoCentavos(usuarioAtualizado.getSaldoCentavos());
            return usuarioRepository.save(usuario);
        });
    }

    public boolean delete(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public Usuario adicionarSaldo(Long usuarioId, Long valorCentavos) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", usuarioId));

        Long novoSaldo = usuario.getSaldoCentavos() + valorCentavos;
        usuario.setSaldoCentavos(novoSaldo);

        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario debitarSaldo(Long usuarioId, Long valorCentavos) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", usuarioId));

        Long saldoAtual = usuario.getSaldoCentavos();

        if (saldoAtual < valorCentavos) {
            throw new SaldoInsuficienteException(saldoAtual, valorCentavos);
        }

        Long novoSaldo = saldoAtual - valorCentavos;
        usuario.setSaldoCentavos(novoSaldo);

        return usuarioRepository.save(usuario);
    }

    public Long consultarSaldo(Long usuarioId) {
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Usuario", usuarioId));

        return usuario.getSaldoCentavos();
    }

    // Métodos legados mantidos para compatibilidade
    public Optional<Usuario> getUsuario(String nome) {
        return findByNome(nome);
    }

    public Optional<Usuario> saveUsuario(Usuario usuario) {
        return Optional.of(save(usuario));
    }
}
