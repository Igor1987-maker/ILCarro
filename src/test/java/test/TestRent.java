package test;

import aplication.MyDataProvider;
import models.User;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestRent extends TestBase {

    @Test(dataProvider = "dataRentingCar",dataProviderClass = MyDataProvider.class)
    public void rentWithLogin(String city, String dataFrom, String dataTo,
                              String email,String password,String name,String Lastname,String phoneNumber) {
        if (app.getUserHelper().isLogInPreset()) {
            app.getUserHelper().openLoginForm();
            app.getUserHelper().fillLoginForm(email, password);
            app.getUserHelper().submitForm();
        }
        app.getSearchHelper().findCarByLocationAndDate(city, dataFrom, dataTo);
        app.getRentHelper().choseAcar();
        User user = new User()
                .withEmail(email)
                .withName(name)
                .withLastName(Lastname)
                .withPhone(phoneNumber);
        app.getRentHelper().fillOrderForm(user);
        app.getRentHelper().clickOnReserve();
        Assert.assertEquals(app.getRentHelper().messageAfterOrder(), "Order success");
        app.getRentHelper().closeButtonClick();


    }


    @Test
    public void rentWithoutLogin() {

        app.getSearchHelper().findCarByLocationAndDate("New York", "27 DEC 2021", "30 DEC 2021");
        app.getRentHelper().choseAcar();
        app.getRentHelper().logInBeforeRent("noa@gmail.com", "Nnoa12345$");
        app.getRentHelper().clickOnRentNow();
        User user = new User()
                .withEmail("noa@gmail.com")
                .withName("George")
                .withLastName("Bush")
                .withPhone("+972543221190");
        app.getRentHelper().fillOrderForm(user);
        app.getRentHelper().clickOnReserve();
        Assert.assertEquals(app.getRentHelper().messageAfterOrder(), "Order success");
        app.getRentHelper().closeButtonClick();

    }

}
