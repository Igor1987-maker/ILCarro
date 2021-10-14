package aplication;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.time.LocalDate;

public class SearchHelper extends HelperBase{


    public SearchHelper(WebDriver wd) {
        super(wd);
    }


    public void findCarByLocationAndDate(String city,String beginDate, String overDate) {
        String [] arrBegin = beginDate.split(" ");
        String [] arrOver = overDate.split(" ");
        int dayTake = Integer.parseInt(arrBegin[0]);
        int dayReturn = Integer.parseInt(arrOver[0]);

        fillCity(city);

        click(By.id("dates"));
        click(By.cssSelector("button[aria-label='Choose month and year']"));
        click(By.xpath("//div[text()=' "+arrBegin[2]+" ']"));
        click(By.xpath("//div[text()=' "+arrBegin[1]+" ']"));
        click(By.xpath(String.format("//div[text()=' %s ']",dayTake)));
        click(By.xpath(String.format("//div[text()=' %s ']",dayReturn)));
        hitOnYalla();



    }

    private void hitOnYalla() {
        click(By.cssSelector("button[type='submit']"));
    }

    private void fillCity(String city) {
        type(By.id("city"), city);
        click(By.xpath("//div[@class='pac-container pac-logo']//div[1]"));
    }

    public void selectDataInFuture(String city, String dataFrom, String dataTo){

        fillCity(city);

        String [] dataF = dataFrom.split(" ");
        String [] dataT = dataTo.split(" ");

        int monthTake = Integer.parseInt(dataF[1]);
        int monthReturn = Integer.parseInt(dataT[1]);
        int monthCurrent = LocalDate.now().getMonthValue();

        click(By.id("dates"));
        choseMonth(monthTake, monthCurrent);

        int dayTake = Integer.parseInt(dataF[0]);
        click(By.xpath(String.format("//div[text()=' %s ']",dayTake)));

        choseMonth(monthReturn,monthTake);

        int dayReturn = Integer.parseInt(dataT[0]);
        click(By.xpath(String.format("//div[text()=' %s ']",dayReturn)));

        hitOnYalla();

        }


    private void choseMonth(int month1, int month2) {
        if(month1 > month2) {
            int count = month1 - month2;
            for (int i = 0; i < count; i++) {

                click(By.cssSelector("button[aria-label='Next month']"));
            }
        }
            else {
            int count = 12 - month2 + month1;
            for (int i = 0; i <= count; i++) {

                click(By.cssSelector("button[aria-label='Next month']"));
            }
          }
    }


    public void typingNegativeDates(String city, String dates) {
        fillCity(city);
        type(By.id("dates"),dates);
        String message = wd.findElement(By.xpath("//div[@class='ng-star-inserted']")).getText();
        Assert.assertEquals(message,"You can't pick date before today");

    }
}
