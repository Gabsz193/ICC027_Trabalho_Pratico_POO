package br.edu.ufam.icomp.ru_digital.config;

import br.edu.ufam.icomp.ru_digital.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    // 1. Bean para criptografar senhas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 3. Define as regras de acesso (URLs públicas vs. privadas)
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authz -> authz
                        // Permite acesso público a CSS, JS, etc.
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()

                        // Permite acesso público às páginas de login e registro
                        .requestMatchers("/login", "/register").permitAll()

                        // Permite o POST para criar usuário (API de registro)
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()

                        // Exige autenticação para todo o resto
                        .anyRequest().authenticated()
                )
                // 4. Configura o Form Login
                .formLogin(form -> form
                        .loginPage("/login") // Diz qual é a sua página de login
                        .loginProcessingUrl("/login") // URL que o <form> envia (deve ser a mesma)
                        .defaultSuccessUrl("/home", true) // Para onde vai após logar
                        .failureUrl("/login?error=true") // Para onde vai se errar
                        .permitAll()
                )
                // 5. Configura o Logout
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL para deslogar
                        .logoutSuccessUrl("/login?logout=true") // Para onde vai após deslogar
                        .permitAll()
                );

        return http.build();
    }
}