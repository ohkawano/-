package com.example.demo;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;

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
				.antMatchers("/login").permitAll()
				.antMatchers("/home").permitAll()
				.antMatchers("/timeLine").permitAll()
				.anyRequest().authenticated();
	}

}
