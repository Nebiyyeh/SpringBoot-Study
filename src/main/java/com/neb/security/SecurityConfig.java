package com.neb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)  //to enable method level auth
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/","index.html","/css/*","/js/*","/images/*","/register").permitAll().
//		and().
//		authorizeRequests().antMatchers("/student/**").hasRole("INSTRUCTOR").and().  //same
//		authorizeRequests().antMatchers("/beans/**").hasAuthority("ROLE_Admin").    //same 
		anyRequest().authenticated().and().httpBasic();
		return http.build();
	}
	
//	@Bean
//	protected InMemoryUserDetailsManager configureAuthentication() {
//		UserDetails userEnes=User.builder().username("Eymen").password(passwordEncoder().encode("Eymen")).roles("Manager").build();
//		UserDetails userEmine=User.builder().username("Emine").password("$2a$10$BhCFxRU/ZI5Lfw77uMmD.O.tWxRCz3XZIbXOU0.cCQNItGWq3OrNy").roles("Instructor").build();
//		return new InMemoryUserDetailsManager(new UserDetails[] {userEnes, userEmine});
//		
//	}    This is for in memory  
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider=new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
		
	}
	
	
	
	

}
