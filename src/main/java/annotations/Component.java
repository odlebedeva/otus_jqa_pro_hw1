package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

    // прокидываем локаторы аннотации через него
    String value();
    // почему стринг, а не селенид элемент:
    // метаданные создаются раньше, чем происходит создание класса и вызов статич конфигруратоа
}
