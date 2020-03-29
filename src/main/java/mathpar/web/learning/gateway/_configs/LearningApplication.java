package mathpar.web.learning.gateway._configs;

import mathpar.web.learning.gateway.utils.MathparProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(MathparProperties.class)
@SpringBootApplication(scanBasePackages = {"mathpar.web.learning.gateway.*"})
public class LearningApplication {
	public static void main(String[] args) {
		SpringApplication.run(LearningApplication.class, args);
	}
}
