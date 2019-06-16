package lastFM.Steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lastFM.CucumberRoot;
import lastFM.Pages.LoginPage;
import lastFM.Pages.SearchPage;
import lastFM.Pages.UserPage;
import lastFM.config.ConfigBean;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class StepDefs extends CucumberRoot {

//    @Autowired
//    private ConfigBean configBean;

    private WebDriver driver;
    private final Logger logger = Logger.getLogger(StepDefs.class);

    public enum Browser {
        FIREFOX,
        CHROME,
        IE,
    };


    public WebDriver initWebDriver(Browser b){

        switch (b) {

            case FIREFOX: driver = new FirefoxDriver();
                break;

            case CHROME:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

                driver = new ChromeDriver();
                break;

            case IE: driver = new InternetExplorerDriver();
                break;

        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        return driver;
    }

    public void closeDriver()
    {
        driver.quit();
        logger.info("Browser closed\n====================================");
    }

    @Before
    public void setUp(){
        initWebDriver(Browser.CHROME);
    }


    @When("^the user enter \"([^\"]*)\" and \"([^\"]*)\"$")
    public void loginLastFm(String username, String password)
    {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login(username, password);
    }

    @Then("^user can find his \"([^\"]*)\" name at the login page$")
    public void checkLoginName(String login){
        LoginPage loginPage = new LoginPage(driver);
        Assert.assertEquals(loginPage.userName(), login);
    }

    @When("^user press \"logout\" button$")
    public void pressLogoutButton(){
        UserPage userPage = new UserPage(driver);
        userPage.openPage();
        userPage.logout();
    }

    @Then("^User can find \"login\" button$")
    public void checkLogoutFromLastFm(){
        UserPage userPage = new UserPage(driver);
        Assert.assertEquals(userPage.loginIcon(),"Login");
    }

    @When("^user enter track name \"([^\"]*)\" and press enter$")
    public void isSearchTrackCompleted(String searchQuery){
        SearchPage searchPage = new SearchPage(driver);
        searchPage.searchTrack(searchQuery).contains(searchQuery);
    }

    @Then("^user find ([^\"]*) on the page$")
    public void checkQuery(String searchQuery){
        SearchPage searchPage = new SearchPage(driver);
        Assert.assertTrue(searchPage.searchTrack(searchQuery).contains(searchQuery));
    }

    @After
    public void tearDown(){
        closeDriver();
    }
}
