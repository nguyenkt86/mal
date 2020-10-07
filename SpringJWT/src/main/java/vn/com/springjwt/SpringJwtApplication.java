package vn.com.springjwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import vn.com.springjwt.model.AppProperties;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableConfigurationProperties(AppProperties.class)
public class SpringJwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringJwtApplication.class, args);
	}

}
