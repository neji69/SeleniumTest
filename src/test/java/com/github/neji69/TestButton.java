package com.github.neji69;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
import static org.assertj.core.api.Assertions.assertThat;


public class TestButton {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().arch32().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testButton() {

        driver.get("https://savkk.github.io/selenium-practice/");
        driver.findElement(By.id("button")).click();
        driver.findElement(By.id("first")).click();

        assertThat(driver.findElement(By.xpath("//label[text()]")).getText())
                .as("Проверка надписи Excellent! ")
                .isEqualTo("Excellent!");

        assertThat(driver.findElement(By.className("button-primary")).getAttribute("value"))
                .as("Проверка появилась ли кнопка Click me too!")
                .isEqualTo("Click me too!");
        driver.findElement(By.className("button-primary")).click(); //вопрос я сначала использую элемент для проверки,
        // а потом на него же кликаю. Так норм или можно как то укоротить?

        assertThat(driver.findElement(By.id("back")).getText())
                .as("Проверка надписи Great! Return to menu ")
                .isEqualTo("Great! Return to menu");
        driver.findElement(By.linkText("Great! Return to menu")).click();
    }

    @AfterClass
    public void exitDriver() {
        driver.quit();
    }

}
