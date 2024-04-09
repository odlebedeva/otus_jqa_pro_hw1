package factories.impl;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxSettings implements IBrowserSettings {
  private String browserVersion = System.getProperty("browser.version");

  @Override
  public WebDriver configure() {
    FirefoxOptions firefoxOptions = new FirefoxOptions();

    firefoxOptions.addArguments("--disable-notifications");
    firefoxOptions.addArguments("--start-maximized");
    firefoxOptions.addArguments("--homepage=about:blank");
    firefoxOptions.addArguments("--ignore-certificate-errors");
    firefoxOptions.addArguments("--lang=ru");
    firefoxOptions.addArguments("--no-sandbox");
    firefoxOptions.addArguments("--remote-allow-origins=*");
    firefoxOptions.addArguments("--no-first-run");
    firefoxOptions.addArguments("--enable-extensions");

    WebDriverManager.firefoxdriver().browserVersion(this.browserVersion).setup();

    return new FirefoxDriver(firefoxOptions);
  }
}
