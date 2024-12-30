package com.csb6.cpp.types;

import java.util.HashSet;
import java.util.Set;

public class CPPVectorType extends CPPType {

    public CPPType valueType;
    private boolean isString;

    public CPPVectorType(CPPType valueType) {
        this.valueType = valueType;
        this.isString = valueType instanceof CPPCharType;
    }

    @Override
    public Set<String> requiredHeaders() {
        var headers = new HashSet<>(valueType.requiredHeaders());
        if(isString) {
            headers.add("string");
        } else {
            headers.add("vector");
        }
        return headers;
    }

    @Override
    public Set<String> requiredEnums() {
        return valueType.requiredEnums();
    }

    @Override
    public String toString() {
        return isString ? "std::string" : String.format("std::vector<%s>", valueType);
    }
}
