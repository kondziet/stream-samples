package pl.kondziet.streamsamples.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable();
        httpSecurity.formLogin().disable();

        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers("/api/authentication/**")
                .permitAll()
                .anyRequest()
                .authenticated()
        );

        return httpSecurity.build();
    }
}
