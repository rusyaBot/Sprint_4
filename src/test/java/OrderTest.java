import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

// Тесты без параметризации
public class OrderTest {
    WebDriver driver;

    @Before
    public void setUp() { //Используем менеджер для простой и удобной подготовки драйверов
//        WebDriverManager.chromedriver().setup(); //Драйвер для chrome
//        driver = new ChromeDriver();
        WebDriverManager.firefoxdriver().setup(); // Драйвер для firefox
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
        // Шаг. Согласие куки
        ConsentToCookies consentToCookies = new ConsentToCookies(driver);
        consentToCookies.closecookiesButton();
    }

    // Создание заказа кнопка нижняя "Заказать". Проверка невалидного номера заказа
    @Test
    public void InvalidOrderNumber() {
        // Шаг1. Создание заказа, заполнение всех полей
        OrderPage orderPage = new OrderPage(driver);            //Подключил класс OrderPage
        orderPage.orderButtonBottom();                          // Перешли по нижней кнопке "заказать"
        orderPage.setOrderPage1("Кот", "Пес", "Нирбург", "89257777777"); // Внесли данные "Для кого самокат"
        orderPage.setOrderPage2("Чистый самокат");
        // Шаг2. Сохранение номера заказа, проверка корректности
        OrderStatus orderStatus = new OrderStatus(driver);   // Подключил класс orderStatus
        orderStatus.getCheckNewOrder();                      // Проверка оформлен ли заказ
        String orderNumber = orderStatus.getSavedOrder();    // Сохранили номер заказа в буфер для поиска
        orderNumber = orderNumber + "invalid";           // Изменили на не валидный заказ
        System.out.println("Вносим не валидный номер, чтобы не нашёл заказ: " + orderNumber); // Доп. логирование
        orderStatus.viewStatusOrder(orderNumber);            // Перешли в проверку заказа через поиск
        orderStatus.viewNotfound();
    }

    @After
    public void tearDown() {
        // Закрыть браузер
        driver.quit();
    }
}
