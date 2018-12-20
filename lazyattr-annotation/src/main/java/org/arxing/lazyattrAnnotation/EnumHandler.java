package org.arxing.lazyattrAnnotation;

public interface EnumHandler<T extends Enum & Valueable> {

    Class<T> cls();

}
