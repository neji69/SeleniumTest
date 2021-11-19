package com.github.neji69;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().arch32().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testFrame() {
        driver.get("https://savkk.github.io/selenium-practice/");
        driver.findElement(By.id("iframe")).click();
        driver.switchTo().frame("code-frame");

        String unvalidCode = driver.findElement(By.id("code")).getText();
        String valideCode = unvalidCode.replaceAll("Your code is: ", "");
        driver.switchTo().defaultContent();

        driver.findElement(By.name("code")).sendKeys(valideCode);
        driver.findElement(By.name("ok")).click();
        driver.findElement(By.linkText("Great! Return to menu")).click();

        assertThat(driver.manage().getCookieNamed("iframe").getValue())
                .isEqualTo("done");
    }

    @AfterClass
    public void exitDriver() {
        driver.quit();
    }
}
