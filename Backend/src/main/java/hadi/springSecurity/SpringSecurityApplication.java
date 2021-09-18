package hadi.springSecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import hadi.springSecurity.BL.UserBL;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringSecurityApplication.class, args);
	}

}
