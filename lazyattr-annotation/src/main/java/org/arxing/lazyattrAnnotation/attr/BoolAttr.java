package org.arxing.lazyattrAnnotation.attr;


import android.support.annotation.BoolRes;

import org.arxing.lazyattrAnnotation.Mode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface BoolAttr {
    boolean defConst() default false;

    @BoolRes int defId() default 0;

    Mode mode() default Mode.NONE;
}
