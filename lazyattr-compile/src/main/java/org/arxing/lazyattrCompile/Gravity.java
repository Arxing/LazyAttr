package org.arxing.lazyattrCompile;

import org.arxing.lazyattrAnnotation.Valueable;

public enum Gravity implements Valueable {
    top,
    left,
    right;

    Gravity(){

    }

    @Override public int val() {
        switch (this){
            case top:
                return 100;
            case left:
                return 200;
            case right:
                return 300;
        }
        return 0;
    }
}
