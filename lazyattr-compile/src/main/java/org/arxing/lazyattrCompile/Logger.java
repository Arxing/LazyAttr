package org.arxing.lazyattrCompile;

import javax.annotation.processing.Messager;
import javax.tools.Diagnostic;

public class Logger {
    private Messager messager;

    public Logger(Messager messager) {
        this.messager = messager;
    }

    public void note(String format, Object... objs) {
        print(Diagnostic.Kind.NOTE, format, objs);
    }

    public void error(String format, Object... objs) {
        print(Diagnostic.Kind.ERROR, format, objs);
    }

    public void warn(String format, Object... objs) {
        print(Diagnostic.Kind.WARNING, format, objs);
    }

    public void other(String format, Object... objs) {
        print(Diagnostic.Kind.OTHER, format, objs);
    }

    private void print(Diagnostic.Kind kind, String format, Object... objs) {
        messager.printMessage(kind, String.format(format, objs));
    }
}
