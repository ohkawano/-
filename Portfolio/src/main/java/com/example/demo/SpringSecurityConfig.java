package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	//パスワードのエンコード方式を変更
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private DataSource dataSource;

	private static final String UserSQL = "SELECT"
			+ " username,"
			+ " authority,"
			+ " true"
			+ " FROM"
			+ " authorities"
			+ " WHERE"
			+ " username=?";

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/css/**", "/image/**", "/js/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/* 以下はアクセス設定です
		 * .permitAll()がついているものは全員がアクセスOK
		 * それ以外は.anyRequest().authenticated();でアクセス禁止*/
		http.authorizeRequests()
				.antMatchers("/home").permitAll()
				.antMatchers("/timeLine").permitAll()
				.antMatchers("/add").permitAll()
				.anyRequest().authenticated();
		http.formLogin()
				.loginPage("/login").permitAll()
				.loginProcessingUrl("/signIn")
				.usernameParameter("username")
				.passwordParameter("authority")
				.defaultSuccessUrl("/adminisratorTimeLine", true)
				.failureUrl("/login?error").permitAll();
		http.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login").permitAll()
				.deleteCookies("JSESSIONID");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
				.dataSource(dataSource)
				.usersByUsernameQuery(UserSQL)
				.passwordEncoder(passwordEncoder());

	}

}
