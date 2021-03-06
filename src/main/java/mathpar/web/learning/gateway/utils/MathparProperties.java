package mathpar.web.learning.gateway.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.client.RestTemplate;

@Getter
@NoArgsConstructor
@ConfigurationProperties("mathpar")
public class MathparProperties {
    private String accountModulePrefix;
    private String schoolModulePrefix;
    private String tasksModulePrefix;

    public void loadPropertiesFromManager(String prefix) {
        RestTemplate restTemplate = new RestTemplate();
        var namespaceProperties = restTemplate.getForObject(prefix+"/getNamespaceProperties?namespace=gateway", FrontendProperties.class);
        if(namespaceProperties==null) throw new RuntimeException("Can't load authentication properties");
        this.schoolModulePrefix = namespaceProperties.schoolUrl;
        this.accountModulePrefix = namespaceProperties.accountUrl;
        this.tasksModulePrefix = namespaceProperties.tasksUrl;
    }

    @Data
    public static class FrontendProperties {
        @JsonProperty("SchoolUrl")
        private String schoolUrl;
        @JsonProperty("AccountUrl")
        private String accountUrl;
        @JsonProperty("TasksUrl")
        private String tasksUrl;
    }
}
