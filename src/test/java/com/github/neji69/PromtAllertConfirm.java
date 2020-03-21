package com.github.neji69;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class PromtAllertConfirm {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().arch32().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testAlert() {
        driver.get("https://savkk.github.io/selenium-practice/");
        driver.findElement(By.id("alerts")).click();
        driver.findElement(By.className("get")).click(); // разница между класc и класcнейм?
        Alert alert = driver.switchTo().alert();
        String unvalidCode = alert.getText();
        ;
        String valideCode = unvalidCode.replaceAll("Your password: ", "");
        alert.accept();
        driver.findElement(By.className("set")).click();
        Alert promt = driver.switchTo().alert();
        promt.sendKeys(valideCode);
        promt.accept();
        assertThat(driver.findElement(By.xpath("//*[@id=\"content\"]/label")).getText())
                .as("Есть ли надпись")
                .isEqualTo("Great!");
        driver.findElement(By.className("return")).click();
        driver.switchTo().alert().accept();
    }

    @AfterTest() //Афтер класс?
    public void exitDriver() {
        driver.quit();
    }
}
