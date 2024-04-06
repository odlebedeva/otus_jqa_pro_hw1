package pageobjects.pages;

import com.google.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import support.DIScoped;

import java.time.LocalDate;

public class CourseCardPage extends AbsBasePage<CourseCardPage> {

  @Inject
  public CourseCardPage(DIScoped diScoped) {
    super(diScoped);
  }

  public void checkCourseNameAndDescriptionData() {
    String name = "";

    waiters.presenceOfElementLocated(By.xpath(jdivChatIconLocator));
    try {
      name = findElem(By.cssSelector(".sc-1og4wiw-0.sc-s2pydo-1.ifZfhS.diGrSa")).getText();
    } catch (NoSuchElementException e) {
      String nameFailMessage = "Имя карточки курса не найдено!";
      log.info(nameFailMessage);
      Assertions.fail(nameFailMessage);
    }

    try {
      findElem(By.cssSelector(".sc-1og4wiw-0.sc-s2pydo-3.gaEufI.dZDxRw")).getText();
    } catch (NoSuchElementException e) {
      String descriptionFailMessage = "Описание курса для карточки '%s' не найдено!";
      log.info(String.format(descriptionFailMessage, name));
      Assertions.fail(String.format(descriptionFailMessage, name));
    }
  }

  public LocalDate getCourseDate() {
    waiters.presenceOfElementLocated(By.xpath(jdivChatIconLocator));
    return dateParser(findElem(By.xpath("//div[contains(@class,'kGoYMV') or contains(@class, 'sc-3cb1l3-4')]//p[substring(text(), string-length(text()) - 0) = 'я' or contains(text(),'та')]")));
  }
}