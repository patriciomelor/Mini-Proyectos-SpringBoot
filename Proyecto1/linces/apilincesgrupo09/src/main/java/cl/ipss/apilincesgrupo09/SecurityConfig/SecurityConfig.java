package cl.ipss.apilincesgrupo09.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.config.Customizer;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/**").permitAll()  // Permitir acceso a la API
                .anyRequest().authenticated()
            )
            .csrf(csrf -> csrf.disable())  // Desactiva CSRF para pruebas con Postman
            .httpBasic();  // Autenticación básica

        return http.build();
    }
    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("admin")
                .password("{noop}MiClaveSegura123")  // sin encriptar para pruebas
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
