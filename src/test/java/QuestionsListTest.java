import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class QuestionsListTest {
    WebDriver driver;

    @Before
    public void setUp() { //Используем менеджер для удобной подготовки драйверов
        WebDriverManager.chromedriver().setup(); //Драйвер для chrome
        driver = new ChromeDriver();
//        WebDriverManager.firefoxdriver().setup(); // Драйвер для firefox
//        driver = new FirefoxDriver();
    }

    @Test // Проверка на соответствие тескта в ответах
    public void CheckQuestions() {
        driver.get("https://qa-scooter.praktikum-services.ru");
        // Шаг1. Согласие куки
        СonsentToCookies сonsentToCookies = new СonsentToCookies(driver);
        сonsentToCookies.closecookiesButton();
        // Шаг2.
        QuestionsList questionsList = new QuestionsList(driver);
        questionsList.scrollToQuestions();

        // Наполнили массив ожидаемыми ответами
        String[] questionExpected = new String[8];
        questionExpected[0] = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
        questionExpected[1] = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
        questionExpected[2] = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
        questionExpected[3] = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
        questionExpected[4] = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
        questionExpected[5] = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
        questionExpected[6] = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
        questionExpected[7] = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";

        // Достали фактические тексты ответов
        String[] questionActual = new String[8];
        questionActual[0] = questionsList.getCheckingQuestion0();
        questionActual[1] = questionsList.getCheckingQuestion1();
        questionActual[2] = questionsList.getCheckingQuestion2();
        questionActual[3] = questionsList.getCheckingQuestion3();
        questionActual[4] = questionsList.getCheckingQuestion4();
        questionActual[5] = questionsList.getCheckingQuestion5();
        questionActual[6] = questionsList.getCheckingQuestion6();
        questionActual[7] = questionsList.getCheckingQuestion7();

       // Сравниваем ожидаемый с фактическим
        for (int i = 0; i < questionActual.length; i++) {
            assertEquals(questionExpected[i], questionActual[i]);
        }
    }

    @After
    public void tearDown() {
        // Закрыть браузер
        driver.quit();
    }
}
