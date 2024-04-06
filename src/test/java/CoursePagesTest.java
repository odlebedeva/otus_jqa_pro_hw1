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
            .filterAndOpenCourseByName("Специализация Системный аналитик");
    new CourseCardPage(driver)
            .checkCourseNameAndDescriptionData();
  }

  @Test
  @Description("Выбор курса, стартующего раньше всех")
  public void choiceCourseAndCheckDate1Test() {
    new MainPage(driver)
      .openPage()
      .choiceEarliestOrLatestCourse(true)
      .checkEarliestOrLatestCourseDateOnPage();
  }
  @Test
  @Description("Выбор курса, стартующего позже всех")
  public void choiceCourseAndCheckDate2Test() {
    new MainPage(driver)
      .openPage()
      .choiceEarliestOrLatestCourse(false)
      .checkEarliestOrLatestCourseDateOnPage();
  }
}