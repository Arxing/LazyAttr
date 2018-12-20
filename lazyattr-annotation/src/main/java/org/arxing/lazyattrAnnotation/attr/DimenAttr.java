package org.arxing.lazyattrAnnotation.attr;


import android.support.annotation.BoolRes;
import android.support.annotation.ColorInt;

import org.arxing.lazyattrAnnotation.Mode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface DimenAttr {
    float defConst() default 0;

    @BoolRes int defId() default 0;

    Mode mode() default Mode.NONE;
}
