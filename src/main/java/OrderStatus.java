import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class OrderStatus {

    // Локаторы:
    private final By newOrderNumber = By.className("Order_ModalHeader__3FDaJ"); // Попап "Заказ оформлен"
    private final By numberOrder = By.className("Order_Text__2broi");          // Текст из попапа с номером заказа
    private final By viewStatusButton = By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Посмотреть статус']"); // Кнопка "Посмотреть статус"
    private final By clickStatusButton = By.xpath(".//button[@class = 'Header_Link__1TAG7' and text() = 'Статус заказа']"); // Кнопка "Статус заказа"
    private final By enterOrderNumber = By.xpath(".//input[@class = 'Input_Input__1iN_Z Header_Input__xIoUq']"); // Ввести номер заказа из буфера
    private final By goButton = By.xpath(".//button[@class = 'Button_Button__ra12g Header_Button__28dPO' and text() = 'Go!']"); // Кнопка Go
    private final By viewNotfound = By.xpath(".//div[@class ='Track_NotFound__6oaoY']"); // Попап не нашёлся заказ

    // Поля для проверки данных
    private final By usernameField = By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=1]/div[@class = 'Track_Value__15eEX']"); // Имя
    private final By surnameField = By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=2]/div[@class = 'Track_Value__15eEX']"); // Фамилия
    private final By addressField = By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=3]/div[@class = 'Track_Value__15eEX']"); // Адрес
    private final By metroStationField = By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=4]/div[@class = 'Track_Value__15eEX']"); // Станция метро
    private final By telephoneField = By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=5]/div[@class = 'Track_Value__15eEX']"); // Телефон
    private final By deliveryDateField = By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=7]/div[@class = 'Track_Value__15eEX']"); // Дата доставки
    private final By periodField = By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=8]/div[@class = 'Track_Value__15eEX']"); // Срок аренды
    private final By colourField = By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=10]/div[@class = 'Track_Value__15eEX']"); // Цвет
    private final By commentField = By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=11]/div[@class = 'Track_Value__15eEX']"); // Комментарий

    // подключаем веб драйвер
    WebDriver driver;
    public OrderStatus(WebDriver driver) {
        this.driver = driver;
    }

    // Проверка оформлен ли заказ
    public void getCheckNewOrder() {
        String newOrder = driver.findElement(newOrderNumber).getText();
        MatcherAssert.assertThat(newOrder, containsString("Заказ оформлен"));
    }

    // Запомнить номер заказа
    public String getSavedOrder() {
        String orderNumberIn = driver.findElement(numberOrder).getText();   // Получили номер заказа
        System.out.println(orderNumberIn);                                  // Вывод номера заказа в логе идеи
        String orderNumber = orderNumberIn.substring(14, 20);               // Вырезать номер заказа, для дальнейшего использования
        System.out.println("Сохранили номер заказа: " + orderNumber);       // Вывести сохранённый номер заказа
        return orderNumber;
    }

    // Посмотреть статус сразу после создания
    public void viewStatusAfterTheFormation() {
        driver.findElement(viewStatusButton).click(); // Кнопка для перехода к карточке заказа. Кнопка "Посмотреть статус"
    }

    // Посмотреть статус через поиск по номеру
    public void viewStatusOrder(String orderNumber) {
        driver.get("https://qa-scooter.praktikum-services.ru");      // Переход на начальную страницу, для перехода через поиск по номеру заказа
        driver.findElement(clickStatusButton).click();               // Клик по кнопке "Статус заказа"
        driver.findElement(enterOrderNumber).sendKeys(orderNumber);  // Ввести номер заказа из буфера
        driver.findElement(goButton).click();                        // Клик по кнопке "Go" для поиска заказа
    }

    public void viewNotfound() {
        assertTrue(driver.findElements(viewNotfound).size() > 0); // Попап не нашёлся заказ
    }

    // Проверка верности сохранённого заказа
    public void checkOrder(String username, String surname, String address, String metroStation, String telephone, String deliveryDate, String period, String colour, String comment) {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        assertEquals("Проверка Имени", username, driver.findElement(usernameField).getText());
        assertEquals("Проверка Фамилии", surname, driver.findElement(surnameField).getText());
        assertEquals("Проверка Адрес", address, driver.findElement(addressField).getText());
        assertEquals("Проверка Станция метро", metroStation, driver.findElement(metroStationField).getText());
        assertEquals("Проверка Телефон", telephone, driver.findElement(telephoneField).getText());
        assertEquals("Проверка Дата доставки", deliveryDate, driver.findElement(deliveryDateField).getText());
        assertEquals("Проверка Срок аренды", period, driver.findElement(periodField).getText());
        assertEquals("Проверка Цвет", colour, driver.findElement(colourField).getText());
        assertEquals("Проверка Комментарий", comment, driver.findElement(commentField).getText());
    }
}