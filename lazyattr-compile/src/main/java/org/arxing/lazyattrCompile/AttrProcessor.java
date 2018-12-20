package org.arxing.lazyattrCompile;

import com.google.auto.service.AutoService;

import org.arxing.lazyattrAnnotation.attr.BoolAttr;
import org.arxing.lazyattrAnnotation.attr.EnumAttr;
import org.arxing.lazyattrAnnotation.attr.IntAttr;
import org.arxing.lazyattrCompile.attrBuilder.AttrBuilder;
import org.arxing.lazyattrCompile.attrBuilder.BoolAttrBuilder;
import org.arxing.lazyattrCompile.attrBuilder.EnumAttrBuilder;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Map;

import javax.annotation.processing.Processor;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

@AutoService(Processor.class)
public class AttrProcessor extends BaseProcessor {
    private ResourceXml xml;

    @Override protected boolean handle(Map<TypeElement, Map<Annotation, List<VariableElement>>> data) {
        xml = new ResourceXml();
        for (Map.Entry<TypeElement, Map<Annotation, List<VariableElement>>> typeElementMapEntry : data.entrySet()) {
            TypeElement elHost = typeElementMapEntry.getKey();
            String viewName = elHost.getSimpleName().toString();
            Styleable styleable = Styleable.of(viewName);
            logger.note("host: %s", elHost.getQualifiedName().toString());
            for (Map.Entry<Annotation, List<VariableElement>> annotationListEntry : typeElementMapEntry.getValue().entrySet()) {
                Annotation annotation = annotationListEntry.getKey();
                List<VariableElement> elFields = annotationListEntry.getValue();
                logger.note("\tannotation: %s", annotation.toString());
                for (VariableElement elField : elFields) {
                    logger.note("\t\tfield: %s", elField.getSimpleName().toString());
                    AttrBuilder attrBuilder = getAttrBuilder(annotation);
                    logger.note("annotation=%s", annotation.toString());
                    Attr attr = attrBuilder.build(elHost, elField, annotation);
                    styleable.addAttr(attr);
                }
            }
            xml.addStyleable(styleable);
        }
        File attrFile = getAttrFile();
        if (!attrFile.exists()) {
            xml.write(getAttrFile());
        }
        return false;
    }

    private AttrBuilder getAttrBuilder(Annotation annotation) {
        if (annotation instanceof BoolAttr)
            return new BoolAttrBuilder();
        if (annotation instanceof EnumAttr)
            return new EnumAttrBuilder();
        return null;
    }
}
