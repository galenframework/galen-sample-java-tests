package net.mindengine.galen.java.sample.tests;

import net.mindengine.galen.java.sample.components.GalenTestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;


public class WelcomePageTest extends GalenTestBase {

    @Test(dataProvider = "devices")
    public void welcomePage_shouldLookGood_onDevice(TestDevice device) throws IOException {
        load("/");
        checkLayout(driver, "/specs/welcomePage.spec", device.getTags());
    }

    @Test(dataProvider = "devices")
    public void loginPage_shouldLookGood_onDevice(TestDevice device) throws IOException {
        load("/");
        driver.findElement(By.cssSelector(".btn")).click();
        checkLayout(driver, "/specs/loginPage.spec", device.getTags());
    }


}
