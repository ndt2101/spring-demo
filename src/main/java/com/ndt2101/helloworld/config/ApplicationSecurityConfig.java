package com.ndt2101.helloworld.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.concurrent.TimeUnit;

import static com.ndt2101.helloworld.config.UserRole.*;


/**
 * basic auth
 * form auth
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordConfig passwordConfig;
    private final UserDetailsService userDetailsService;

    @Autowired
    public ApplicationSecurityConfig(PasswordConfig passwordConfig, UserDetailsService userDetailsService) {
        this.passwordConfig = passwordConfig;
        this.userDetailsService = userDetailsService;
    }


    // basic auth
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
////                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
////                .and()
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/users/").hasRole(ADMIN.name()) // role based authentication
////                .antMatchers(HttpMethod.POST, "/api/students").hasAuthority(WRITE.getPermission())
////                .antMatchers(HttpMethod.PUT, "/api/students/**").hasAuthority(WRITE.getPermission())
////                .antMatchers(HttpMethod.DELETE, "/api/students/**").hasAuthority(DELETE.getPermission())
////                .antMatchers(HttpMethod.GET, "/api/students/").hasAnyRole(ADMIN.name(), STUDENT_ADMIN.name())
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }
    // form auth
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/").hasRole(ADMIN.name()) // role based authentication
//                .antMatchers(HttpMethod.POST, "/api/students").hasAuthority(WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/api/students/**").hasAuthority(WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE, "/api/students/**").hasAuthority(DELETE.getPermission())
//                .antMatchers(HttpMethod.GET, "/api/students/").hasAnyRole(ADMIN.name(), STUDENT_ADMIN.name())
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .defaultSuccessUrl("/home")
                .and()
                .rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
                .key("aaa")
                .and()
                .logout()
//                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
                    .invalidateHttpSession(true)
                    .clearAuthentication(true)
                    .deleteCookies("remember-me", "JSECTIONID")
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login");
    }

    /*
    user in memory
     */
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        UserDetails user1 = User.builder()
//                .username("user")
//                .password(passwordConfig.passwordEncoder().encode("111"))
////                .roles(STUDENT.name()) //tự convert sang dạng ROLE_STUDENT -> không được đặt là ROLE_STUDENT
//                .authorities(STUDENT.getAuthorities()) // permission based authentication
//                .build();
//
//        UserDetails user2 = User.builder()
//                .username("user2")
//                .password(passwordConfig.passwordEncoder().encode("123456"))
////                .roles(ADMIN.name())
//                .authorities(ADMIN.getAuthorities())
//                .build();
//
//        UserDetails user3 = User.builder()
//                .username("user3")
//                .password(passwordConfig.passwordEncoder().encode("123456"))
////                .roles(STUDENT_ADMIN.name())
//                .authorities(STUDENT_ADMIN.getAuthorities())
//                .build();
//        return new InMemoryUserDetailsManager(user1, user2, user3);
//    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordConfig.passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }
}
