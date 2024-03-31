package steps.pages;

import com.google.inject.Inject;
import io.cucumber.java.ru.Тогда;
import pageobjects.pages.CourseCardPage;

public class CourseCardPageSteps {
  @Inject
  public CourseCardPage courseCardPage;

  @Тогда("На странице отображается его название и описание")
  public void checkNameAndDescription() {courseCardPage.checkCourseNameAndDescriptionData();}
}