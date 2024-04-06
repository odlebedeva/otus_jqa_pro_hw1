package pageobjects.pages;

import com.google.inject.Inject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import annotations.UrlPrefix;
import support.DIScoped;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@UrlPrefix("/")
public class MainPage extends AbsBasePage<MainPage> {
  private LocalDate earliestOrLatestCourseDate;

  @Inject
  public MainPage(DIScoped diScoped) {
    super(diScoped);
  }

  public void findAndOpenCourseByName(String courseName) {
    String courseNameLocator = "//h5[contains(text(),'%s')]";

    waiters.presenceOfElementLocated(By.xpath(jdivChatIconLocator));
    List<WebElement> filteredByNameCourses = findElems(By.xpath(String.format(courseNameLocator, courseName)));
    log.info(String.format("Найдено курсов, по запросу '%s': %d.", courseName, filteredByNameCourses.size()));

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
    Assertions.assertEquals(this.earliestOrLatestCourseDate, new CourseCardPage(diScoped).getCourseDate());
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

  public void findCourseInSpecifiedDateOrLater(String courseDate) {
    LocalDate specifiedCourseDate = LocalDate.parse(courseDate, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    Map<WebElement, LocalDate> sectionDateMap = getSectionElementsWithLocalDate();

    Map<WebElement, LocalDate> filteredOnDateMap = sectionDateMap.entrySet().stream()
            .filter(entry -> entry.getValue().isEqual(specifiedCourseDate) || entry.getValue().isAfter(specifiedCourseDate))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    if (!filteredOnDateMap.isEmpty()) {
      log.info(String.format("Курсы, стартующие не раньше %s: ", courseDate));
      filteredOnDateMap.forEach((key, value) -> {
        WebElement parentElement = key;
        String hrefValue = null;

        while (parentElement != null) {
          hrefValue = parentElement.getAttribute("href");
          if (hrefValue != null) {
            break;
          }
          parentElement = parentElement.findElement(By.xpath(".."));
        }

        try {
          Document doc = Jsoup.connect(hrefValue).get();
          Elements nameCourse = doc.select(".sc-1og4wiw-0.sc-s2pydo-1.ifZfhS.diGrSa");
          log.info("Название: " + nameCourse.get(0).text());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
        log.info("Дата старта: " + value.toString());
      });
    } else {
      log.info(String.format("Курсов, стартующих позже %s не найдено! ", courseDate));
    }
  }
}