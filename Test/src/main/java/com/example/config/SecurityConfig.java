package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
public class SecurityConfig {
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		// Do not apply security
		http.authorizeHttpRequests()
		.requestMatchers("/login").permitAll()
		.requestMatchers("/user/signup").permitAll()
		.anyRequest().authenticated();
		
		http.headers(headers -> headers.frameOptions().disable());
		http.authenticationProvider(authenticationProvider());
		
		//login process
		http
			.formLogin()
			.loginProcessingUrl("/login")
			.loginPage("/login")
			.failureUrl("/login?error")
			.usernameParameter("userEmail")
			.passwordParameter("password")
			.defaultSuccessUrl("/home", true);
		
		//logout process
		http
			.logout()
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutUrl("/logout")
			.logoutSuccessUrl("/login?logout");
		//disable CSRF measures (temporary)
		http.csrf().disable();
		return http.build();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		// Set of login unnecessary page
		return (web) -> web
				.ignoring()
				.requestMatchers("/webjars/**")
				.requestMatchers("/css/**")
				.requestMatchers("/js/**")
				.requestMatchers(new AntPathRequestMatcher("/h2-console/**")); 
			}
		
		@Bean
		public PasswordEncoder passwordEncoder() {
			return new BCryptPasswordEncoder();
			}

		@Bean
		public UserDetailsService userDetailsService() {
		UserDetails admin = User.withUsername("admin@gmail.com")
								.password(passwordEncoder().encode("12345"))
								.roles("ADMIN")
								.build();
		UserDetails user = User.withUsername("user@gmail.com")
								.password(passwordEncoder().encode("12345"))
								.roles("USER")
								.build();
		return new InMemoryUserDetailsManager(admin, user);
		}
		
		@Bean
		public DaoAuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
			authProvider.setUserDetailsService(userDetailsService());
			authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
		}
}
