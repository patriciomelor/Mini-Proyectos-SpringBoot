package cl.ipss.apilincesgrupo09.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/practicas").permitAll() // Permitir acceso a esta ruta
                .anyRequest().authenticated() // Requiere autenticación para otras rutas
            )
            .httpBasic(); // Reemplazo moderno para la autenticación básica HTTP

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("admin")
            .password("{noop}MiClaveSegura123") // Usa {noop} para indicar que la contraseña no está codificada
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
