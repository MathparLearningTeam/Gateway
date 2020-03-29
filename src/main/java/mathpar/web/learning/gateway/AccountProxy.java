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
public class AccountProxy {
    private final RestTemplate restTemplate = new RestTemplate();
    private final MathparProperties mathparProperties;

    public AccountProxy(MathparProperties mathparProperties) {
        this.mathparProperties = mathparProperties;
    }

    @RequestMapping(path = "/account/**", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity<Object> proxyAuthenticationRequest(RequestEntity<Object> requestEntity) throws URISyntaxException {
        var request = new RequestEntity<>(requestEntity.getBody(),
                requestEntity.getHeaders(),
                requestEntity.getMethod(),
                // Path url is cropped for 8 levels from beginning to drop /account prefix; Substring is more efficient then .replace() operation
                // Api prefix is appended to limit the range of endpoints which we are able to proxy so no private endpoints are proxied
                new URI(mathparProperties.getAccountModulePrefix() + "/api" + requestEntity.getUrl().getPath().substring(8) + "?" + requestEntity.getUrl().getQuery()));
        return restTemplate.exchange(request, Object.class);
    }

    @GetMapping(path = "/account/docs")
    public ResponseEntity<Object> proxyDocsRequest(){
        return restTemplate.getForEntity(mathparProperties.getAccountModulePrefix()+"/v2/api-docs", Object.class);
    }
}
