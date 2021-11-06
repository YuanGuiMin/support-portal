package com.meowu.support.portal.service.commons.security.stereotype;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Initializer{

    @AliasFor(annotation = Component.class)
    String value() default "";
}
