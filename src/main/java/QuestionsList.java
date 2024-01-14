import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QuestionsList {

    // Локаторы - Вопросы
    private final By questions0 = By.id("accordion__heading-0");
    private final By questions1 = By.id("accordion__heading-1");
    private final By questions2 = By.id("accordion__heading-2");
    private final By questions3 = By.id("accordion__heading-3");
    private final By questions4 = By.id("accordion__heading-4");
    private final By questions5 = By.id("accordion__heading-5");
    private final By questions6 = By.id("accordion__heading-6");
    private final By questions7 = By.id("accordion__heading-7");
    // Ответы
    private final By answer0 = By.id("accordion__panel-0");
    private final By answer1 = By.id("accordion__panel-1");
    private final By answer2 = By.id("accordion__panel-2");
    private final By answer3 = By.id("accordion__panel-3");
    private final By answer4 = By.id("accordion__panel-4");
    private final By answer5 = By.id("accordion__panel-5");
    private final By answer6 = By.id("accordion__panel-6");
    private final By answer7 = By.id("accordion__panel-7");
    WebDriver driver;

    public QuestionsList(WebDriver driver) {
        this.driver = driver;
    }

    // Скролим до "Вопросы о Важном"
    public void scrollToQuestions() {
        WebElement element = driver.findElement(questions0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public String getCheckingQuestion(int question) {
        String[] questionActual = new String[8];
        // Через оператор switch
        switch (question) {
            case 0:
                driver.findElement(questions0).click();
                questionActual[0] = driver.findElement(answer0).getText();break;
            case 1:
            driver.findElement(questions1).click();
            questionActual[1] = driver.findElement(answer1).getText();break;
            case 2:
            driver.findElement(questions2).click();
            questionActual[2] = driver.findElement(answer2).getText();break;
            case 3:
            driver.findElement(questions3).click();
            questionActual[3] = driver.findElement(answer3).getText();break;
            case 4:
            driver.findElement(questions4).click();
            questionActual[4] = driver.findElement(answer4).getText();break;
            case 5:
            driver.findElement(questions5).click();
            questionActual[5] = driver.findElement(answer5).getText();break;
            case 6:
            driver.findElement(questions6).click();
            questionActual[6] = driver.findElement(answer6).getText();break;
            case 7:
            driver.findElement(questions7).click();
            questionActual[7] = driver.findElement(answer7).getText();break;
        }
          // Через оператор if
//        if (question == 0) {
//            driver.findElement(questions0).click();
//            questionActual[0] = driver.findElement(answer0).getText();
//        } else if (question == 1) {
//            driver.findElement(questions1).click();
//            questionActual[1] = driver.findElement(answer1).getText();
//        } else if (question == 2) {
//            driver.findElement(questions2).click();
//            questionActual[2] = driver.findElement(answer2).getText();
//        } else if (question == 3) {
//            driver.findElement(questions3).click();
//            questionActual[3] = driver.findElement(answer3).getText();
//        } else if (question == 4) {
//            driver.findElement(questions4).click();
//            questionActual[4] = driver.findElement(answer4).getText();
//        } else if (question == 5) {
//            driver.findElement(questions5).click();
//            questionActual[5] = driver.findElement(answer5).getText();
//        } else if (question == 6) {
//            driver.findElement(questions6).click();
//            questionActual[6] = driver.findElement(answer6).getText();
//        } else if (question == 7) {
//            driver.findElement(questions7).click();
//            questionActual[7] = driver.findElement(answer7).getText();
//        }

        // Получили текст ответа (логирование)
        System.out.println("Текущий ответ: " + questionActual[question]);
        return questionActual[question];
    }
}
