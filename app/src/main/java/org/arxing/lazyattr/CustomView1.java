package org.arxing.lazyattr;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import org.arxing.lazyattrAnnotation.attr.BoolAttr;
import org.arxing.lazyattrAnnotation.attr.EnumAttr;
import org.arxing.lazyattrAnnotation.EnumHandler;
import org.arxing.lazyattrAnnotation.Mode;

public class CustomView1 extends View {
    @BoolAttr(mode = Mode.CONST, defConst = true) private boolean isOpened = true;
    @EnumAttr(GravityHandler.class) private LayoutGravity gravity;

    public CustomView1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);


    }

    public static class GravityHandler implements EnumHandler<LayoutGravity> {

        @Override public Class<LayoutGravity> cls() {
            return LayoutGravity.class;
        }
    }
}
