package com.github.neji69;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import static org.assertj.core.api.Assertions.*;
import java.util.concurrent.TimeUnit;

public class TestYandex {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().arch32().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test (enabled = false)
    public void testYandex() {

        String keyword = "руддщ цкщдв";
        driver.get("https://yandex.ru");
        driver.findElement(By.name("text")).sendKeys(keyword + Keys.ENTER);


        String polePoiska = driver.findElement(By.name("text")).getAttribute("value");

        assertThat(polePoiska)
                .as("Проверка введенного запроса")
                .isEqualTo("hello world");

        assertThat(driver.getTitle())
                .as("Проверка Тайтла")
                .isEqualTo("hello world — Яндекс: нашлось 2 млн результатов");
    }

    @AfterClass
    public void exitDriver() {
        driver.quit();
    }
}
