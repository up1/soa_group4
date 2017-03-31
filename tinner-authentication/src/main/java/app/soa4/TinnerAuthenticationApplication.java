package app.soa4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@CrossOrigin(origins = "*")
public class TinnerAuthenticationApplication {
	@RequestMapping("/")
	String hello() {
		return "hello world";
	}
	public static void main(String[] args) {
		SpringApplication.run(TinnerAuthenticationApplication.class, args);
	}
}
