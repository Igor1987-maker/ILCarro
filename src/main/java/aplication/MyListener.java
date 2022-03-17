package aplication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

public class MyListener extends AbstractWebDriverEventListener {

    Logger logger = LoggerFactory.getLogger(MyListener.class);

    public MyListener() {

        super();
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {

        super.beforeFindBy(by, element, driver);
        logger.info("Start finding element by ------>" +by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {

        super.afterFindBy(by, element, driver);

        logger.info("The element -->" +by + "was found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {

        super.onException(throwable, driver);
        logger.info("We heave throwable -->" +throwable.getMessage());

        int i = (int) (System.currentTimeMillis()/1000%3600);
        String screenshot = "src/test/screenshots/screen-"+i+".png";
        HelperBase hb = new HelperBase(driver);
        hb.takeScreenShot(screenshot);

    }

    @Override
    public void beforeAlertAccept(WebDriver driver) {
        super.beforeAlertAccept(driver);
    }


}
