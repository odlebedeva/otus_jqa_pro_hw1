package pageobjects.pages;

import annotations.UrlPrefix;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageobjects.utils.AbsBaseUtils;

public abstract class AbsBasePage<T extends AbsBasePage<T>> extends AbsBaseUtils {

  protected final static String BASE_URL = System.getProperty("base.url");
  protected String jdivChatIconLocator = "//jdiv[@class='iconWrap_f24a']";

  public AbsBasePage(WebDriver driver) {
    super(driver);
  }

  public T openPage() {
    driver.get(BASE_URL + getUrlPrefix());
    return (T) this;
  }

  protected void closeCookiesMessage() {
    WebElement cookieMessageButton = findElem(By.cssSelector(".sc-9a4spb-0.ckCZjI"));
    moveAndClick(cookieMessageButton);
  }

  private String getUrlPrefix() {
    UrlPrefix urlAnnotation = getClass().getAnnotation(UrlPrefix.class);
    return urlAnnotation.value();
  }
}
