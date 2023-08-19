package ya.test;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ya.samokatPageObj.OrderSamokatPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class OrderSamokatPageTest {
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

    public OrderSamokatPageTest(String firstName, String secondName, String adress, String phoneNumber, String datepicker) {
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

        OrderSamokatPage orderPage = new OrderSamokatPage(driver);
        orderPage.waitForLoadPage();
        orderPage.openOrderFormHeader();
        orderPage.waitForLoadFirstOrderPage();
        orderPage.fillFirstNameField(FIRST_NAME);
        orderPage.fillSecondNameField(SECOND_NAME);
        orderPage.fillAdressField(ADRESS);
        orderPage.fillMetroField();
        orderPage.fillPhoneNumberField(PHONE_NUMBER);
        orderPage.clickNextButton();
        orderPage.waitForLoadSecondOrderPage();
        orderPage.fillDatepicker(DATEPICKER);
        orderPage.selectDuration();
        orderPage.waitForButtonClickable();
        orderPage.clickOrderButton();
        orderPage.waitForOpenConfirmPopup();
        orderPage.clickConfirmButton();
        orderPage.waitForOpenOrderPopup();


        MatcherAssert.assertThat(orderPage.getPopupHeader(), containsString("Заказ оформлен"));
    }

    @Test
    public void checkOrderFromBodyButton(){
        driver.get("https://qa-scooter.praktikum-services.ru/");

        OrderSamokatPage orderPage = new OrderSamokatPage(driver);
        orderPage.waitForLoadPage();
        orderPage.scrollToElement();
        orderPage.openOrderFormBody();
        orderPage.waitForLoadFirstOrderPage();
        orderPage.fillFirstNameField(FIRST_NAME);
        orderPage.fillSecondNameField(SECOND_NAME);
        orderPage.fillAdressField(ADRESS);
        orderPage.fillMetroField();
        orderPage.fillPhoneNumberField(PHONE_NUMBER);
        orderPage.clickNextButton();
        orderPage.waitForLoadSecondOrderPage();
        orderPage.fillDatepicker(DATEPICKER);
        orderPage.selectDuration();
        orderPage.waitForButtonClickable();
        orderPage.clickOrderButton();
        orderPage.waitForOpenConfirmPopup();
        orderPage.clickConfirmButton();
        orderPage.waitForOpenOrderPopup();


        MatcherAssert.assertThat("Ошибка: заказ не был оформлен", orderPage.getPopupHeader(), containsString("Заказ оформлен"));
    }


    @After
    public void teardown() {
        driver.quit();
    }
}
