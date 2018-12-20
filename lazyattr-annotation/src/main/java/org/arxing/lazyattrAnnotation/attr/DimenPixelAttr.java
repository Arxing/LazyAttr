package org.arxing.lazyattrAnnotation.attr;


import android.support.annotation.BoolRes;

import org.arxing.lazyattrAnnotation.Mode;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.CLASS)
@Target(ElementType.FIELD)
public @interface DimenPixelAttr {
    int defConst() default 0;

    @BoolRes int defId() default 0;

    Mode mode() default Mode.NONE;

    PixelMode pixelMode() default PixelMode.SIZE;

    enum PixelMode {
        SIZE,
        OFFSET
    }
}
