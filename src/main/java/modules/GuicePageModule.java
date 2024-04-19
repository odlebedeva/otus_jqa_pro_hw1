package modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import pageobjects.ChatPage;
import pageobjects.MainPage;

public class GuicePageModule extends AbstractModule {

  @Provides
  @Singleton  //для параллелизации по сьютам
  public MainPage getMainPage() {
    // метод провайдер
    // в рамках одного инстанса переиспользование
    return new MainPage();
  }

  @Provides
  @Singleton
  public ChatPage getChatPage() {
    return new ChatPage();
  }
}