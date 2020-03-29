package mathpar.web.learning.gateway;

import mathpar.web.learning.gateway._configs.LearningApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = LearningApplication.class)
@ActiveProfiles("test")
class LearningApplicationTests {

	@Test
	void contextLoads() {
	}

}
