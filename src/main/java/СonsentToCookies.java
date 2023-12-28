import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class СonsentToCookies {   // Соглашаемся с куки
    WebDriver driver;
    public СonsentToCookies(WebDriver driver) {
        this.driver = driver;
    }
    public void closecookiesButton(){
        driver.findElement(By.xpath(".//button[@id = 'rcc-confirm-button']")).click();  // Кликаем на Соглашаемся с куки
    }
}
