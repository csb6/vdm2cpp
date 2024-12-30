package com.csb6.cpp.types;

import java.util.Set;

public class CPPSetType extends CPPType {

    // TODO: require valueType meets requirements for std::set::value_type
    public CPPType valueType;

    public CPPSetType(CPPType valueType) {
        this.valueType = valueType;
    }

    @Override
    public void collectRequiredHeaders(Set<String> headers) {
        valueType.collectRequiredHeaders(headers);
        headers.add("set");
    }

    @Override
    public void collectRequiredEnums(Set<String> enums) {
        valueType.collectRequiredEnums(enums);
    }

    @Override
    public String toString() {
        return String.format("std::set<%s>", valueType);
    }
}
