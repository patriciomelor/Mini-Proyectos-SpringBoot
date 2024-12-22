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
                .requestMatchers("/api/practicas").permitAll()  // Permitir GET y POST
                .requestMatchers("/api/practicas/**").permitAll()  // Permitir PUT y DELETE
                .anyRequest().authenticated()  // Proteger otras rutas
            )
            .csrf(csrf -> csrf.disable())  // Desactivar CSRF
            .httpBasic(Customizer.withDefaults());  // Usar Customizer en lugar de withDefaults()

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        var user = User.withUsername("admin")
            .password("{noop}MiClaveSegura123")  // Sin encriptar para pruebas
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }
}
