package com.github.neji69;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class TableTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().arch32().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testTable() {
        driver.get("https://savkk.github.io/selenium-practice/");
        driver.findElement(By.id("table")).click();

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[1]/input")).sendKeys("Газпром");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[2]/input")).sendKeys("Руслан Субаев");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[3]/input")).sendKeys("Татарстан");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input")).click();

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[1]/input")).sendKeys("РусНефть");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[2]/input")).sendKeys("Мухди Анаир");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[3]/input")).sendKeys("Киргизия");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input")).click();

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[1]/input")).sendKeys("Вавилон");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[2]/input")).sendKeys("Марат Газиев");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[3]/input")).sendKeys("Москва");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input")).click();

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[1]/input")).sendKeys("Найк");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[2]/input")).sendKeys("Дмитрий Найкович");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[3]/input")).sendKeys("Китай");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input")).click();

        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[1]/input")).sendKeys("СтройСырМед");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[2]/input")).sendKeys("Иосиф Яковский");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/div[3]/input")).sendKeys("Тебет");
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/form/input")).click();


        driver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[2]/td[1]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[4]/td[1]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"customers\"]/tbody/tr[7]/td[1]/input")).click();
        driver.findElement(By.xpath("//*[@id=\"content\"]/input")).click();

        driver.findElement(By.linkText("Great! Return to menu")).click();

        assertThat(driver.manage().getCookieNamed("table").getValue())
                .isEqualTo("done");
    }

    @AfterClass
    public void exitDriver() {
        driver.quit();
    }
}
