package khshanovskyi.ktaiinterviewassistantrag.setting;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Provides DB settings that are taken from the `application.yaml`
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "db")
public class DbSettings {
    private String username;
    private String password;
    private String url;
    private String host;
    private int port;
    private String database;
    private String interviewsTableName;
}
