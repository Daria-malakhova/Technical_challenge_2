package lastFM;

import cucumber.api.java8.En;
import lastFM.AppTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;


@SpringBootTest(classes = AppTest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("INTEGRATION_TEST")
@ContextConfiguration
public class CucumberRoot implements En {

    @Autowired
    protected TestRestTemplate template;

}