package org.arxing.lazyattrCompile;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.arxing.lazyattrAnnotation.attr.BoolAttr;
import org.arxing.lazyattrAnnotation.attr.ColorAttr;
import org.arxing.lazyattrAnnotation.attr.DimenAttr;
import org.arxing.lazyattrAnnotation.attr.DimenPixelAttr;
import org.arxing.lazyattrAnnotation.attr.EnumAttr;
import org.arxing.lazyattrAnnotation.attr.FlagAttr;
import org.arxing.lazyattrAnnotation.attr.FloatAttr;
import org.arxing.lazyattrAnnotation.attr.FractionAttr;
import org.arxing.lazyattrAnnotation.attr.IntAttr;
import org.arxing.lazyattrAnnotation.attr.RefAttr;
import org.arxing.lazyattrAnnotation.attr.StringAttr;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.tools.Diagnostic;

public abstract class BaseProcessor extends AbstractProcessor {
    protected ProcessingEnvironment env;
    protected Logger logger;
    private String fileName = "lazy_attr.xml";
    private Set<Class<? extends Annotation>> supportTypes = new HashSet<>();

    @Override public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        this.env = processingEnv;
        logger = new Logger(env.getMessager());
        logger.note("init");

        try {
            init();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override public Set<String> getSupportedAnnotationTypes() {
        Set<Class<? extends Annotation>> types = Stream.of(BoolAttr.class, ColorAttr.class, DimenAttr.class,
                                                           DimenPixelAttr.class, EnumAttr.class, FlagAttr.class, FloatAttr.class,
                                                           FractionAttr.class, IntAttr.class, RefAttr.class, StringAttr.class
                )
                                                       .collect
                (Collectors
                                                                                                                                    .toSet());
        supportTypes.addAll(types);
        return Stream.of(supportTypes).map(Class::getName).collect(Collectors.toSet());
    }

    @Override public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.RELEASE_8;
    }

    @Override public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (roundEnv.processingOver())
            return false;
        env.getMessager().printMessage(Diagnostic.Kind.NOTE, "process");
        Map<TypeElement, Map<Annotation, List<VariableElement>>> data = new HashMap<>();
        for (Class<? extends Annotation> type : supportTypes) {
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(type);
            for (Element element : elements) {
                if (element.getKind() == ElementKind.FIELD) {
                    Annotation annotation = element.getAnnotation(type);
                    Stream.of(element.getAnnotationMirrors()).filter(mirror->mirror.getAnnotationType().)


                    VariableElement elField = (VariableElement) element;
                    TypeElement elHost = (TypeElement) elField.getEnclosingElement();

                    if (!data.containsKey(elHost))
                        data.put(elHost, new HashMap<>());
                    Map<Annotation, List<VariableElement>> innerData = data.get(elHost);

                    if (!innerData.containsKey(annotation))
                        innerData.put(annotation, new ArrayList<>());

                    List<VariableElement> list = innerData.get(annotation);
                    list.add(elField);
                }
            }
        }
        return handle(data);
    }

    protected abstract boolean handle(Map<TypeElement, Map<Annotation, List<VariableElement>>> data);

    private void init() throws Exception {
        File attrFile = getAttrFile();
        if (attrFile.exists()) {
            logger.note("Delete %s", attrFile.toString());
            attrFile.delete();
        }
    }

    protected File getAttrFolderFile() {
        String jarPath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        return new File(Stream.of(jarPath.split("/"))
                              .custom(new Reverse<>())
                              .skip(4)
                              .custom(new Reverse<>())
                              .collect(Collectors.joining("/"))
                              .concat("/app/src/main/res/values"));
    }

    protected File getAttrFile() {
        return new File(getAttrFolderFile(), fileName);
    }
}
