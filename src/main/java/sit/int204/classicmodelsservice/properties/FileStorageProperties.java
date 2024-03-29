package sit.int204.classicmodelsservice.properties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix= "file")
public class FileStorageProperties {
    private String uploadDir;
    private String fileServiceHostName;

}
