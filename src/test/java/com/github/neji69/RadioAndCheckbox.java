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
    }

    @AfterClass
    public void exitDriver() {
        driver.quit();
    }
}
