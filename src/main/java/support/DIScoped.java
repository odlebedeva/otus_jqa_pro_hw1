package support;

import io.cucumber.guice.ScenarioScoped;
import org.openqa.selenium.WebDriver;
import factories.WebDriverFactory;

@ScenarioScoped
public class DIScoped {
  // создание сессии через Фабрику
  public WebDriver driver = new WebDriverFactory().create();

  public WebDriver getDriver() {
    return this.driver;
  }
}