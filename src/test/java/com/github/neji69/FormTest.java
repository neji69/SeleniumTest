package com.github.neji69;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FormTest {

    WebDriver driver;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().arch32().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testForm() {
        driver.findElement(By.id("form")).click();
        driver.findElement(By.xpath("//form[@id='testform']/div[1]/input[@type='text']")).sendKeys("Руслан");
        driver.findElement(By.xpath("//form[@id='testform']/div[2]/input[@type='text']")).sendKeys("Субаев");
        driver.findElement(By.xpath("//form[@id='testform']//input[@type='email']")).sendKeys("Silverrang@mail.ru");
        driver.findElement(By.xpath("//*[@id=\"testform\"]/div[4]/input[1]")).click();
        driver.findElement(By.xpath("//form[@id='testform']/div[5]/input[@type='text']")).sendKeys("Сафиулина 18");

        //URL resourceUrl = Thread.currentThread().getContextClassLoader().getResource("/javforselenium-1.png");
        //String filePath = resourceUrl.getPath();
        // WebElement fileInput = driver.findElement(By.xpath("//*[@id=\"testform\"]/div[6]/input"));
        //  fileInput.sendKeys(filePath);

        driver.findElement(By.xpath("//form[@id='testform']//textarea")).sendKeys("Я хз что тут должно быть");
        driver.findElement(By.xpath("//form[@id='testform']/input[@type='submit']")).click();
    }

    @AfterTest()
    public void exitDriver() {
        driver.quit();
    }
}
