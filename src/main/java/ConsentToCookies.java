import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConsentToCookies {   // Соглашаемся с куки
    private final By cookiesButton = By.xpath(".//button[@id = 'rcc-confirm-button']"); // Локатор кнопки с куки
    WebDriver driver;
    public ConsentToCookies(WebDriver driver) {
        this.driver = driver;
    }


    public void closecookiesButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(cookiesButton));
        driver.findElement(cookiesButton).click();  // Кликаем на Соглашаемся с куки
    }
}
