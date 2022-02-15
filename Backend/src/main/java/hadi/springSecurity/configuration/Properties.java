package hadi.springSecurity.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")
@Component
public class Properties
{
	@Value("${auth.credentials.expire-in-days}")
	private int credentialExpirationDays;
	@Value("${auth.token-lifetime-mins}")
	private int tokenLifetime;
	@Value("${auth.refresh-token-lifetime-mins}")
	private int refreshTokenLifetime;
	@Value("${jwt.secret}")
	private String jwtSecret;
	@Value("${jwt.issuer}")
	private String jwtIssuer;
	@Value("${auth.max-concurrent-logins}")
	private int maxConcurrentLogins;
	@Value("${mail.from}")
	private String mailFrom;
	@Value("${mail.signature}")
	private String mailSignature;
	
	public int getCredentialExpirationDays()
	{
		return credentialExpirationDays;
	}

	public int getTokenLifetime()
	{
		return tokenLifetime;
	}
	
	public int getRefreshTokenLifetime()
	{
		return refreshTokenLifetime;
	}

	public String getJwtSecret()
	{
		return jwtSecret;
	}

	public String getJwtIssuer()
	{
		return jwtIssuer;
	}

	public int getMaxConcurrentLogins()
	{
		return maxConcurrentLogins;
	}

	public String getMailFrom()
	{
		return mailFrom;
	}

	public String getMailSignature()
	{
		return mailSignature;
	}
}
