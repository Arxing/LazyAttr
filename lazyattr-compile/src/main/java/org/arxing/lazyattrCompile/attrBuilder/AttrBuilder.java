package org.arxing.lazyattrCompile.attrBuilder;

import org.arxing.lazyattrCompile.Attr;

import java.lang.annotation.Annotation;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public interface AttrBuilder<T extends Annotation> {
    Attr build(TypeElement elHost, VariableElement elField, T annotation);

    default String attrName(VariableElement elField) {
        return elField.getSimpleName().toString();
    }
}
