package base;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.github.tomakehurst.wiremock.client.WireMock.configureFor;

public abstract class BaseTest {
    protected static WireMockServer wireMockServer;
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    @BeforeAll
    static void startMockServer(){
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        configureFor("localhost", 8080);
        log.info("WireMock server started at port: " + wireMockServer.port());
    }

    @AfterAll
    static void stopMockServer(){
        if (wireMockServer != null && wireMockServer.isRunning()) {
            wireMockServer.stop();
            log.info("WireMock server stopped");
        }
    }
}
