package br.edu.ufam.icomp.ru_digital.features.usuario;

import br.edu.ufam.icomp.ru_digital.entities.usuario.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Optional<Usuario> getUsuario(String nome) {
        return this.usuarioRepository.findByNome(nome);
    }

    public Optional<Usuario> saveUsuario(Usuario usuario) {
        return Optional.of(this.usuarioRepository.save(usuario));
    }
}
