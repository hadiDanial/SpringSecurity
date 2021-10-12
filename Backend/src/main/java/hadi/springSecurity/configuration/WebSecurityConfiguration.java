package hadi.springSecurity.configuration;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import hadi.springSecurity.security.filters.TokenAuthFilter;
import hadi.springSecurity.security.filters.UsernamePasswordAuthFilter;
import hadi.springSecurity.security.providers.TokenAuthProvider;
import hadi.springSecurity.security.providers.UsernamePasswordAuthProvider;
import hadi.springSecurity.services.AuthenticationService;
import hadi.springSecurity.services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter
{
	private final UsernamePasswordAuthProvider usernamePasswordAuthProvider;
	private final UsernamePasswordAuthFilter usernamePasswordAuthFilter;
	private final TokenAuthProvider tokenAuthProvider;
	private final TokenAuthFilter tokenAuthFilter;
	private final AuthenticationService authenticationService;
	private final UserService userService;
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
            "/auth/**"
    };
	
	@Autowired
	@Lazy
	public WebSecurityConfiguration(UsernamePasswordAuthProvider usernamePasswordAuthProvider,
			UsernamePasswordAuthFilter usernamePasswordAuthFilter,
			TokenAuthProvider tokenAuthProvider,
			TokenAuthFilter tokenAuthFilter,
			AuthenticationService authenticationService,
			UserService userService)
	{
		this.usernamePasswordAuthProvider = usernamePasswordAuthProvider;
		this.usernamePasswordAuthFilter = usernamePasswordAuthFilter;
		this.tokenAuthProvider = tokenAuthProvider;
		this.tokenAuthFilter = tokenAuthFilter;
		this.authenticationService = authenticationService;
		this.userService = userService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception
	{
		auth
			.authenticationProvider(usernamePasswordAuthProvider)
			.authenticationProvider(tokenAuthProvider)
			.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.addFilterBefore(tokenAuthFilter, BasicAuthenticationFilter.class)
			.addFilterAt(usernamePasswordAuthFilter, BasicAuthenticationFilter.class);
		
		http.csrf().disable(); // lesson 9 // focus on CORS
		http.cors().disable();
		http.httpBasic();
		http.authorizeRequests()
						.antMatchers(AUTH_WHITELIST).permitAll()	
						.antMatchers("/user/**").permitAll()
						.anyRequest().authenticated();
//						.and()
//						.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//			            .and().formLogin().disable();

//		http.cors(c -> {
//			CorsConfigurationSource cs = r -> {
//				CorsConfiguration cc = new CorsConfiguration();
//				cc.setAllowedOrigins(List.of("*"));
//				cc.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
//				return cc;
//			};
//
//			c.configurationSource(cs);
//		});
	}

	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

 	@Bean
 	public UsernamePasswordAuthProvider usernamePasswordAuthProvider()
 	{
 		return new UsernamePasswordAuthProvider(authenticationService);
 	}
 	@Bean
 	public UsernamePasswordAuthFilter usernamePasswordAuthFilter() throws Exception
 	{
 		return new UsernamePasswordAuthFilter(authenticationManager());
 	}
 	@Bean
 	public TokenAuthProvider tokenAuthProvider()
 	{
 		return new TokenAuthProvider(authenticationService, userService);
 	}
 	@Bean
 	public TokenAuthFilter tokenAuthFilter() throws Exception
 	{
 		return new TokenAuthFilter(authenticationManager());
 	}
}
