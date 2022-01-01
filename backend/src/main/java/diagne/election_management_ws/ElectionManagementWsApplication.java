package diagne.election_management_ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ElectionManagementWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectionManagementWsApplication.class, args);
    }

}
