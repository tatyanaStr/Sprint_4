package ya.samokatPageObj;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OrderSamokatPage {

    private WebDriver driver;

    //Заголовок главной страницы
    private By headerMainPage = By.xpath("//div[contains(text(), \"Самокат\")]");

    // Кнопка Заказать в хэдере
    private By orderFormHeaderButton = By.xpath(".//div[contains(@class, 'Header')]//button[contains(text(), 'Заказать')]");

    //Кнопка Заказать в теле страницы
    private By orderFormBodyButton = By.xpath(".//div[contains(@class, 'Home_RoadMap')]//button[contains(text(), 'Заказать')]");
    // Заголовок Для кого самокат
    private By headerFirstPage = By.xpath(".//div[contains(text(), 'Для кого самокат')]");
    // Инпут Имя
    private By firstNameField = By.xpath(".//input[contains(@placeholder, 'Имя')]");
    // Инпут Фамилия
    private By secondNameField = By.xpath(".//input[contains(@placeholder, 'Фамилия')]");
    // Инпут Адрес
    private By adressField = By.xpath(".//input[contains(@placeholder, 'Адрес')]");
    // Селект Метро
    private By metroSelect = By.xpath(".//input[contains(@placeholder, 'Станция метро')]");

    // Выбор станции метро
    private By metroStation = By.xpath(".//div[@class ='select-search__select']//li[4]");
    // Инпут Телефон
    private By phoneNumperField = By.xpath(".//input[contains(@placeholder, 'Телефон')]");
    // Кнопка Далее
    private By nextButton = By.xpath(".//button[contains(text(), 'Далее')]");
    // Заголовок Про аренду
    private By headerSecondPage = By.xpath(".//div[contains(text(), 'Про аренду')]");
    // Datepicker
    private By datepickerField = By.xpath(".//input[contains(@placeholder, 'Когда привезти самокат')]");
    // Селект Срок аренды
    private By durationField = By.xpath(".//div[@class ='Dropdown-placeholder']");
    //Значение длительности
    private By durationValue = By.xpath(".//div[@class = 'Dropdown-menu']//div[1]");
    // Кнопка заказать
    private By orderButton = By.xpath(".//div[contains(@class,'Order')]//button[contains(text(), 'Заказать')]");

    //Заголовок поп-апа подтверждения оформления заказа
    private By popupHeader = By.xpath("//div[contains(text(), 'Хотите оформить заказ?')]");
    // Кнопка Да в поп-апе
    private By popupConfirmButton = By.xpath("//button[contains(text(), 'Да')]");
    // Заголовок поп-апа Заказ оформлен
    private By popupOrderHeader = By.xpath("//div[contains(text(), 'Заказ оформлен')]");

    public void waitForLoadPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(headerMainPage));
    }

    public OrderSamokatPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadFirstOrderPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(headerFirstPage));
    }

    public void waitForLoadSecondOrderPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(headerSecondPage));
    }

    public void scrollToElement() {

        WebElement element = driver.findElement(orderFormBodyButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

    }

    public void openOrderFormHeader() {
        driver.findElement(orderFormHeaderButton).click();
    }

    public void openOrderFormBody() {
        driver.findElement(orderFormBodyButton).click();
    }

    public void fillFirstNameField(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
    }

    public void fillSecondNameField(String secondName) {

        driver.findElement(secondNameField).sendKeys(secondName);
    }

    public void fillAdressField(String adress) {

        driver.findElement(adressField).sendKeys(adress);
    }

    public void fillPhoneNumberField(String phoneNumber) {

        driver.findElement(phoneNumperField).sendKeys(phoneNumber);
    }

    public void fillMetroField() {

        driver.findElement(metroSelect).click();
        driver.findElement(metroStation).click();
    }

    public void clickNextButton() {

        driver.findElement(nextButton).click();
    }

    public void fillDatepicker(String date) {

        driver.findElement(datepickerField).sendKeys(date);
        driver.findElement(datepickerField).sendKeys(Keys.RETURN);
    }

    public void selectDuration() {
        driver.findElement(durationField).click();
        driver.findElement(durationValue).click();
    }

    public void clickOrderButton() {

        driver.findElement(orderButton).click();
    }

    public void waitForButtonClickable() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.elementToBeClickable(orderButton));
    }

    public void waitForOpenConfirmPopup() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(popupHeader));
    }

    public void clickConfirmButton() {
        driver.findElement(popupConfirmButton).click();
    }

    public void waitForOpenOrderPopup() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(popupOrderHeader));
    }

    public String getPopupHeader() {
        return driver.findElement(popupOrderHeader).getText();
    }

}
