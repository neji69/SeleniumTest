package com.github.neji69;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.TimeUnit;

public class TestYandex {

    @Test
    public void TestFirst() throws InterruptedException {
        String keyword = "руддщ цкщдв";
        System.setProperty("webdriver.chrome.driver","src/main/resources/ChromeDriver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://yandex.ru");
        driver.findElement(By.name("text")).sendKeys(keyword + Keys.ENTER);

        TimeUnit.SECONDS.sleep(3);

        String polePoiska = driver.findElement(By.name("text")).getAttribute("value");

        assertThat(polePoiska)
                .as("Проверка введенного запроса")
                .isEqualTo("hello world");

        assertThat(driver.getTitle())
                .as("Проверка Тайтла")
                .isEqualTo("hello world — Яндекс: нашлось 2 млн результатов");

        driver.quit();
    }
}
