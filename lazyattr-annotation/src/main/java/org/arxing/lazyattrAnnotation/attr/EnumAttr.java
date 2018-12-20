package org.arxing.lazyattrAnnotation.attr;


import org.arxing.lazyattrAnnotation.EnumHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface EnumAttr {
    Class<? extends EnumHandler<?>> value();
}
