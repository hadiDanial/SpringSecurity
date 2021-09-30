package hadi.springSecurity.configuration;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
//	private final UsernamePasswordAuthenticationProvider usernamePasswordAuthProvider;
//	private final UsernamePasswordAuthenticationFilter usernamePasswordAuthFilter;
	private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**",
            // other public endpoints of your API may be appended to this array
            "/auth/"
    };
	
//	@Autowired
//	public WebSecurityConfiguration(UsernamePasswordAuthenticationProvider usernamePasswordAuthProvider,
//			UsernamePasswordAuthenticationFilter usernamePasswordAuthFilter)
//	{
//		this.usernamePasswordAuthProvider = usernamePasswordAuthProvider;
//		this.usernamePasswordAuthFilter = usernamePasswordAuthFilter;
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception
//	{
//		auth.authenticationProvider(usernamePasswordAuthProvider);
//	}
//
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
//		http.addFilterAt(usernamePasswordAuthFilter, BasicAuthenticationFilter.class);
		http.csrf().disable(); // lesson 9 // focus on CORS
//		http.cors();
		http.httpBasic();
		http.authorizeRequests()
						.antMatchers(AUTH_WHITELIST)
						.permitAll()						
						.mvcMatchers(HttpMethod.GET, "user/**").authenticated()
						.anyRequest().permitAll();

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

}
