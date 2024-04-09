package pageobjects.pages;

import annotations.UrlPrefix;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.LocalDate;
import java.util.*;

@UrlPrefix("/")
public class MainPage extends AbsBasePage<MainPage> {
  private LocalDate earliestCourseDate;
  private LocalDate latestCourseDate;
  private LocalDate earliestOrLatestCourseDate;

  public MainPage(WebDriver driver) {
    super(driver);
  }

  public void filterAndOpenCourseByName(String requiredCourseName) {
    String courseNameLocator = "//h5[contains(text(),'%s')]";

    waiters.presenceOfElementLocated(By.xpath(jdivChatIconLocator));
    List<WebElement> filteredByNameCourses = findElems(By.xpath(String.format(courseNameLocator, requiredCourseName)));
    log.info(String.format("Найдено курсов, по запросу '%s': %d.", requiredCourseName, filteredByNameCourses.size()));

    if (filteredByNameCourses.isEmpty()) {
      String noCoursesForCheckingFailMessage = "Нет курсов для проверки";
      log.info(noCoursesForCheckingFailMessage);
      Assertions.fail(noCoursesForCheckingFailMessage);
    } else {
      Random random = new Random();
      WebElement chosenCourse = filteredByNameCourses.get(random.nextInt(filteredByNameCourses.size()));
      if (filteredByNameCourses.size() > 1) {
        log.info(String.format("Из найденных курсов, для проверки случайно был выбран '%s'.", chosenCourse.getText()));
      }

      try {
        moveAndClick(chosenCourse);
      } catch (ElementClickInterceptedException e) {
        closeCookiesMessage();
        moveAndClick(chosenCourse);
      }
    }
  }

  public MainPage choiceEarliestOrLatestCourse(boolean choiceCourseDate) {
    Map<WebElement, LocalDate> tilesDateMap = this.getSectionElementsWithLocalDate();
    Map<WebElement, LocalDate> earlistOrLatestCourseMap = new HashMap<>();
    if (choiceCourseDate) {
      // если true, то выбираем самый ранний курс
      Optional<Map.Entry<WebElement, LocalDate>> courseEntry = tilesDateMap.entrySet().stream()
          .reduce((entry1, entry2) -> entry1.getValue().isBefore(entry2.getValue()) ? entry1 : entry2);

      earlistOrLatestCourseMap = courseEntry.map(entry -> {
        Map<WebElement, LocalDate> mapWithLatestDate = new HashMap<>();
        mapWithLatestDate.put(entry.getKey(), entry.getValue());
        return mapWithLatestDate;
      }).orElseGet(HashMap::new);

      this.earliestOrLatestCourseDate = earlistOrLatestCourseMap.values().iterator().next();
      log.info(String.format("Курс, стартующий раньше всех, начинается %s", this.earliestOrLatestCourseDate));

    } else {
      Optional<Map.Entry<WebElement, LocalDate>> courseEntry = tilesDateMap.entrySet().stream()
          .reduce((entry1, entry2) -> entry1.getValue().isAfter(entry2.getValue()) ? entry1 : entry2);

      earlistOrLatestCourseMap = courseEntry.map(entry -> {
        Map<WebElement, LocalDate> mapWithLatestDate = new HashMap<>();
        mapWithLatestDate.put(entry.getKey(), entry.getValue());
        return mapWithLatestDate;
      }).orElseGet(HashMap::new);

      this.earliestOrLatestCourseDate = earlistOrLatestCourseMap.values().iterator().next();
      log.info(String.format("Курс, стартующий позже всех, начинается %s", this.earliestOrLatestCourseDate));
    }
    try {
      moveAndClick(earlistOrLatestCourseMap.keySet().iterator().next());
    } catch (ElementClickInterceptedException e) {
      closeCookiesMessage();
      moveAndClick(earlistOrLatestCourseMap.keySet().iterator().next());
    }
    return this;
  }

  public void checkEarliestOrLatestCourseDateOnPage() {
    Assertions.assertEquals(this.earliestOrLatestCourseDate, new CourseCardPage(driver).getCourseDate());
  }

  private Map<WebElement, LocalDate> getSectionElementsWithLocalDate() {

    // Блоки курсов на главной странице
    String firstTypeBlockLocator = "//span[contains(text(), 'С ')]";
    String secondTypeBlockLocator  = ".sc-12yergf-7.dPBnbE";

    waiters.presenceOfElementLocated(By.xpath(jdivChatIconLocator));
    List<WebElement> blockList = findElems(By.xpath(firstTypeBlockLocator));
    blockList.addAll(findElems(By.cssSelector(secondTypeBlockLocator)));

    Map<WebElement, LocalDate> blockDateMap = new HashMap<>();
    for (WebElement element : blockList) {
      LocalDate date = dateParser(element);
      blockDateMap.put(element, date);
    }
    return blockDateMap;
  }
}