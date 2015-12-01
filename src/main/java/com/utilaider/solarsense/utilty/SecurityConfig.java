package com.utilaider.solarsense.utilty;

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
				ChannelProcessingFilter.class).authorizeRequests().anyRequest()
				.authenticated().and().formLogin().loginPage("/")
				.loginProcessingUrl("j_spring_security_check")
				.usernameParameter("user").passwordParameter("password")
				.failureUrl("/login?error").defaultSuccessUrl("/", true)
				.permitAll().and().logout().logoutSuccessUrl("/login?logout")
				.invalidateHttpSession(true).deleteCookies("JSESSIONID")
				.logoutUrl("/j_spring_security_logout");

	}
}
