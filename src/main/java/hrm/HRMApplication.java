package hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class HRMApplication {

	public static void main(String[] args) {
		SpringApplication.run(HRMApplication.class, args);
	}

	@Bean
	public RestTemplate customRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}