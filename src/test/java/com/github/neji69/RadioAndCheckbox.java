package com.github.neji69;

import org.openqa.selenium.By;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class RadioAndCheckbox {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().arch32().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testRadioCheckbox() {
        driver.get("https://savkk.github.io/selenium-practice/");
        driver.findElement(By.id("checkbox")).click();
        driver.findElement(By.id("one")).click();
        driver.findElement(By.id("go")).click();
        assertThat(driver.findElement(By.id("one")).getAttribute("value"))
                .as("проверка ресультата чекбокса")
                .isEqualTo(driver.findElement(By.id("result")).getText());

//проверяем условия радиобокса на странице "Checkbox and Radio"
        driver.findElement(By.id("radio_one")).click();
        driver.findElement(By.id("radio_go")).click();
        assertThat(driver.findElement(By.id("radio_one")).getAttribute("value"))
                .as("проверка результата радиобокса")
                .isEqualTo(driver.findElement(By.id("radio_result")).getText());
        driver.findElement(By.linkText("Great! Return to menu")).click();

        assertThat(driver.manage().getCookieNamed("checkboxes").getValue())
                .isEqualTo("done");
    }

    @AfterClass
    public void exitDriver() {
        driver.quit();
    }
}
