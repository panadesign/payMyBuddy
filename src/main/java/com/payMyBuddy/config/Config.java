package com.payMyBuddy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
				.withUser("user1").password(passwordEncoder().encode("user1")).roles("USER")
				.and()
				.withUser("user2").password(passwordEncoder().encode("user2")).roles("USER");
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
				.authorizeRequests()
				.anyRequest().authenticated()
				.and()
				.formLogin().permitAll()
				.loginPage("/login")
				.defaultSuccessUrl("/transfer").permitAll()
				.and()
				.oauth2Login()
				.loginPage("/login")
				.defaultSuccessUrl("/transfer").permitAll()
				.and()
				.logout()
				.logoutUrl("/perform_logout")
				.logoutSuccessUrl("/login");

	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
				.antMatchers(
						"/css/**", "/img/**")
				.antMatchers("/h2-console/**");
	}


	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
