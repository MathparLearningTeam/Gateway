package mathpar.web.learning.gateway.controllers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import mathpar.web.learning.gateway.utils.MathparProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class DocsController {
    private final RestTemplate restTemplate = new RestTemplate();
    private final MathparProperties mathparProperties;

    public DocsController(MathparProperties mathparProperties) {
        this.mathparProperties = mathparProperties;
    }

    @GetMapping("/swagger-resources")
    public List<SwaggerResource> getResources(){
        return List.of(
                new SwaggerResource("Account API", "/account/docs", "2.0", "/account/docs"),
                new SwaggerResource("School API", "/school/docs", "2.0", "/school/docs")
        );
    }

    @GetMapping("/swagger-resources/configuration/security")
    public String getSecurity(){
        return "{}";
    }

    @GetMapping("/swagger-resources/configuration/ui")
    public String getUiProperties(){
        return "{\"deepLinking\":true,\"displayOperationId\":false,\"defaultModelsExpandDepth\":1,\"defaultModelExpandDepth\":1,\"defaultModelRendering\":\"example\",\"displayRequestDuration\":false,\"docExpansion\":\"none\",\"filter\":false,\"operationsSorter\":\"alpha\",\"showExtensions\":false,\"tagsSorter\":\"alpha\",\"validatorUrl\":\"\",\"apisSorter\":\"alpha\",\"jsonEditor\":false,\"showRequestHeaders\":false,\"supportedSubmitMethods\":[\"get\",\"put\",\"post\",\"delete\",\"options\",\"head\",\"patch\",\"trace\"]}";
    }

    @GetMapping(path = "/account/docs")
    public ResponseEntity<Object> proxyAccountDocsRequest(){
        return restTemplate.getForEntity(mathparProperties.getAccountModulePrefix()+"/v2/api-docs", Object.class);
    }

    @GetMapping(path = "/school/docs")
    public ResponseEntity<Object> proxySchoolDocsRequest(){
        return restTemplate.getForEntity(mathparProperties.getSchoolModulePrefix()+"/v2/api-docs", Object.class);
    }

    @Getter
    @AllArgsConstructor
    private static class SwaggerResource{
        private final String name;
        private final String url;
        private final String swaggerVersion;
        private final String location;
    }
}
