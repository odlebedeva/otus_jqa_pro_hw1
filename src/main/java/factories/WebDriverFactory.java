package factories;

import exceptions.BrowserNotSupportedException;
import factories.impl.ChromeSettings;
import factories.impl.FirefoxSettings;
import factories.impl.OperaSettings;
import org.openqa.selenium.support.events.EventFiringWebDriver;

// реализация фабрики для выбора браузера
public class WebDriverFactory implements IFactory<EventFiringWebDriver> {
  private String browserName = System.getProperty("browser.name").toLowerCase();

  @Override
  public EventFiringWebDriver create() {
    switch (this.browserName) {
      case "chrome": {
        return new EventFiringWebDriver(new ChromeSettings().configure());
      }
      case "opera": {
        return new EventFiringWebDriver(new OperaSettings().configure());
      }
      case "firefox": {
        return new EventFiringWebDriver(new FirefoxSettings().configure());
      }
      default: {
        throw new BrowserNotSupportedException(this.browserName);
      }
    }
  }
}
