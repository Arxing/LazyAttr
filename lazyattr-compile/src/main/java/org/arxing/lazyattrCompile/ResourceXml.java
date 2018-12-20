package org.arxing.lazyattrCompile;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class ResourceXml {
    private Map<String, Styleable> styleableMap = new HashMap<>();

    private Element collect() {
        Element resources = new Element(Consts.RESOURCES);
        for (Styleable styleable : styleableMap.values()) {
            Element elStyleable = styleable.collect();
            resources.addContent(elStyleable);
        }
        return resources;
    }

    public ResourceXml addStyleable(Styleable styleable) {
        styleableMap.put(styleable.getName(), styleable);
        return this;
    }

    public Styleable targetStyleable(String name) {
        if (!styleableMap.containsKey(name))
            styleableMap.put(name, Styleable.of(name).setParent(this));
        return styleableMap.get(name);
    }

    public void write(File file) {
        try {
            Element resources = collect();
            Document document = new Document(resources);
            XMLOutputter outputter = new XMLOutputter();
            outputter.setFormat(Format.getPrettyFormat().setIndent("    "));
            outputter.output(document, new OutputStreamWriter(new FileOutputStream(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
