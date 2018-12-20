package org.arxing.lazyattrCompile;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;

import org.jdom2.Element;

import java.util.ArrayList;
import java.util.List;


import javafx.util.Pair;

public class Attr {
    private String name;
    private List<Format> formats;
    private List<Pair<String, Integer>> enumChildren;
    private List<Pair<String, Integer>> flagChildren;

    private Attr(String name) {
        this.name = name;
        this.formats = new ArrayList<>();
        this.enumChildren = new ArrayList<>();
        this.flagChildren = new ArrayList<>();
    }

    public static Attr of(String name) {
        return new Attr(name);
    }

    public List<Pair<String, Integer>> getEnumChildren() {
        return enumChildren;
    }

    public List<Pair<String, Integer>> getFlagChildren() {
        return flagChildren;
    }

    public String getName() {
        return name;
    }

    public List<Format> getFormats() {
        return formats;
    }

    public Attr addFormats(Format[] formats) {
        for (Format format : formats) {
            addFormat(format);
        }
        return this;
    }

    public Attr addFormat(Format format) {
        formats.add(format);
        return this;
    }

    public Attr addEnumChild(String name, int value) {
        enumChildren.add(new Pair<>(name, value));
        return this;
    }

    public Attr addFlagChild(String name, int value) {
        flagChildren.add(new Pair<>(name, value));
        return this;
    }

    Element collect() {
        Element attr = new Element(Consts.ATTR);
        attr.setAttribute(Consts.NAME, name);

        if (formats.size() > 0)
            attr.setAttribute(Consts.FORMAT, Stream.of(formats).map(Format::toString).collect(Collectors.joining("|")));
        Stream.of(enumChildren).forEach(pair -> {
            Element enumChild = new Element(Consts.ENUM);
            enumChild.setAttribute(Consts.NAME, pair.getKey());
            enumChild.setAttribute(Consts.VALUE, pair.getValue().toString());
            attr.addContent(enumChild);
        });
        Stream.of(flagChildren).forEach(pair -> {
            Element flagChild = new Element(Consts.FLAG);
            flagChild.setAttribute(Consts.NAME, pair.getKey());
            flagChild.setAttribute(Consts.VALUE, pair.getValue().toString());
            attr.addContent(flagChild);
        });
        return attr;
    }
}
