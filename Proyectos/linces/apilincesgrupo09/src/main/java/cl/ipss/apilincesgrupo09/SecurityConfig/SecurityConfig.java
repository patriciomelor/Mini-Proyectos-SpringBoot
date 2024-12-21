package cl.ipss.apilincesgrupo09.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/practicas").permitAll()  // Permitir sin autenticación
                .anyRequest().authenticated()  // Proteger el resto
            )
            .csrf(csrf -> csrf.disable())  // Desactivar CSRF para pruebas POST
            .httpBasic(withDefaults());  // Autenticación básica

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("admin")
            .password("{noop}MiClaveSegura123")  // Sin encriptar (solo para pruebas)
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
