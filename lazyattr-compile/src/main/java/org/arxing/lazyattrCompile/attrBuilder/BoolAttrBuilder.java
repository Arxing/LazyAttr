package org.arxing.lazyattrCompile.attrBuilder;

import org.arxing.lazyattrAnnotation.attr.BoolAttr;
import org.arxing.lazyattrCompile.Attr;
import org.arxing.lazyattrCompile.Format;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class BoolAttrBuilder implements AttrBuilder<BoolAttr> {
    @Override public Attr build(TypeElement elHost, VariableElement elField, BoolAttr annotation) {
        return Attr.of(attrName(elField)).addFormat(Format.BOOLEAN);
    }
}
