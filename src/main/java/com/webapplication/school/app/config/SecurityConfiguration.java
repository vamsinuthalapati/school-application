package com.webapplication.school.app.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.webapplication.school.app.jwtFilter.JwtRequestFilter;
import com.webapplication.school.app.repository.UserRepository;
import com.webapplication.school.app.serviceImpl.CustomUserDetailsService;



//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
//@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
@EnableOAuth2Sso
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private CustomUserDetailsService customUserDetails;
    
    @Autowired
	private JwtRequestFilter jwtRequestFilter;
    
    @Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(customUserDetails)
        .passwordEncoder(getPasswordEncoder());
        
//    		auth.userDetailsService(userDetailsService)
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/app/v1/userLogin").permitAll()
//                .anyRequest().authenticated()
//                .and().sessionManagement()
//				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////                .and()
////                .formLogin().permitAll();
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//
//    }
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
					.authorizeRequests().antMatchers("/app/v1/userLogin", "/app/v1/uploadFile", "/app/v1/deleteFile").permitAll()
					.anyRequest().authenticated();
//					.and().sessionManagement()
//					.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

    private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return true;
            }
        };
    }
}
