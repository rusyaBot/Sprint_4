import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static jdk.nashorn.internal.objects.NativeString.substring;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertEquals;

public class OrderStatus {
    // подключаем веб драйвер
    WebDriver driver;

    public OrderStatus(WebDriver driver) {
        this.driver = driver;
    }

    // Проверка оформлен ли заказ
    public void getCheckNewOrder() {
        String newOrder = driver.findElement(By.className("Order_ModalHeader__3FDaJ")).getText();
        MatcherAssert.assertThat(newOrder, containsString("Заказ оформлен"));
    }

    // Запомнить номер заказа
    public String getSavedOrder() {
        String orderNumberIn = driver.findElement(By.className("Order_Text__2broi")).getText(); // Получили номер заказа
        System.out.println(orderNumberIn);                                                      // Вывод номера заказа в логе идеи
        String orderNumber = substring(orderNumberIn, 14, 20);                       // Вырезать номер заказа, для дальнейшего использования
        System.out.println("Сохранили номер заказа: " + orderNumber);                           // Вывести сохранённый номер заказа
        return orderNumber;
    }

    // Посмотреть статус сразу после создания
    public void viewStatusAfterTheFormation() {
        driver.findElement(By.xpath(".//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Посмотреть статус']")).click(); // Кнопка для перехода к карточке заказа
    }

    // Посмотреть статус через поиск по номеру
    private final By emptyNumber = By.xpath(".//div[@class ='Track_NotFound__6oaoY']");
    public void viewStatusOrder(String orderNumber) {
        driver.get("https://qa-scooter.praktikum-services.ru"); // Переход на начальную страницу, для перехода через поиск по номеру заказа
        driver.findElement(By.xpath(".//button[@class = 'Header_Link__1TAG7' and text() = 'Статус заказа']")).click();      // Клик по кнопке "Статус заказа"
        driver.findElement(By.xpath(".//input[@class = 'Input_Input__1iN_Z Header_Input__xIoUq']")).sendKeys(orderNumber);  // Ввести номер заказа из буфера
        driver.findElement(By.xpath(".//button[@class = 'Button_Button__ra12g Header_Button__28dPO' and text() = 'Go!']")).click(); // Клик по кнопке "Go" для поиска заказа
        assertEquals(true, driver.findElements(emptyNumber).size() > 0); // Проверь, что нашёлся хотя бы один нужный элемент
    }


    // Проверка верности сохранённого заказа
    public void checkOrder(String username, String surname, String address, String metroStation, String telephone, String deliveryDate, String period, String colour, String comment) {
        assertEquals("Проверка Имени", username, driver.findElement(By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=1]/div[@class = 'Track_Value__15eEX']")).getText());
        assertEquals("Проверка Фамилии", surname, driver.findElement(By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=2]/div[@class = 'Track_Value__15eEX']")).getText());
        assertEquals("Проверка Адрес", address, driver.findElement(By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=3]/div[@class = 'Track_Value__15eEX']")).getText());
        assertEquals("Проверка Станция метро", metroStation, driver.findElement(By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=4]/div[@class = 'Track_Value__15eEX']")).getText());
        assertEquals("Проверка Телефон", telephone, driver.findElement(By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=5]/div[@class = 'Track_Value__15eEX']")).getText());
        assertEquals("Проверка Дата доставки", deliveryDate, driver.findElement(By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=7]/div[@class = 'Track_Value__15eEX']")).getText());
        assertEquals("Проверка Срок аренды", period, driver.findElement(By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=8]/div[@class = 'Track_Value__15eEX']")).getText());
        assertEquals("Проверка Цвет", colour, driver.findElement(By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=10]/div[@class = 'Track_Value__15eEX']")).getText());
        assertEquals("Проверка Комментарий", comment, driver.findElement(By.xpath(".//div[@class = 'Track_OrderInfo__2fpDL']/div[position()=11]/div[@class = 'Track_Value__15eEX']")).getText());
    }
}
