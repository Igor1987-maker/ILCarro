package aplication;

import models.Car;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    EventFiringWebDriver wd;
    UserHelper userHelper;
    HelperCar car;
    SearchHelper searchHelper;
    RentHelper rentHelper;
    String browser;
    Properties properties;



    public ApplicationManager(String browser) {

        this.browser = browser;
    properties = new Properties();
    }

    public void init() throws IOException {

        String target = System.getProperty("target", "data");
        properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties",target))));

        if(browser.equals(BrowserType.CHROME)){
            wd = new EventFiringWebDriver(new ChromeDriver());
        }
        else if (browser.equals(BrowserType.FIREFOX)){
            wd = new EventFiringWebDriver(new FirefoxDriver());
        }
        wd.register(new MyListener());

        //wd.navigate().to("https://ilcarro.xyz/search");
        wd.navigate().to(properties.getProperty("web.Base"));
        wd.manage().window().maximize();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        userHelper = new UserHelper(wd);
        car = new HelperCar(wd);
        searchHelper = new SearchHelper(wd);
        rentHelper = new RentHelper(wd);

    }

    public void stop() {

        wd.quit();
    }

    public UserHelper getUserHelper() {

        return userHelper;
    }

    public HelperCar car() {
        return car;
    }

    public SearchHelper getSearchHelper() {
        return searchHelper;
    }

    public RentHelper getRentHelper() {
        return rentHelper;
    }

    public String setEmail() {
        return properties.getProperty("web.email");
    }
    public String setPassword() {
        return properties.getProperty("web.password");
    }
}
