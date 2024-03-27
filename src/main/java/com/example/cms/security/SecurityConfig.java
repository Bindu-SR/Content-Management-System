package com.example.cms.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private CustomerUserDeatilsService uds;

	public SecurityConfig(CustomerUserDeatilsService uds) {
		super();
		this.uds = uds;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http.csrf(csrf-> csrf.disable())
				   .authorizeHttpRequests(auth->auth.requestMatchers("/users/register")
						   .permitAll() //public
						   .anyRequest() //any request apart from url should be authenticate
						   .authenticated())
						   .formLogin(Customizer.withDefaults()) //2-tier -> specifies default configuration
						   .build(); //protection
		
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}
	
	AuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider(); //will
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(uds);
		return provider;
	}
	
}
