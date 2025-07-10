package com.backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;


//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//    @Autowired
//    DataSource dataSource;                    // Based on the configuration provided spring will know to give you the correct dataSource
//
//    @Autowired
//    private AuthEntryPointJwt unauthorizedHandler; // handles 401 responses
//
//    @Bean
//    public AuthTokenFilter authenticationJwtTokenFilter() {
//        return new AuthTokenFilter();           // your JWT‐parsing Filter
//    }
//
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/signin").permitAll()           // your login endpoint
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .exceptionHandling(e -> e.authenticationEntryPoint(unauthorizedHandler))
//                .headers(h -> h.frameOptions().sameOrigin())         // for H2 console
//                .csrf(csrf -> csrf.disable())
//                .addFilterBefore(authenticationJwtTokenFilter(),
//                        UsernamePasswordAuthenticationFilter.class);
//        return http.build();
//    }
//
//    // by default this would wire up a JdbcUserDetailsManager
//    // pointing at Spring’s “users” and “authorities” tables:
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
//
//    // seeds two users into the default tables — you can remove this
//    @Bean
//    public CommandLineRunner initData(UserDetailsService uds) { … }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration auth)
//            throws Exception {
//        return auth.getAuthenticationManager();
//    }
//}


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    DataSource dataSource; //The Supabase pooler DataSource

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }


    @Bean
    public UserDetailsService userDetailsService(){

        //We are constructing a user object right here
        //{noop} is a prefix that should be saved as plaintext don't worry abt rn
        UserDetails user1 = User.withUsername("martin1")
                .password("{noop}hello")
                .roles("USER")
                .build();


//        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
//        userDetailsManager.createUser(user1);
        return new JdbcUserDetailsManager(dataSource);
    }

}
