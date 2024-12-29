package com.csb6.cpp.types;

import java.util.HashSet;
import java.util.Set;

public class CPPSetType extends CPPType {

    // TODO: require valueType meets requirements for std::set::value_type
    public CPPType valueType;

    public CPPSetType(CPPType valueType) {
        this.valueType = valueType;
    }

    @Override
    public Set<String> requiredHeaders() {
        var headers = new HashSet<>(valueType.requiredHeaders());
        headers.add("set");
        return headers;
    }

    @Override
    public Set<String> requiredEnums() {
        return valueType.requiredEnums();
    }

    @Override
    public String toString() {
        return String.format("std::set<%s>", valueType);
    }
}
