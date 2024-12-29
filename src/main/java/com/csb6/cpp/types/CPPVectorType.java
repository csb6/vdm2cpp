package com.csb6.cpp.types;

import java.util.HashSet;
import java.util.Set;

public class CPPVectorType extends CPPType {

    public CPPType valueType;

    public CPPVectorType(CPPType valueType) {
        this.valueType = valueType;
    }

    @Override
    public Set<String> requiredHeaders() {
        var headers = new HashSet<>(valueType.requiredHeaders());
        headers.add("vector");
        return headers;
    }

    @Override
    public Set<String> requiredEnums() {
        return valueType.requiredEnums();
    }

    @Override
    public String toString() {
        return String.format("std::vector<%s>", valueType);
    }
}
