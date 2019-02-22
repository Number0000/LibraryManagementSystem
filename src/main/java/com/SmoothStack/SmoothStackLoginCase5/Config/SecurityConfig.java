package com.SmoothStack.SmoothStackLoginCase5.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	@Autowired
//	private AuthorRepository authorRepository;
//	
//	@Autowired
//	protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(authorName -> {
//			Author author = authorRepository.findOne(authorName);
//			if(author != null) {
//				return new org.springframework.security.core.userdetails.User(author.getUserName(), author.getPassword(), 
//						true, true, true, true, AuthorityUtils.createAuthorityList("USER"));
//			} else {
//				throw new UsernameNotFoundException("Could not find the user" + authorName + "'");
//			}
//		});
//	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests().anyRequest().fullyAuthenticated().and().httpBasic().and().csrf().disable();
	}
}