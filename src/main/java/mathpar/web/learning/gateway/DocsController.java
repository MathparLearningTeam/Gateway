package mathpar.web.learning.gateway;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DocsController {
    @GetMapping("/swagger-resources")
    public List<SwaggerResource> getResources(){
        return List.of(
                new SwaggerResource("Authentication API", "/authentication/docs", "2.0", "/authentication/docs"),
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

    @Getter
    @AllArgsConstructor
    private static class SwaggerResource{
        private final String name;
        private final String url;
        private final String swaggerVersion;
        private final String location;
    }
}
