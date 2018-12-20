package org.arxing.lazyattrCompile;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.jdom2.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Styleable {
    private String name;
    private Map<String, Attr> attrMap = new HashMap<>();
    private ResourceXml parent;

    private Styleable(String name) {
        this.name = name;
    }

    public static Styleable of(String name) {
        return new Styleable(name);
    }

    public String getName() {
        return name;
    }

    public Styleable addAttr(String name, Format... formats) {
        return addAttr(Attr.of(name).addFormats(formats));
    }

    public Styleable addAttr(Attr attr) {
        attrMap.put(attr.getName(), attr);
        return this;
    }

    public ResourceXml parent() {
        return parent;
    }

    Styleable setParent(ResourceXml parent) {
        this.parent = parent;
        return this;
    }

    Element collect() {
        Element styleable = new Element(Consts.DECLARE_STYLEABLE);
        styleable.setAttribute(Consts.NAME, name);
        for (Attr attr : attrMap.values()) {
            Element elAttr = attr.collect();
            styleable.addContent(elAttr);
        }
        return styleable;
    }
}
