package extensions;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.google.inject.Guice;
import modules.GuiceComponentsModule;
import modules.GuicePagesModule;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import providers.AndroidWebDriverProvider;

public class AndroidExtension implements BeforeAllCallback, AfterAllCallback {

  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    // создали инджектор и он заинджектит в тест объект мэйнпейдж
    Guice.createInjector(new GuicePagesModule(), new GuiceComponentsModule());

    Configuration.browserSize = null;
    //Configuration.remote = System.getProperty("remote.url");
    Configuration.browser = AndroidWebDriverProvider.class.getName();
    Configuration.timeout = 20000L;
  }

  @Override
  public void afterAll(ExtensionContext extensionContext) {
    Selenide.closeWebDriver();
  }
}
