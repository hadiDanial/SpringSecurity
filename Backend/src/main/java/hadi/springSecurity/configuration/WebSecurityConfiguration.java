package hadi.springSecurity.configuration;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import hadi.springSecurity.security.filters.UsernamePasswordAuthenticationFilter;
import hadi.springSecurity.security.providers.UsernamePasswordAuthenticationProvider;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	private final UsernamePasswordAuthenticationProvider usernamePasswordAuthProvider;
	private final UsernamePasswordAuthenticationFilter usernamePasswordAuthFilter;

	@Autowired
	public WebSecurityConfiguration(UsernamePasswordAuthenticationProvider usernamePasswordAuthProvider,
			UsernamePasswordAuthenticationFilter usernamePasswordAuthFilter)
	{
		this.usernamePasswordAuthProvider = usernamePasswordAuthProvider;
		this.usernamePasswordAuthFilter = usernamePasswordAuthFilter;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth.authenticationProvider(usernamePasswordAuthProvider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.addFilterAt(usernamePasswordAuthFilter, BasicAuthenticationFilter.class);
		http.csrf().disable(); // lesson 9 // focus on CORS
//		http.authorizeRequests().mvcMatchers("*").permitAll().anyRequest().permitAll();
		http.httpBasic();
		http.csrf().disable();
		http.authorizeRequests()
						.mvcMatchers("*").permitAll();

		http.cors(c -> {
			CorsConfigurationSource cs = r -> {
				CorsConfiguration cc = new CorsConfiguration();
				cc.setAllowedOrigins(List.of("*"));
				cc.setAllowedMethods(List.of("GET", "POST"));
				return cc;
			};

			c.configurationSource(cs);
		});
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

//
//	@Bean
//	public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception
//	{
//		return new UsernamePasswordAuthenticationFilter(authenticationManager());
//	}
}
