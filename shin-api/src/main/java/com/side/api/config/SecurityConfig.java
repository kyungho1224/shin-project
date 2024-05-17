package com.side.api.config;

import com.side.api.config.filter.JwtTokenFilter;
import com.side.api.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

	private final JwtTokenFilter jwtTokenFilter;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		  .httpBasic(AbstractHttpConfigurer::disable)
		  .csrf(AbstractHttpConfigurer::disable)
		  .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		  .authorizeHttpRequests(request -> request
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
			.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
			.requestMatchers("/open-api/**").permitAll()
			.requestMatchers("/h2-console/**").permitAll()
			.anyRequest().authenticated()
		  )
		  .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
		  .formLogin(AbstractHttpConfigurer::disable)
		;
		return httpSecurity.build();
	}

}
