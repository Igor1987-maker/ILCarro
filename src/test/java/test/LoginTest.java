package test;

import aplication.MyDataProvider;
import models.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginTest extends TestBase {

    @BeforeMethod//(alwaysRun = true)
    public void preCondition() {
        if (app.getUserHelper().isLogoutPresent()) {
            app.getUserHelper().logout();
        }
    }




    @Test//(dataProvider = "validDataLogin",dataProviderClass = MyDataProvider.class)
    public static void loginSuccess(String email,String password) {
        app.getUserHelper().openLoginForm();
        app.getUserHelper().fillLoginForm("noa@gmail.com", "Nnoa12345$");
        app.getUserHelper().submitForm();
        //Assert.assertTrue(app.getUserHelper().isLogged());
    }





    @Test(dataProvider = "dataLoginCVS",dataProviderClass = MyDataProvider.class)
    public void negativeLoginWrongPassword(String email, String password) {
        {
            app.getUserHelper().openLoginForm();
            app.getUserHelper().fillLoginForm(email,password);
            app.getUserHelper().submitForm();


        }
    }

    @Test(groups = {"web"})
    public void negativeLoginWrongPassword() {
        {
            app.getUserHelper().openLoginForm();
            app.getUserHelper().fillLoginForm("noa@gmail.com", "Nnoa12345");
            app.getUserHelper().submitForm();
            Assert.assertFalse(app.getUserHelper().isLogged());

        }
    }


    @AfterMethod(alwaysRun = true)
    public void postCondition() {
        if (app.getUserHelper().isOkButtonPresent()) {
            app.getUserHelper().clickOkButton();
        }
        if (app.getUserHelper().isLogoutPresent()) {
            app.getUserHelper().logout();
        }

    }
}
