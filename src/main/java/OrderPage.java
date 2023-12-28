import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {
    // подключаем веб драйвер
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    // Метод. Переход в шаблон заполнения заказа через верхнюю кнопку "Заказать"
    public void orderButtonTop() {
        driver.findElement(By.xpath(".//button[@class = 'Button_Button__ra12g']")).click();   // Жмем на кнопку Заказать
    }


    // Метод. Переход в шаблон заполнения заказа через нижнюю кнопку "Заказать"
    public void orderButtonBottom() {
        driver.findElement(By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']")).click();   // Жмем на кнопку Заказать
    }


    // Метод. Заполняем первую страницу (Для кого самокат)
    public void setOrderPage1(String username, String surname, String address, String telephone) {
        driver.findElement(By.xpath(".//input[@placeholder = '* Имя']")).sendKeys(username);      // Вводим имя
        driver.findElement(By.xpath(".//input[@placeholder = '* Фамилия']")).sendKeys(surname);   // Вводим фамилию
        driver.findElement(By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']")).sendKeys(address);   // Вводим адрес

        driver.findElement(By.className("select-search")).click();                                      // ставим курсор для выбора станции метро
        WebElement station = driver.findElement(By.xpath(".//*[text()='Щёлковская']"));  // Выбираем станцию
        station.click();                                                                                // Жмём на выбранную станцию

        driver.findElement(By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']")).sendKeys(telephone);             // Вводим телефон
        driver.findElement(By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Далее']")).click();  // Нажатие на кнопку Далее
    }


    // Метод. Заполняем вторую страницу (Про аренду)
    public void setOrderPage2(String comment) {
//        Не полчилось :(
//        LocalDate date = LocalDate.now();                                        // Получить текущую дату
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // Отформатировать
//        String text = date.format(formatter);                                    // Перевести дату в стрингу
//        driver.findElement(dateInput).sendKeys(text);                            // Подставить
//        driver.findElement(By.className("Order_Content__bmtHS")).click();        // Нажать в пустое место чтобы дата зафиксировалась. Дата сбрасывается на другой день(

        driver.findElement(By.xpath(".//input[@placeholder = '* Когда привезти самокат']")).click();    // Поставить курсор в поле для ввода даты
        WebElement dateInput = driver.findElement(By.xpath(".//*[@aria-label='Choose воскресенье, 31-е декабря 2023 г.']")); // Найти нужную дату
        dateInput.click();    // Выбрать дату

        driver.findElement(By.className("Dropdown-placeholder")).click();          // Поставить курсор в поле для выбора Срока аренды
        driver.findElement(By.xpath(".//div[@class = 'Dropdown-option' and text() = 'трое суток']")).click();     // Выбора Срока аренды
        driver.findElement(By.xpath(".//input[@id = 'black']")).click();                                          // Выбора цвета самоката
        driver.findElement(By.xpath(".//input[@placeholder = 'Комментарий для курьера']")).sendKeys(comment);     // Комментарий для курьера
        driver.findElement(By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']")).click();  // Нажать на кнопку "Заказать"
        driver.findElement(By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']")).click();        // Нажать на кнопку "Да"
    }

}
