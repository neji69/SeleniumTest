package com.github.neji69;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;
import org.openqa.selenium.support.ui.Select;


import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestYandex {

    WebDriver driver;

    @BeforeTest
    public void setUp (){
        WebDriverManager.chromedriver().arch32().setup();
        driver =new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);

    }
    //Отключил тест из первой домашки так как он уже проверен.
    @Test (enabled = false)
    public void testFirst ()  {

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

    //Внутри этого теста домашка номер 16 --->
    @Test
    public void testTwo (){
        //Проверяем условия на странице button

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

//проверяем условия чекбокса на странице "Checkbox and Radio"
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
                .as("проверка ресультата радиобокса")
                .isEqualTo(driver.findElement(By.id("radio_result")).getText());
        driver.findElement(By.linkText("Great! Return to menu")).click();  //Вопрос. По условию надо проверить появилась ли надпись Great! Return to menu
                                                                            //и после этого нажать на нее. Является ли мое действие
                                                                            // просто нажатия кнопки. Проверкой того, что она появилась?
                                                                            // ну тоесть если я могу на нее нажать значит она есть
                                                                            // это вроде логично. но верно ли ?

        // Вопрос номер 2. Есть ли смысл две отдельные страницы в нашем случае первая "https://savkk.github.io/selenium-practice/button/"
        // и вторая "https://savkk.github.io/selenium-practice/checkboxes/" , тестировать в потоках, как два независимых теста?
        // здесь этого не просят , но по сути, наверное, так и нужно ? да?
        //
        //И раз пошла такая пьянка, еще вопрос. Не понимаю про логирование. Что и где нужно логировать?
        //Вот в данном тесте что и где мне нужно было залогировать и почему?
        //
    }

    //Внутри этого теста домашка номер 16 --->
    @Test
    public void testThree () {
        //Проверяем условия на странице button

        driver.get("https://savkk.github.io/selenium-practice/");
        driver.findElement(By.id("select")).click();
        Select selectHero = new Select( driver.findElement(By.name("hero")));
        selectHero.selectByVisibleText("John von Neumann");
        List<String> progLang =new LinkedList<String>();
                //(Collections.singleton((driver.findElement(By.name("languages")).getText())));
        //System.out.println(progLang);
        String languages = (driver.findElement(By.name("languages")).getText());
        String[] arrSplit = languages.split("\n");
        for (int i=0; i < arrSplit.length; i++)
        {
            progLang.add(arrSplit[i]);
        }
        System.out.println(progLang.get(3));

        Select seleclanguages = new Select( driver.findElement(By.name("languages")));

        int index1 = 0; // используеться для выбора индекса из списка
        int index2 = 3; // используеться для выбора индекса из списка

        seleclanguages.deselectByIndex(index1); // Есть ли разница обращаться к множественному выбору по имени или индексу?
        seleclanguages.deselectByIndex(index2);
        driver.findElement(By.id("go")).click();
        //String s =driver.findElement(By.xpath("//div[@id='content']/label[4]")).getTagName();
        List<WebElement> elementNameResult = driver.findElements(By.name("result"));
        System.out.println (elementNameResult.get(1).getText());

        assertThat(elementNameResult.get(1)) //как находить правильно второй одинаковый элемент,пришлось использовать икспатч
                .as("Проверка выбора языков программирования ")
                .isEqualTo(progLang.get(index1)+", " + progLang.get(index2));
    }


        @AfterTest()
    public void exitDriver(){
        driver.quit();
    }
}
