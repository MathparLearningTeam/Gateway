package mathpar.web.learning.gateway;

import mathpar.web.learning.gateway.utils.MathparProperties;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class SchoolProxy {
    private final RestTemplate restTemplate = new RestTemplate();
    private final MathparProperties mathparProperties;

    public SchoolProxy(MathparProperties mathparProperties) {
        this.mathparProperties = mathparProperties;
    }

    @RequestMapping(path = "/school/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity<Object> proxyRequest(RequestEntity<Object> requestEntity) throws URISyntaxException {
        var request = new RequestEntity<>(requestEntity.getBody(),
                requestEntity.getHeaders(),
                requestEntity.getMethod(),
                // Path url is cropped for 7 levels from beginning to drop /school prefix; Substring is more efficient then .replace() operation
                // Api prefix is appended to limit the range of endpoints which we are able to proxy so no private endpoints are proxied
                new URI(mathparProperties.getSchoolModulePrefix() + "/api" + requestEntity.getUrl().getPath().substring(7) + "?" + requestEntity.getUrl().getQuery()));
        return restTemplate.exchange(request, Object.class);
    }

    @GetMapping(path = "/school/docs")
    public ResponseEntity<Object> proxyDocsRequest(){
        return restTemplate.getForEntity(mathparProperties.getSchoolModulePrefix()+"/v2/api-docs", Object.class);
    }
}
