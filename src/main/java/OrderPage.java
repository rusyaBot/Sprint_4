import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderPage {

    //Локаторы
    // Начальная страница
    private final By orderButtonTopLocator = By.xpath(".//button[@class = 'Button_Button__ra12g']"); // кнопка Заказать Верхняя
    private final By orderButtonBottomLocator = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM']"); // кнопка Заказать Нижняя

    // Первая страница (Для кого самокат)
    private final By usernameField = By.xpath(".//input[@placeholder = '* Имя']");    // Имя
    private final By surnameField = By.xpath(".//input[@placeholder = '* Фамилия']"); // Фамилия
    private final By addressField = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']"); // Адрес
    private final By metroStationField = By.xpath(".//*[text()='Щёлковская']"); // Станция метро
    private final By telephoneField = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']"); // Телефон
    private final By nextButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Далее']"); // Кнопка далее

    // Вторая страница (Про аренду)
    private final By deliveryDateFieldClick = By.xpath(".//input[@placeholder = '* Когда привезти самокат']"); // Дата поставить курсор для выбора даты
    private final By deliveryDateField = By.xpath(".//*[@aria-label='Choose воскресенье, 14-е января 2024 г.']"); // Выбрать Дату доставки
    private final By periodField3 = By.xpath(".//div[@class = 'Dropdown-option' and text() = 'трое суток']"); // Срок аренды
    private final By colourBlackField = By.xpath(".//input[@id = 'black']"); // Цвет
    private final By commentField = By.xpath(".//input[@placeholder = 'Комментарий для курьера']"); // Комментарий
    private final By orderButtonInAboutRent = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']");  // Нажать на кнопку "Заказать"
    private final By ButtonYesInAboutRent = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");  // Нажать на кнопку "Да"


    // подключаем веб драйвер
    WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }


    // Метод. Переход в шаблон заполнения заказа через верхнюю кнопку "Заказать"
    public void orderButtonTop() {
        driver.findElement(orderButtonTopLocator).click();           // Жмем на кнопку Заказать
    }


    // Метод. Переход в шаблон заполнения заказа через нижнюю кнопку "Заказать"
    public void orderButtonBottom() {
        driver.findElement(orderButtonBottomLocator).click();        // Жмем на кнопку Заказать
    }


    // Метод. Заполняем первую страницу (Для кого самокат)
    public void setOrderPage1(String username, String surname, String address, String telephone) {
        driver.findElement(usernameField).sendKeys(username);        // Вводим имя
        driver.findElement(surnameField).sendKeys(surname);          // Вводим фамилию
        driver.findElement(addressField).sendKeys(address);          // Вводим адрес

        driver.findElement(By.className("select-search")).click();   // ставим курсор для выбора станции метро
        WebElement station = driver.findElement(metroStationField);  // Выбираем станцию
        station.click();                                             // Жмём на выбранную станцию

        driver.findElement(telephoneField).sendKeys(telephone);      // Вводим телефон
        driver.findElement(nextButton).click();                      // Нажатие на кнопку Далее
    }


    // Метод. Заполняем вторую страницу (Про аренду)
    public void setOrderPage2(String comment) {
//        Не полчилось :(
//        LocalDate date = LocalDate.now();                                        // Получить текущую дату
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy"); // Отформатировать
//        String text = date.format(formatter);                                    // Перевести дату в стрингу
//        driver.findElement(dateInput).sendKeys(text);                            // Подставить
//        driver.findElement(By.className("Order_Content__bmtHS")).click();        // Нажать в пустое место чтобы дата зафиксировалась. Дата сбрасывается на другой день

        driver.findElement(deliveryDateFieldClick).click();               // Поставить курсор в поле для ввода даты
        WebElement dateInput = driver.findElement(deliveryDateField);      // Найти нужную дату
        dateInput.click();                                                 // Выбрать дату
        driver.findElement(By.className("Dropdown-placeholder")).click();  // Поставить курсор в поле для выбора Срока аренды
        driver.findElement(periodField3).click();                          // Выбора Срока аренды
        driver.findElement(colourBlackField).click();                      // Выбора цвета самоката
        driver.findElement(commentField).sendKeys(comment);                // Комментарий для курьера
        driver.findElement(orderButtonInAboutRent).click();                // Нажать на кнопку "Заказать"
        driver.findElement(ButtonYesInAboutRent).click();                  // Нажать на кнопку "Да"
    }

}
