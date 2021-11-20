//package com.tochko.advertising_platform.config;
//
//import com.tochko.advertising_platform.controller.LoggingAccessDeniedHandler;
//import com.tochko.advertising_platform.service.UserDetailsServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private static final String ADMIN = "ROLE_ADMIN";
//    private static final String USER = "ROLE_USER";
//    private final UserDetailsServiceImpl userDetailsService;
//
//   // private final LoggingAccessDeniedHandler accessDeniedHandler;
//
//    @Autowired
//    public WebSecurityConfig(UserDetailsServiceImpl service) {
//        this.userDetailsService=service;
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//    }
//
////
////    @Bean
////    public PasswordEncoder getPasswordEncoder() {
////        return NoOpPasswordEncoder.getInstance();
////    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/", "/index", "/about", "/registration").permitAll()
//                .antMatchers("/admin/**").hasAnyRole(ADMIN)
//                .antMatchers("/user/**").hasAnyRole(USER)
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                    .loginPage("/login")
//                    .permitAll()
//                .and()
//                .logout()
//                    .permitAll();
////                .and()
////                .exceptionHandling()
////                .accessDeniedHandler(accessDeniedHandler);
//
//        // Config Remember Me.
////        http.authorizeRequests().and() //
////                .rememberMe().tokenRepository(this.persistentTokenRepository()) //
////                .tokenValiditySeconds(1 * 24 * 60 * 60); // 24h
//
//    }
//
////    @Autowired
////    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        auth.inMemoryAuthentication()
////                .withUser("user").password("{noop}pass").roles("USER")
////                .and()
////                .withUser("admin").password("{noop}pass").roles("ADMIN");
////    }
//}
