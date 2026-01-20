package cofig;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.slf4j.*;
import tests.ApiTestLombok;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class RequestConfig {
    private static final String TOKEN = "d80dff7a51893caef056229334296ff3e646a5e3b8674ab80205a421b63c3641";
    private static final String environmentFileName = System.getProperty("environment.file", "sitnext.properties");
    private static final Logger log = LoggerFactory.getLogger(ApiTestLombok.class);

    public static RequestSpecification getCommonSpec(){
        return new RequestSpecBuilder().setBaseUri(getEnvironmentName())
                .addHeader("Authorization", "Bearer " + TOKEN)
                .setContentType(ContentType.JSON).build();
    }

    static String getEnvironmentName(){
        //Read properties file
        String environmentName = null;
        Properties properties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream("target/test-classes/" + environmentFileName)) {
            properties.load(fileInputStream);

            //Set BASE_URL using the value from properties file
            environmentName = properties.getProperty("baseUrl");
            //System.out.println("API Tests will run against: " + BASE_URL);
        } catch (IOException e) {
            //System.err.println("Could not load properties file" + environmentFileName);
            log.info("Could not load properties file: {}", environmentFileName);
        }

        if (environmentName == null) {
            throw new RuntimeException("Property 'baseUrl' not found in: " + environmentFileName);
        }

        log.info("API Tests will run against: {}", environmentName);
        return environmentName;
    }
}
