package pageobjects.pages;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import annotations.UrlPrefix;
import pageobjects.utils.AbsBaseUtils;
import support.DIScoped;

public abstract class AbsBasePage<T extends AbsBasePage<T>> extends AbsBaseUtils {

  protected final static String BASE_URL = System.getProperty("base.url");
  protected String jdivChatIconLocator = "//jdiv[@class='iconWrap_f24a']";

  @Inject
  public AbsBasePage(DIScoped diScoped) {
    super(diScoped);
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
    return urlAnnotation.value().endsWith("/") ? urlAnnotation.value() : urlAnnotation.value() + "/";
  }
}
