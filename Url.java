package framework.annotation;
import java.lang.annotation.*;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //RUNTIME no ampiasatsika aloha zao
@Target ( ElementType.METHOD) //manisy virgule , Element.METHOD
public @interface Url {
    String url_map() default "";
    String[] parameters() default {};
}
