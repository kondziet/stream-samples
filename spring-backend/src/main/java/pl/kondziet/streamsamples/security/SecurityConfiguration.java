package pl.kondziet.streamsamples.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import pl.kondziet.streamsamples.jwt.JwtVerifyFilter;

@AllArgsConstructor
@Configuration
public class SecurityConfiguration {

    private JwtVerifyFilter jwtVerifyFilter;
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors(Customizer.withDefaults());

        httpSecurity.csrf().disable();
        httpSecurity.formLogin().disable();

        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers("/api/posts/home")
                .permitAll()
                .requestMatchers("/api/comments/**")
                .permitAll()
                .requestMatchers("api/authentication/**")
                .permitAll()
                .anyRequest()
                .authenticated()
        );

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        httpSecurity.authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtVerifyFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
