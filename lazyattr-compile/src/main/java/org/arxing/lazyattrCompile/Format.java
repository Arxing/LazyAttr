package org.arxing.lazyattrCompile;

public enum Format {
    INTEGER,
    COLOR,
    BOOLEAN,
    DIMENSION,
    STRING,
    REFERENCE,
    FLOAT,
    ENUM,
    FLAG;

    @Override public String toString() {
        switch (this) {
            case INTEGER:
                return "integer";
            case COLOR:
                return "color";
            case BOOLEAN:
                return "boolean";
            case DIMENSION:
                return "dimension";
            case STRING:
                return "string";
            case REFERENCE:
                return "reference";
            case FLOAT:
                return "float";
            case ENUM:
                return "enum";
            case FLAG:
                return "flag";
            default:
                return null;
        }
    }
}
