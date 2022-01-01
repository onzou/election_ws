package diagne.election_management_ws.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter @Setter
@ConfigurationProperties(prefix = "file")
public class FileProperties
{
    private String uploadDir;
}