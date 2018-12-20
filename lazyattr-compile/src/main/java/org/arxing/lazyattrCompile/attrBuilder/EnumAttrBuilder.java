package org.arxing.lazyattrCompile.attrBuilder;

import com.annimon.stream.Stream;

import org.arxing.lazyattrAnnotation.EnumHandler;
import org.arxing.lazyattrAnnotation.Valueable;
import org.arxing.lazyattrAnnotation.attr.EnumAttr;
import org.arxing.lazyattrCompile.Attr;
import org.arxing.lazyattrCompile.Format;

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class EnumAttrBuilder implements AttrBuilder<EnumAttr> {
    @Override public Attr build(TypeElement elHost, VariableElement elField, EnumAttr annotation) {
        Attr attr = Attr.of(attrName(elField));
        try {
            attr.addFormat(Format.ENUM);
            EnumHandler enumHandler = annotation.value().newInstance();
            Stream.of(enumHandler.cls().getEnumConstants()).forEach(obj -> {
                Enum e = (Enum) obj;
                Valueable valueable = (Valueable) obj;
                String enumName = e.name();
                int enumValue = valueable.val();
                attr.addEnumChild(enumName, enumValue);
            });
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return attr;
    }
}
