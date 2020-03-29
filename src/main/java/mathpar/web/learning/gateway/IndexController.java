package mathpar.web.learning.gateway;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class IndexController {

    //This resource should be injected in compilation time thus making it's impossible to run client locally without additional efforts
    //To run client locally you should download MathparWeb_Client project, build for production and copy all the assets produced to resources/assets folder
    //For Vue project (currently in use) run `npm run build` and then copy all `dist` folder content to assets folder so that index.html is located in the assets root
    @GetMapping({"/", ""})
    public String getIndex() throws IOException {
        return new String(new ClassPathResource("/assets/index.html").getInputStream().readAllBytes());
    }
}
