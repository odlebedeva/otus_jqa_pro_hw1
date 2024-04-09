package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // данные метаданнные должны быть доступны в runtime
@Target(ElementType.FIELD) // данную аннотацию вешаем на field
// как маркер - не содержит значений, просто маркирует, чтобы поменять поведение
// программы в runtime
public @interface Driver {
}