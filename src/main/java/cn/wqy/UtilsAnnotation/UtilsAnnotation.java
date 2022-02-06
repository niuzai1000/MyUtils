package cn.wqy.UtilsAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
@UtilsAnnotation("UtilsAnnotation Utils")
public @interface UtilsAnnotation {
    String value();
}
