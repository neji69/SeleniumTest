package com.github.neji69;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class PromtAllertConfirm {
    private static final Logger log = Logger.getLogger(PromtAllertConfirm.class);
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

    @Test
    public void negativTestAlert() {
        driver.get("https://savkk.github.io/selenium-practice/");
        driver.findElement(By.id("alerts")).click();
        driver.findElement(By.className("get")).click();
        Alert alert = driver.switchTo().alert();
        String unvalidCode = alert.getText();
        String valideCode = unvalidCode.replaceAll("Your password: ", "");
        alert.accept();
        driver.findElement(By.className("set")).click();
        Alert promt = driver.switchTo().alert();
        promt.sendKeys("не правильный код");
        promt.accept();
        List<WebElement> negativvalue;
        negativvalue = (driver.findElements(By.xpath("//*[@id=\"content\"]/label")));
        if(negativvalue.size() == 0) log.info("Негативный тест прошел успешно. Пароль неверный. Файл не найден.");
        else {
            driver.findElement(By.className("return")).click();
            driver.switchTo().alert().accept();

            assertThat(driver.manage().getCookieNamed("alerts").getValue())
                    .isEqualTo("done");
        }
    }

    @AfterTest
    public void exitDriver() {
        driver.quit();
    }
}
