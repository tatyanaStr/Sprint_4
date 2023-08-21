package ru.praktikum_services.qa_scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomeScooterPage {

    private WebDriver driver;

    // Вопросы в аккордеоне
    private By AccordionQuestion(int i) {

        return By.id("accordion__heading-" + i);
    }

    // Ответы в аккордеоне
    private By AccordionAnswer(int i) {

        return By.id("accordion__panel-" + i);
    }

    // Заголовок главной страницы
    private By headerMainPage = By.xpath("//div[contains(text(), 'Самокат')]");

    public HomeScooterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void scrollToElement(int i) {

        WebElement element = driver.findElement(AccordionQuestion(i));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);

    }

    public void waitForLoadPage() {

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(headerMainPage));

    }

    public String getAccordionQuestion(int i) {

        return driver.findElement(AccordionQuestion(i)).getText();

    }

    public String getAccordionAnswer(int i) {

        driver.findElement(AccordionQuestion(i)).click();
        waitForAccordeonOpen(i);
        return driver.findElement(AccordionAnswer(i)).getText();

    }

    public void waitForAccordeonOpen(int i) {

        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(AccordionAnswer(i)));
    }

}
