import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class QuestionsListTest {

    private final int question;
    private final String answerExpected;

    public QuestionsListTest(int question, String answerExpected) {
        this.question = question;
        this.answerExpected = answerExpected;
    }

        @Parameterized.Parameters
        public static Object[][] getQuestions () {
            //Тестовые данные
            return new Object[][]{
                    {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                    {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                    {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                    {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                    {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                    {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                    {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                    {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
            };
        }
        WebDriver driver;

        @Before
        public void setUp () { //Используем менеджер для удобной подготовки драйверов
        WebDriverManager.chromedriver().setup(); //Драйвер для chrome
        driver = new ChromeDriver();
//            WebDriverManager.firefoxdriver().setup(); // Драйвер для firefox
//            driver = new FirefoxDriver();
            driver.get("https://qa-scooter.praktikum-services.ru"); // Переход на сайт по заказу самоката
            // Согласие куки
            ConsentToCookies consentToCookies = new ConsentToCookies(driver);
            consentToCookies.closecookiesButton();
        }

        @Test // Проверка на соответствие тескта в ответах
        public void CheckQuestions () {
            QuestionsList questionsList = new QuestionsList(driver);
            questionsList.scrollToQuestions();
            // Достали фактические тексты ответов
            String questionActual = questionsList.getCheckingQuestion(question);
            // Сравниваем ожидаемый с фактическим
            assertEquals(answerExpected, questionActual);

        }

        @After
        public void tearDown () {
            // Закрыть браузер
            driver.quit();
        }
    }
