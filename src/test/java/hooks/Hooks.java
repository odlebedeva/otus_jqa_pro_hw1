package hooks;

import io.cucumber.java.After;
import org.openqa.selenium.WebDriver;
import support.DIScoped;

import javax.inject.Inject;

public class Hooks {

  @Inject
  public DIScoped diScoped;

  @After
  public void close(){
    WebDriver driver = diScoped.getDriver();
    if(driver!=null) {
      driver.close();
      driver.quit();
    }
  }
}