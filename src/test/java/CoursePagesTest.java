import annotations.Driver;
import extensions.DriverManagerExtension;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import pageobjects.pages.CourseCardPage;
import pageobjects.pages.MainPage;

@ExtendWith(DriverManagerExtension.class)
public class CoursePagesTest {

  @Driver
  private WebDriver driver;

  @Test
  @Description("Фильтр по названию курса")
  public void choiceCourseByNameTest() {
    new MainPage(driver)
            .openPage()
            .filterAndOpenCourseByName("Специализация QA Automation Engineer");
    new CourseCardPage(driver)
            .checkCourseNameAndDescriptionData();
  }

  @Test
  @Description("Выбор курса, стартующего раньше всех")
  public void choiceEarliestCourseAndCheckDateTest() {
    new MainPage(driver)
            .openPage()
            .choiceEarliestCourse()
            .checkEarliestCourseDateOnPage();
  }

  @Test
  @Description("Выбор курса, стартующего позже всех")
  public void choiceLatestCourseAndCheckDateTest() {
    new MainPage(driver)
            .openPage()
            .choiceLatestCourse()
            .checkLatestCourseDateOnPage();
  }
}