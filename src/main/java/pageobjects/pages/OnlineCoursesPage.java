package pageobjects.pages;

import annotations.UrlPrefix;
import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import support.DIScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@UrlPrefix("/online/")
public class OnlineCoursesPage extends AbsBasePage<OnlineCoursesPage> {

  @Inject
  public OnlineCoursesPage(DIScoped diScoped) {
    super(diScoped);
  }

  public Map<String, Integer> getListCoursesWithPrice() {
    Map<String, Integer> coursesMap = new HashMap<>();
    List<WebElement> coursesBlock = findElems(By.cssSelector(".lessons__new-item-container"));

    for (WebElement element : coursesBlock) {
      String name = element
              .findElement(By.cssSelector(".lessons__new-item-title.lessons__new-item-title_with-tags.lessons__new-item-title_with-bg.js-ellipse"))
              .getText();

      Integer price = Integer.parseInt(element
              .findElement(By.cssSelector(".lessons__new-item-price"))
              .getText()
              .replaceAll("[^\\d]", ""));

      coursesMap.put(name, price);
    }
    return coursesMap;
  }

  public void findCheapestCourses() {
    Optional<Map.Entry<String, Integer>> minEntry = this.getListCoursesWithPrice().entrySet().stream()
            .min(Map.Entry.comparingByValue());
    int lowPrice = minEntry.get().getValue();
    log.info("Курсы с самой низкой ценой:");
    this.getListCoursesWithPrice().entrySet().stream()
            .filter(entry -> entry.getValue() == lowPrice)
            .forEach(entry -> log.info(entry.getKey() + " - " + entry.getValue() + " руб."));
  }

  public void findExpensiveCourses() {
    Optional<Map.Entry<String, Integer>> maxEntry = this.getListCoursesWithPrice().entrySet().stream()
            .max(Map.Entry.comparingByValue());
    int highPrice = maxEntry.get().getValue();
    log.info("Курсы с самой высокой ценой:");
    this.getListCoursesWithPrice().entrySet().stream()
            .filter(entry -> entry.getValue() == highPrice)
            .forEach(entry -> log.info(entry.getKey() + " - " + entry.getValue() + " руб."));
  }
}