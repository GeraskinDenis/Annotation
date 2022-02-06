package ru.geraskindenis.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    // Обязательное поле при объявлении аннотации
    String name();

    // Не обязательное поле, поскольку есть значение по умолчанию
    boolean lazyLoad() default false;
}
