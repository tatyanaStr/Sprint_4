package ru.praktikum_services.qa_scooter.test;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import ru.praktikum_services.qa_scooter.pages.HomeScooterPage;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class MainPageTest {
    WebDriver driver;

    @Before
    public void setUpChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private final String QUESTION;
    private final String ANSWER;

    public MainPageTest(String question, String answer) {
        this.QUESTION = question;
        this.ANSWER = answer;
    }

    @Parameterized.Parameters
    public static Object[][] getAccordeonItems() {
        return new Object[][]{
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я живу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}

        };
    }

    @Test
    public void checkAccordeonQuestions() {
        //driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        HomeScooterPage scooterPage = new HomeScooterPage(driver);
        scooterPage.waitForLoadPage();
        scooterPage.scrollToElement(0);
        String answerActual = null;
        for (int i = 0; i <= 7; i++) {
            var questionActual = scooterPage.getAccordionQuestion(i);
            if (questionActual.equals(QUESTION)) {

                answerActual = scooterPage.getAccordionAnswer(i);
                break;
            }

        }
        MatcherAssert.assertThat("Ошибка: Текст ответа не соответствует вопросу", answerActual, containsString(ANSWER));

    }

    @After
    public void teardown() {
        driver.quit();
    }
}

