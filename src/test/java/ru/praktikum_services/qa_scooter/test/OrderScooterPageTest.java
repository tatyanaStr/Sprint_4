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
import ru.praktikum_services.qa_scooter.pages.OrderScooterPage;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class OrderScooterPageTest {
    WebDriver driver;

    @Before
    public void setUpChrome() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    private final String FIRST_NAME;
    private final String SECOND_NAME;
    private final String ADRESS;
    private final String PHONE_NUMBER;
    private final String DATEPICKER;

    public OrderScooterPageTest(String firstName, String secondName, String adress, String phoneNumber, String datepicker) {
        this.FIRST_NAME = firstName;
        this.SECOND_NAME = secondName;
        this.ADRESS = adress;
        this.PHONE_NUMBER = phoneNumber;
        this.DATEPICKER = datepicker;
    }

    @Parameterized.Parameters
    public static Object[][] getAccordeonItems() {
        return new Object[][]{
                {"Иван", "Иванов", "Москва", "88005553535", "30.01.2023"},
                {"Ivan", "Ivanov", "Moscow", "88005553535", "30.01.2023"}


        };
    }

    @Test
    public void checkOrderFromHeaderButton(){
        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderScooterPage orderPage = new OrderScooterPage(driver);
        orderPage.clickOrderButtonFromHeader();
        orderPage.fillFirstPageOrderForm(FIRST_NAME, SECOND_NAME, ADRESS, PHONE_NUMBER);
        orderPage.clickNextButton();
        orderPage.fillSecondPageOrderForm(DATEPICKER);
        orderPage.makeOrder();
        orderPage.confirmOrder();


        MatcherAssert.assertThat(orderPage.getPopupHeader(), containsString("Заказ оформлен"));
    }

    @Test
    public void checkOrderFromBodyButton(){
        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderScooterPage orderPage = new OrderScooterPage(driver);
        orderPage.clickOrderButtonFromBody();
        orderPage.fillFirstPageOrderForm(FIRST_NAME, SECOND_NAME, ADRESS, PHONE_NUMBER);
        orderPage.clickNextButton();
        orderPage.fillSecondPageOrderForm(DATEPICKER);
        orderPage.makeOrder();
        orderPage.confirmOrder();


        MatcherAssert.assertThat("Ошибка: заказ не был оформлен", orderPage.getPopupHeader(), containsString("Заказ оформлен"));
    }


    @After
    public void teardown() {
        driver.quit();
    }
}
