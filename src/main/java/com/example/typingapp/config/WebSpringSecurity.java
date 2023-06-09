package com.example.typingapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSpringSecurity {

    private UserDetailsService userDetailsService;

    public WebSpringSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http.csrf().disable()
                    .authorizeHttpRequests((authorize) ->
                            authorize.requestMatchers(new AntPathRequestMatcher("/register/**")).permitAll()
                                    .requestMatchers(new AntPathRequestMatcher("/")).permitAll()
                                    .requestMatchers(new AntPathRequestMatcher("/login/**")).permitAll()
                                    .requestMatchers(new AntPathRequestMatcher("/menu/**")).hasAnyRole("ADMIN", "USER")
                                    .requestMatchers(new AntPathRequestMatcher("/lesson/**")).hasAnyRole("ADMIN", "USER")
                                    .requestMatchers(new AntPathRequestMatcher("/history/**")).hasAnyRole("ADMIN", "USER")
                    )
                    .formLogin(form -> form
                            .loginPage("/login")
                            .defaultSuccessUrl("/menu")
                            .loginProcessingUrl("/login")
                            .permitAll()
                    ).logout(logout -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                            .permitAll()
                    );
            return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
