package com.example.EazyBankApp.security;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.EazyBankApp.filter.CsrfTokenFilter;
import com.example.EazyBankApp.filter.JwtTokenGenerationFilter;
import com.example.EazyBankApp.filter.JwtTokenValidationFilter;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class ProjectSecurityConfig {

    // @Bean
    // public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    //     UserDetails admin = User.builder().username("sam").password("1111").roles("USER","ADMIN").build();
    //     UserDetails user = User.builder().username("kim").password("1111").roles("USER").build();
    //     return new InMemoryUserDetailsManager(admin,user);
    // }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        //1. cors
        http.cors(cors -> cors.configurationSource(new CorsConfigurationSource() {

            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest arg0) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowCredentials(true);
                config.setAllowedHeaders(Collections.singletonList("*"));
                config.setAllowedMethods(Collections.singletonList("*"));
                config.setAllowedOrigins(Collections.singletonList("*"));
                config.setExposedHeaders(Arrays.asList("Authorization"));
                config.setMaxAge(3600L);

                return config;
            }

        }));

        // 2. JsessionID
        // http.securityContext(context -> context.requireExplicitSave(false))
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // 3. csrf
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        // http.csrf(csrf->csrf.disable());
        http.csrf(csrf -> csrf.csrfTokenRequestHandler(requestHandler).ignoringRequestMatchers("/notices", "/contact")
            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()));
        http.addFilterAfter(new CsrfTokenFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JwtTokenGenerationFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidationFilter(), BasicAuthenticationFilter.class);

        // 4. authorization
        http.authorizeHttpRequests(request -> {
            request.requestMatchers("h2-consol/**").permitAll();
            request.requestMatchers(HttpMethod.DELETE, "/account","/customer").hasRole("ADMIN"); // By using this line before " request.requestMatchers("/account").hasAnyRole("USER","ADMIN");" we are restricting authority to delete account detail to "ADMIN" only.
            request.requestMatchers(HttpMethod.GET, "/account/**").hasAuthority("VIEW_ACCOUNT");
            request.requestMatchers("/account", "/account/**", "/customer", "/customer/**").hasAnyRole("USER", "ADMIN"); // Now  post, get and put request can be send by users, no need to explicitly specify method in requestMatchers. But better approach is to use seperate request matchers for each requests.
            request.anyRequest().permitAll();
        });

        // 5.default settings
        http.headers(headers -> headers.frameOptions().disable());
        http.formLogin(Customizer.withDefaults());
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return NoOpPasswordEncoder.getInstance();
    }
}
