package org.arxing.lazyattr;

import org.arxing.lazyattrAnnotation.Valueable;

public enum LayoutGravity implements Valueable {
    top(1),
    bottom(2),
    left(3),
    right(4);

    final int val;

    LayoutGravity(int val) {
        this.val = val;
    }

    @Override public int val() {
        return val;
    }
}
