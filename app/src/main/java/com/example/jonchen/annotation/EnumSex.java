package com.example.jonchen.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 17041931
 * @since 2018/1/11
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface EnumSex {
    public enum Sex {
        BOY("男"),
        GIRL("女");
        private String sex;

        Sex(String sex) {
            this.sex = sex;
        }
    }

    Sex sex() default Sex.BOY;
}
