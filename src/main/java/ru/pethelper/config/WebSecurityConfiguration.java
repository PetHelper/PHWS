package ru.pethelper.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST,"/user/*").permitAll()
                .antMatchers(HttpMethod.GET,"/user/activate/**").permitAll()
                .antMatchers(HttpMethod.GET,"/pet/findAll").permitAll()
                .antMatchers(HttpMethod.POST,"/pet/*").permitAll()
                .antMatchers(HttpMethod.GET,"/vetclinic/*").permitAll()
                .anyRequest().authenticated();
    }
}
