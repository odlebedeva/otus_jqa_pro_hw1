package pageobjects.utils;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import support.DIScoped;
import waiters.Waiters;

import java.util.List;

public abstract class AbsElementActions {
  protected WebDriver driver;
  protected DIScoped diScoped;
  protected Actions actions;
  protected JavascriptExecutor js;
  private final Waiters actionWaiter;

  @Inject
  public AbsElementActions(DIScoped diScoped) {
    this.diScoped = diScoped;
    this.driver = diScoped.getDriver();
    this.actions = new Actions(driver);
    this.actionWaiter = new Waiters(driver, 10);
  }

  public WebElement findElem(By by) {
    return driver.findElement(by);
  }

  public List<WebElement> findElems(By by) {
    return driver.findElements(by);
  }

  public void moveToElement(WebElement element) {
    actions.moveToElement(element).perform();
  }

  public void moveAndClick(WebElement element) {
    actionWaiter.waitForElementClickable(element);
    this.moveToElement(element);
    element.click();
  }
}
