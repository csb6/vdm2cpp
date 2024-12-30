package com.csb6.cpp.types;

import java.util.Set;

public class CPPMapType extends CPPType {

    // TODO: require valueType and keyType meet requirements
    public CPPType keyType;
    public CPPType valueType;

    public CPPMapType(CPPType keyType, CPPType valueType) {
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    public void collectRequiredHeaders(Set<String> headers) {
        keyType.collectRequiredHeaders(headers);
        valueType.collectRequiredHeaders(headers);
        headers.add("map");
    }

    @Override
    public void collectRequiredEnums(Set<String> enums) {
        keyType.collectRequiredEnums(enums);
        valueType.collectRequiredEnums(enums);
    }

    @Override
    public String toString() {
        return String.format("std::map<%s, %s>", keyType, valueType);
    }
}
