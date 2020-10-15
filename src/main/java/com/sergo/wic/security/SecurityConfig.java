//package com.sergo.wic.security;
//
//import com.sergo.wic.repository.ModeratorRepository;
//import com.sergo.wic.repository.RoleRepository;
//import com.sergo.wic.service.ModeratorService;
//import com.sergo.wic.service.impl.ModeratorServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.http.SessionCreationPolicy;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private ModeratorService moderatorService;
//
//    public SecurityConfig(@Autowired ModeratorService moderatorService){
//        this.moderatorService = moderatorService;
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) {
//        auth.authenticationProvider(authenticationProvider());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
////        http
////                .csrf().disable()
////                .authorizeRequests()
////               // .anyRequest()
////              //  .permitAll()   // , "/admin/**"
////                .antMatchers("/admin/","/admin" )
//////                .hasRole("ADMIN")
//////                .antMatchers("/")
////                .authenticated()
////               // .antMatchers("/login")
////              //  .rememberMe()
////              //  .permitAll()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .loginProcessingUrl("/authenticateTheUser")
////             //   .defaultSuccessUrl("/admin")
////                .successForwardUrl("/admin")
////                .and()
////                .logout()
////              //  .logoutSuccessUrl("/admin/login")
////                .permitAll()
////                .and()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
////                .maximumSessions(1)
////                .expiredUrl("/login")
////                .maxSessionsPreventsLogin(true)
////        ;
//
//    }
//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
//        auth.setUserDetailsService(moderatorService);
//    //    auth.setPasswordEncoder(passwordEncoder());
//        return auth;
//    }
//
//    @Bean
//    public ModeratorService moderatorService(ModeratorRepository moderatorRepository, RoleRepository roleRepository) {
//        return new ModeratorServiceImpl(moderatorRepository, roleRepository);
//    }
//
//}
