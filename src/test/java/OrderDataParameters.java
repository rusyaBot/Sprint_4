import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


@RunWith(Parameterized.class)
public class OrderDataParameters {
    //Добавь необходимые поля
    private final String username;
    private final String surname;
    private final String address;
    private final String telephone;
    private final String invalid;

    public OrderDataParameters(String username, String surname, String address, String telephone, String invalid) {
        this.username = username;
        this.surname = surname;
        this.address = address;
        this.telephone = telephone;
        this.invalid = invalid;
    }

    @Parameterized.Parameters
    public static Object[][] getUserData() {
        //Тестовые данные
        return new Object[][]{
                {"Гарри", "Поттер", "Хогворд", "+79257777777", "invalid"},
                {"Кот", "Пес", "Нирбург", "+89257777777", "invalid"},
        };
    }

    WebDriver driver;

    @Before
    public void setUp() { //Менеджер для подготовки драйверов
//        WebDriverManager.chromedriver().setup(); //Драйвер для chrome
//        driver = new ChromeDriver();
        WebDriverManager.firefoxdriver().setup(); // Драйвер для firefox
        driver = new FirefoxDriver();
    }

    @Test
    public void newOrderScooterAndButtonTop2() {
        driver.get("https://qa-scooter.praktikum-services.ru");
        // Шаг1. Согласие куки
        СonsentToCookies сonsentToCookies = new СonsentToCookies(driver);
        сonsentToCookies.closecookiesButton();
        // Шаг2. Создание заказа, заполнение всех полей
        OrderPage orderPage = new OrderPage(driver);         // Подключил класс OrderPage
        orderPage.orderButtonTop();                          // Перешли по верхней кнопке "заказать"
        orderPage.setOrderPage1(username, surname, address, telephone); // Внесли данные "Для кого самокат"
        orderPage.setOrderPage2("Чистый самокат");
        // Шаг3. Сохранение номера заказа, проверка корректности
        OrderStatus orderStatus = new OrderStatus(driver);   // Подключил класс orderStatus
        orderStatus.getCheckNewOrder();                      // Проверка оформлен ли заказ
        orderStatus.getSavedOrder();                         // Сохранили номер заказа
        orderStatus.viewStatusAfterTheFormation();           // Перешли в проверку заказа
        // Проверка карточки заказа по тестовым данным
        orderStatus.checkOrder(username, surname, address, "Щёлковская", telephone, "3 января", "трое суток", "чёрный жемчуг", "Чистый самокат");
    }

    // Создание заказа кнопка2 "Заказать". Проверка карточки заказа через поиск
    @Test
    public void newOrderScooterAndButtonBottom2() {
        driver.get("https://qa-scooter.praktikum-services.ru");
        // Шаг1. Согласие куки
        СonsentToCookies сonsentToCookies = new СonsentToCookies(driver);
        сonsentToCookies.closecookiesButton();
        // Шаг2. Создание заказа, заполнение всех полей
        OrderPage orderPage = new OrderPage(driver);            //Подключил класс OrderPage
        orderPage.orderButtonBottom();                          // Перешли по нижней кнопке "заказать"
        orderPage.setOrderPage1(username, surname, address, telephone); // Внесли данные "Для кого самокат"
        orderPage.setOrderPage2("Чистый самокат");
        // Шаг3. Сохранение номера заказа, проверка корректности
        OrderStatus orderStatus = new OrderStatus(driver);   // Подключил класс orderStatus
        orderStatus.getCheckNewOrder();                      // Проверка оформлен ли заказ
        String orderNumber = orderStatus.getSavedOrder();    // Сохранили номер заказа в буфер для поиска
        orderStatus.viewStatusOrder(orderNumber);            // Перешли в проверку заказа через поиск
        // Проверка карточки заказа по тестовым данным
        orderStatus.checkOrder(username, surname, address, "Щёлковская", telephone, "3 января", "трое суток", "чёрный жемчуг", "Чистый самокат");
    }

    // Создание заказа кнопка2 "Заказать". Проверка невалидного номера заказа
    @Test
    public void InvalidOrderNumber() {
        driver.get("https://qa-scooter.praktikum-services.ru");
        // Шаг1. Согласие куки
        СonsentToCookies сonsentToCookies = new СonsentToCookies(driver);
        сonsentToCookies.closecookiesButton();
        // Шаг2. Создание заказа, заполнение всех полей
        OrderPage orderPage = new OrderPage(driver);            //Подключил класс OrderPage
        orderPage.orderButtonBottom();                          // Перешли по нижней кнопке "заказать"
        orderPage.setOrderPage1(username, surname, address, telephone); // Внесли данные "Для кого самокат"
        orderPage.setOrderPage2("Чистый самокат");
        // Шаг3. Сохранение номера заказа, проверка корректности
        OrderStatus orderStatus = new OrderStatus(driver);   // Подключил класс orderStatus
        orderStatus.getCheckNewOrder();                      // Проверка оформлен ли заказ
        String orderNumber = orderStatus.getSavedOrder();    // Сохранили номер заказа в буфер для поиска
        orderNumber = orderNumber + "invalid";           // Изменили на не валидный заказ
        System.out.println("Вносим не валидный номер, чтобы не нашёл заказ: " + orderNumber); // Доп. логирование
        orderStatus.viewStatusOrder(orderNumber);            // Перешли в проверку заказа через поиск
    }

    @After
    public void tearDown() {
        // Закрыть браузер
        driver.quit();
    }
}


