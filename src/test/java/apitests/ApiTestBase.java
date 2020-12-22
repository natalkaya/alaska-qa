package apitests;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import httpclient.AlaskaHttpClient;
import org.junit.jupiter.api.BeforeAll;

import java.util.Optional;
import java.util.logging.Logger;

public class ApiTestBase {
    private static Config testConfig;
    protected static AlaskaHttpClient alaskaService;
    protected static Logger log;

    @BeforeAll
    public static void suiteSetUp() {
        testConfig = ConfigFactory.load();
        log = Logger.getLogger("SuiteSetup");
        try {
            alaskaService = new AlaskaHttpClient(Optional.of(testConfig.getString("alaskaservice.baseuri")).orElseThrow(Exception::new));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void removeBears() {
        try {
            if (alaskaService != null) alaskaService.deleteAllBears();
        } catch (Exception e) {
            log.warning(e.getMessage());
        }
    }

}
