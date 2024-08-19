package com.intrabucks.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.intrabucks.jwt.AuthEntryPoint;
import com.intrabucks.jwt.AuthenticationFilter;
import com.intrabucks.jwt.BucksAccessDeniedHandler;
import com.intrabucks.login.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@Autowired
	private AuthenticationFilter authenticationFilter;
	
	@Autowired
	private AuthEntryPoint exceptionHandler;
	
	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	@Autowired
    private BucksAccessDeniedHandler accessDeniedHandler;
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.cors().and().csrf().disable().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		.authorizeRequests()
		.antMatchers("/api/**/main/**").permitAll()
		.antMatchers("/api/**/department/**", "/api/**/employee/**", "/api/**/quitter/**").hasAnyRole("HR", "EB") // 인사
		.antMatchers("/api/**/sales/**").hasAnyRole("EB", "DM", "FD") // 매출
		.antMatchers("/api/**/stock/**").hasAnyRole("EB", "DM", "LO") // 재고
		.antMatchers("/api/**/store/**", "/api/**/manager/**").hasAnyRole("EB", "DM") // 매장
		.antMatchers("/api/**/purchase/**").hasAnyRole("EB", "DM", "PD", "LO") // 발주
		.antMatchers("/api/**/menu/**").hasAnyRole("RD") // 메뉴
		.antMatchers("/api/**/customer/**").hasAnyRole("CS", "MD") // CRM
		// 전자결재
		.anyRequest().authenticated().and() // 협업
		.exceptionHandling().accessDeniedHandler(accessDeniedHandler)
		.authenticationEntryPoint(exceptionHandler).and()
		.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
