// Em br.edu.ufam.icomp.ru_digital.service.CustomUserDetailsService.java

package br.edu.ufam.icomp.ru_digital.service;

import br.edu.ufam.icomp.ru_digital.features.usuario.UsuarioRepository; // Importe seu repositório
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
        // Busca o usuário pela matrícula (que definimos como "username")
        return usuarioRepository.findByCpf(cpf)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com a matrícula: " + cpf));
    }
}