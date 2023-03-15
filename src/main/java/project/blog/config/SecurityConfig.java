package project.blog.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;
import project.blog.jwt.JwtAuthenticationFilter;
import project.blog.jwt.JwtAuthorizationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsFilter corsFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(corsFilter)
//                .addFilter(new JwtAuthenticationFilter())
//                .addFilter(new JwtAuthorizationFilter()
                .formLogin().disable()
                .httpBasic().disable()
                .authorizeRequests()
                .antMatchers("/blog/**")
                .access("hasRole('PUBLIC_USER') or hasRole('PRIVATE_USER') or hasRole('ADMIN')")
                .antMatchers("/blog/admin/**")
                .access("hasRole('ADMIN')")
                .anyRequest().permitAll();

        return http.build();
    }
}
