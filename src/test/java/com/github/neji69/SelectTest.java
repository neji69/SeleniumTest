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

        seleclanguages.selectByIndex(index1); // Есть ли разница обращаться к множественному выбору по имени или индексу?
        seleclanguages.selectByIndex(index2);
        driver.findElement(By.id("go")).click();
        List<WebElement> elementNameResult = driver.findElements(By.name("result"));

        assertThat(elementNameResult.get(1).getText()) //как находить правильно второй одинаковый элемент,пришлось использовать икспатч
                .as("Проверка выбора языков программирования ")
                .isEqualTo(progLang.get(index1) + ", " + progLang.get(index2)); // очень большие сомнения про запятую. это явно костыль.
        // но как подругому не пойму. А если надо будет проверить 3 значения
        // надо будет еще одну запятую рисовать? Или раз мы знаем что должно
        // получиться  просто и задать этот результа? А как же автоматизация?7??7

        // еще можешь потом показать как использовать логические условия типа и или в асертдж

        assertThat(driver.findElement(By.linkText("Great! Return to menu")).getText())
                .as("Проверка появления надписи ")
                .isEqualTo("Great! Return to menu"); //как сделать, если это правда, то кликнуть на нее?
        driver.findElement(By.linkText("Great! Return to menu")).click();
    }

    @AfterClass
    public void exitDriver() {
        driver.quit();
    }
}