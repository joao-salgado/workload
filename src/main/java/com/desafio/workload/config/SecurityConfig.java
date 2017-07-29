package com.desafio.workload.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/layout/**")
			.antMatchers("/images/**"); 
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/departamentos/novo").hasRole("CADASTRAR_DEPARTAMENTO")
				.antMatchers("/cursos/novo").hasRole("CADASTRAR_CURSO")
				.antMatchers("/tipos/novo").hasRole("CADASTRAR_TIPO_CURSO")
				.antMatchers("/disciplinas/nova").hasRole("CADASTRAR_DISCIPLINA")
				.antMatchers("/turmas/nova").hasRole("CADASTRAR_TURMA")
				.antMatchers("/alunos/novo").hasRole("CADASTRAR_ALUNO")
				.antMatchers("/professores/**").hasRole("CADASTRAR_PROFESSOR")
				.antMatchers("/atividades/**").hasRole("DOCENTE")
				.antMatchers("/usuarios/**").hasRole("CADASTRAR_USUARIO")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403")
				.and()
			.sessionManagement()
				//.maximumSessions(1)  DIZ RESPEITO A QUANTIDADE DE USUÁRIOS SIMULTÂNEOS COM O MESMO LOGIN NO SISTEMA
				.invalidSessionUrl("/login"); //DIZ RESPEITO À PÁGINA QUE SERÁ REDIRECIONADA SE DER CONFLITO NA SESSÃO OU POST INVÁLIDO
			
				
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
