package components;

import annotations.Component;


@Component("id:chat")
public class ChatWindowComponent extends AbsBaseComponent<ChatWindowComponent> {

//    private String defaultMessage = "[text='Type a message...']";
//
//    public ChatWindowComponent sendMessage(String text) {
//        $(defaultMessage).shouldBe(Condition.visible);
//        $(defaultMessage).sendKeys(text);
//        isPresent(text)
//            .click("Send");
//        //.isPresent(text);
//        return this;
//    }
//
//    public ChatWindowComponent checkResponse(String expectedResponse) {
//        $(defaultMessage).shouldBe(Condition.visible);
//        System.out.println($x("//android.widget.TextView[@text='" + expectedResponse + "']").shouldBe(Condition.visible).text());
//        return this;
//    }
//
//    public ChatWindowComponent checkStatement(String statement) {
//        String[] words = statement.split(" ");
//        System.out.println(Arrays.toString(words));
//
//        Stream.of(words).forEach(i -> checkResponse(i));
//        return this;
//    }

}
