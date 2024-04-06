package steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pageobjects.pages.MainPage;

public class MainPageSteps {

  @Inject
  public MainPage mainPage;

  @Пусть("Открыта главная страница")
  public void openPage() {
    mainPage.openPage();
  }

  @Если("Найден и открыт курс с названием {string}")
  public void findAndOpen(String courseName) {mainPage.findAndOpenCourseByName(courseName);}

  @Тогда("Открыть самый ранний курс и проверить дату")
  public void openEarliestCourseAndCheckDate() {
    mainPage.choiceEarliestOrLatestCourse(true)
            .checkEarliestOrLatestCourseDateOnPage();
  }

  @Тогда("Открыть самый поздний курс и проверить дату")
  public void openLatestCourseAndCheckDate() {
    mainPage.choiceEarliestOrLatestCourse(false)
            .checkEarliestOrLatestCourseDateOnPage();
  }

  @Тогда("Найти курс, стартующий не раньше даты {string}")
  public void findCourseInSpecifiedDateOrLater(String courseDate) {
    mainPage.findCourseInSpecifiedDateOrLater(courseDate);
  }
}