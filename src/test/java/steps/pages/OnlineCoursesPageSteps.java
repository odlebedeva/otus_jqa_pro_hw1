package steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import pageobjects.pages.OnlineCoursesPage;

public class OnlineCoursesPageSteps {
  @Inject
  public OnlineCoursesPage onlineCoursesPage;

  @Пусть("Открыта страница подготовительных курсов")
  public void openPreparatoryPage() {
    onlineCoursesPage.openPage();
  }

  @Тогда("Найти самый дешёвый курс")
  public void findCheapestCourses() { onlineCoursesPage.findCheapestCourses(); }

  @И("Найти самый дорогой курс")
  public void findExpensiveCourses() { onlineCoursesPage.findExpensiveCourses(); }
}
