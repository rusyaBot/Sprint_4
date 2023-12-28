import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuestionsList {
    WebDriver driver;

    public QuestionsList(WebDriver driver) {
        this.driver = driver;
    }

    //0) Сколько это стоит? И как оплатить?                  | Сутки — 400 рублей. Оплата курьеру — наличными или картой.
    //1) Хочу сразу несколько самокатов! Так можно?          | Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.
    //2) Как рассчитывается время аренды?                    | Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.
    //3) Можно ли заказать самокат прямо на сегодня?         | Только начиная с завтрашнего дня. Но скоро станем расторопнее.
    //4) Можно ли продлить заказ или вернуть самокат раньше? | Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.
    //5) Вы привозите зарядку вместе с самокатом?            | Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.
    //6) Можно ли отменить заказ?                            | Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.
    //7) Я жизу за МКАДом, привезёте?                        | Да, обязательно. Всем самокатов! И Москве, и Московской области.

    // Скролим до "Вопросы о Важном"
    public void scrollToQuestions(){
        WebElement element = driver.findElement(By.id("accordion__heading-0"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public String getCheckingQuestion0() {
        driver.findElement(By.id("accordion__heading-0")).click();                       // Открыли вопрос
        String textquestion = driver.findElement(By.id("accordion__panel-0")).getText(); // Получили текст ответа
        System.out.println("Текущий ответ: " + textquestion);                            // Вывод полученного текста для логирования
        return textquestion;
    }
    public String getCheckingQuestion1() {
        driver.findElement(By.id("accordion__heading-1")).click();                       // Открыли вопрос
        String textquestion = driver.findElement(By.id("accordion__panel-1")).getText(); // Получили текст ответа
        System.out.println("Текущий ответ: " + textquestion);                            // Вывод полученного текста для логирования
        return textquestion;
    }
    public String getCheckingQuestion2() {
        driver.findElement(By.id("accordion__heading-2")).click();                       // Открыли вопрос
        String textquestion = driver.findElement(By.id("accordion__panel-2")).getText(); // Получили текст ответа
        System.out.println("Текущий ответ: " + textquestion);                            // Вывод полученного текста для логирования
        return textquestion;
    }
    public String getCheckingQuestion3() {
        driver.findElement(By.id("accordion__heading-3")).click();                       // Открыли вопрос
        String textquestion = driver.findElement(By.id("accordion__panel-3")).getText(); // Получили текст ответа
        System.out.println("Текущий ответ: " + textquestion);                            // Вывод полученного текста для логирования
        return textquestion;
    }
    public String getCheckingQuestion4() {
        driver.findElement(By.id("accordion__heading-4")).click();                       // Открыли вопрос
        String textquestion = driver.findElement(By.id("accordion__panel-4")).getText(); // Получили текст ответа
        System.out.println("Текущий ответ: " + textquestion);                            // Вывод полученного текста для логирования
        return textquestion;
    }
    public String getCheckingQuestion5() {
        driver.findElement(By.id("accordion__heading-5")).click();                       // Открыли вопрос
        String textquestion = driver.findElement(By.id("accordion__panel-5")).getText(); // Получили текст ответа
        System.out.println("Текущий ответ: " + textquestion);                            // Вывод полученного текста для логирования
        return textquestion;
    }
    public String getCheckingQuestion6() {
        driver.findElement(By.id("accordion__heading-6")).click();                       // Открыли вопрос
        String textquestion = driver.findElement(By.id("accordion__panel-6")).getText(); // Получили текст ответа
        System.out.println("Текущий ответ: " + textquestion);                            // Вывод полученного текста для логирования
        return textquestion;
    }
    public String getCheckingQuestion7() {
        driver.findElement(By.id("accordion__heading-7")).click();                       // Открыли вопрос
        String textquestion = driver.findElement(By.id("accordion__panel-7")).getText(); // Получили текст ответа
        System.out.println("Текущий ответ: " + textquestion);                            // Вывод полученного текста для логирования
        return textquestion;
    }
}
