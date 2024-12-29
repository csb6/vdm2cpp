package com.csb6.cpp.types;

import java.util.HashSet;
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
    public Set<String> requiredHeaders() {
        var headers = new HashSet<>(keyType.requiredHeaders());
        headers.addAll(valueType.requiredHeaders());
        headers.add("map");
        return headers;
    }

    @Override
    public Set<String> requiredEnums() {
        var enums = new HashSet<>(keyType.requiredEnums());
        enums.addAll(valueType.requiredEnums());
        return enums;
    }

    @Override
    public String toString() {
        return String.format("std::map<%s, %s>", keyType, valueType);
    }
}
