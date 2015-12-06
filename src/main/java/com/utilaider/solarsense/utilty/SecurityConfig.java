package com.utilaider.solarsense.utilty;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	@Qualifier("loginServiceImpl")
	UserDetailsService userDetailsService;
	@Autowired
	private DataSource dataSource;

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(
				new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.addFilterBefore(new SimpleCORSFilter(),
				ChannelProcessingFilter.class).authorizeRequests()
				.antMatchers("/register/**").permitAll().anyRequest()
				.authenticated().and().formLogin().loginPage("/login")
				.loginProcessingUrl("/j_spring_security_check")
				.usernameParameter("userName").passwordParameter("password")
				.failureUrl("/login?error").defaultSuccessUrl("/home", true)
				.permitAll().and().logout().logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID")
				.logoutUrl("/j_spring_security_logout");

	}
}
