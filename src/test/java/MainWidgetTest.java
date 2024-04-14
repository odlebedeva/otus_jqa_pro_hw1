import com.google.inject.Inject;
import components.ChatWindowComponent;
import extensions.AndroidExtension;
import modules.GuicePageModule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pageobjects.pages.MainPage;

@ExtendWith(AndroidExtension.class)
public class MainWidgetTest {

  //@Inject
  //private MainPage mainPage;
  @Inject
  MainPage mainPage = new GuicePageModule().getMainPage();

  @Inject
  private ChatWindowComponent chatWindowComponent;
  @Test
  public void navigationMainPageTest() {
    mainPage.open()
      .clickNextButton()
      .clickChatButton();

    chatWindowComponent.getComponentEntity().click();
  }
}
