package com.skilldistillery.supportlocal.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
    private DataSource dataSource;

    // this bean is created in the application starter class if you're looking for it
    @Autowired
    private PasswordEncoder encoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS, "/api/**").permitAll() // For CORS, the preflight request
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() 
        .antMatchers(HttpMethod.GET, "/api/businesses").permitAll() // will hit the OPTIONS on the route
        .antMatchers(HttpMethod.GET, "/api/businesses/**").permitAll() 
        .antMatchers(HttpMethod.GET, "/api/comments").permitAll()
        .antMatchers(HttpMethod.GET, "/api/comments/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/business/{businessId}/reviews").permitAll()
        .antMatchers(HttpMethod.GET, "/api/preferences").permitAll()
        .antMatchers(HttpMethod.GET, "/api/preferences/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/addresses").permitAll()
        .antMatchers(HttpMethod.GET, "/api/addresses/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/articles").permitAll()
        .antMatchers(HttpMethod.GET, "/api/articles/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/profile/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/profile").permitAll()
        .antMatchers(HttpMethod.GET, "/api/yelp/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/users/{uid}/reviews").permitAll()
        .antMatchers(HttpMethod.GET, "/api/articles/business/**").permitAll()
        .antMatchers(HttpMethod.GET, "/api/businesses/info/**").permitAll()

        .antMatchers("/api/**").authenticated() // Requests for our REST API must be authorized.
        .anyRequest().permitAll()               // All other requests are allowed without authorization.
        .and()
        .httpBasic();                           // Use HTTP Basic Authentication

        http
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        String userQuery = "SELECT email, password, active FROM User WHERE email=?";
        String authQuery = "SELECT email, role FROM User WHERE email=?";
        auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery(userQuery)
        .authoritiesByUsernameQuery(authQuery)
        .passwordEncoder(encoder);
        
    }

}
