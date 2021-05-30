package com.blockgame.crash.config.auth;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception{
        web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{

        //http.csrf().disable();

        http.authorizeRequests()
            .antMatchers("/admin/**").authenticated()
            .anyRequest().permitAll();

        http.formLogin()
            .loginPage("/account/login")
            .loginProcessingUrl("/login")
            .usernameParameter("id")
            .defaultSuccessUrl("/")
            .failureHandler(failureHandler())
            .permitAll();

        http.logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
            .invalidateHttpSession(true);
    }

    @Bean
    public AuthenticationFailureHandler failureHandler(){
        return new CustomAuthFailureHandler();
    }
    
}
