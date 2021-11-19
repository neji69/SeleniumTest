package com.github.neji69;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.*;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

public class SelectTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().arch32().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testSelect() {
        driver.get("https://savkk.github.io/selenium-practice/");
        driver.findElement(By.id("select")).click();
        Select selectHero = new Select(driver.findElement(By.name("hero")));
        selectHero.selectByVisibleText("John von Neumann");

            /* Создаю список, создаю строку, затем разделяю строку на слова и помещаю
            в список каждое слово как отдельный элемент. (используя спец функцию Split) */
        List<String> progLang = new LinkedList<String>();
        String languages = (driver.findElement(By.name("languages")).getText());
        String[] arrSplit = languages.split("\n");
        for (int i = 0; i < arrSplit.length; i++) {
            progLang.add(arrSplit[i]);
        }

        Select seleclanguages = new Select(driver.findElement(By.name("languages")));

        int index1 = 0; // используеться для выбора индекса из списка
        int index2 = 3; // используеться для выбора индекса из списка

        seleclanguages.selectByIndex(index1);
        seleclanguages.selectByIndex(index2);
        driver.findElement(By.id("go")).click();
        List<WebElement> elementNameResult = driver.findElements(By.name("result"));

        assertThat(elementNameResult.get(1).getText())
                .as("Проверка выбора языков программирования ")
                .isEqualTo(progLang.get(index1) + ", " + progLang.get(index2));

        assertThat(driver.findElement(By.linkText("Great! Return to menu")).getText())
                .as("Проверка появления надписи ")
                .isEqualTo("Great! Return to menu");
        driver.findElement(By.linkText("Great! Return to menu")).click();

        assertThat(driver.manage().getCookieNamed("select").getValue())
                .isEqualTo("done");
    }

    @AfterClass
    public void exitDriver() {
        driver.quit();
    }
}
