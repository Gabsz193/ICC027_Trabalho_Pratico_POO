package br.edu.ufam.icomp.ru_digital.features.funcionario;

import br.edu.ufam.icomp.ru_digital.entities.funcionario.model.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Optional<Funcionario> findByCpf(String cpf) {
        return funcionarioRepository.findByCpf(cpf);
    }

    public Optional<Funcionario> findByEmail(String email) {
        return funcionarioRepository.findByEmail(email);
    }

    public Optional<Funcionario> findByMatricula(String matricula) {
        return funcionarioRepository.findByMatricula(matricula);
    }

    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public Optional<Funcionario> update(Long id, Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id).map(funcionario -> {
            funcionario.setNome(funcionarioAtualizado.getNome());
            funcionario.setCpf(funcionarioAtualizado.getCpf());
            funcionario.setEmail(funcionarioAtualizado.getEmail());
            funcionario.setMatricula(funcionarioAtualizado.getMatricula());
            funcionario.setSaldoCentavos(funcionarioAtualizado.getSaldoCentavos());
            if (funcionarioAtualizado.getCargos() != null) {
                funcionario.setCargos(funcionarioAtualizado.getCargos());
            }
            return funcionarioRepository.save(funcionario);
        });
    }

    public boolean delete(Long id) {
        if (funcionarioRepository.existsById(id)) {
            funcionarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
