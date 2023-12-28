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
    }

    // Создание заказа кнопку1 "Заказать". Проверка карточки заказа сразу после создания
    @Test
    public void newOrderScooterAndButtonTop() {
        driver.get("https://qa-scooter.praktikum-services.ru");
        // Шаг1. Согласие куки
        СonsentToCookies сonsentToCookies = new СonsentToCookies(driver);
        сonsentToCookies.closecookiesButton();
        // Шаг2. Создание заказа, заполнение всех полей
        OrderPage orderPage = new OrderPage(driver);         // Подключил класс OrderPage
        orderPage.orderButtonTop();                          // Перешли по верхней кнопке "заказать"
        orderPage.setOrderPage1("Гарри", "Поттер", "Хогворд", "+79257777777"); // Внесли данные "Для кого самокат"
        orderPage.setOrderPage2("Чистый самокат");
        // Шаг3. Сохранение номера заказа, проверка корректности
        OrderStatus orderStatus = new OrderStatus(driver);   // Подключил класс orderStatus
        orderStatus.getCheckNewOrder();                      // Проверка оформлен ли заказ
        orderStatus.getSavedOrder();                         // Сохранили номер заказа
        orderStatus.viewStatusAfterTheFormation();           // Перешли в проверку заказа
        // Проверка карточки заказа по тестовым данным
        orderStatus.checkOrder("Гарри", "Поттер", "Хогворд", "Щёлковская", "+79257777777", "3 января", "трое суток", "чёрный жемчуг", "Чистый самокат");
    }

    // Создание заказа кнопка2 "Заказать". Проверка карточки заказа через поиск
    @Test
    public void newOrderScooterAndButtonBottom() {
        driver.get("https://qa-scooter.praktikum-services.ru");
        // Шаг1. Согласие куки
        СonsentToCookies сonsentToCookies = new СonsentToCookies(driver);
        сonsentToCookies.closecookiesButton();
        // Шаг2. Создание заказа, заполнение всех полей
        OrderPage orderPage = new OrderPage(driver);            //Подключил класс OrderPage
        orderPage.orderButtonBottom();                          // Перешли по нижней кнопке "заказать"
        orderPage.setOrderPage1("Кот", "Пес", "Нирбург", "89257777777"); // Внесли данные "Для кого самокат"
        orderPage.setOrderPage2("Чистый самокат");
        // Шаг3. Сохранение номера заказа, проверка корректности
        OrderStatus orderStatus = new OrderStatus(driver);   // Подключил класс orderStatus
        orderStatus.getCheckNewOrder();                      // Проверка оформлен ли заказ
        String orderNumber = orderStatus.getSavedOrder();    // Сохранили номер заказа в буфер для поиска
        orderStatus.viewStatusOrder(orderNumber);            // Перешли в проверку заказа через поиск
        // Проверка карточки заказа по тестовым данным
        orderStatus.checkOrder("Кот", "Пес", "Нирбург", "Щёлковская", "89257777777", "3 января", "трое суток", "чёрный жемчуг", "Чистый самокат");
    }
    @After
    public void tearDown() {
        // Закрыть браузер
        driver.quit();
    }
}
