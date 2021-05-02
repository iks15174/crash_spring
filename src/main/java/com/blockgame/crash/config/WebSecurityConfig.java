//package com.blockgame.crash.config;
//
//import com.blockgame.crash.service.MemberService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
//
//    @Autowired 
//    private MemberService memberService;
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception{
//        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//
//        http.authorizeRequests()
//            .antMatchers("/game/**").authenticated()
//            .antMatchers("/**").permitAll();
//
//        http.formLogin()
//            .loginPage("/")
//            .usernameParameter("id")
//            .defaultSuccessUrl("/game")
//            .permitAll();
//
//        http.logout()
//            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//            .logoutSuccessUrl("/")
//            .invalidateHttpSession(true);
//    }
//
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
//    }
//    
//}
//