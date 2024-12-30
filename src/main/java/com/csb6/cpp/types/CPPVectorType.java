package com.csb6.cpp.types;

import java.util.Set;

public class CPPVectorType extends CPPType {

    public CPPType valueType;
    private boolean isString;

    public CPPVectorType(CPPType valueType) {
        this.valueType = valueType;
        this.isString = valueType instanceof CPPCharType;
    }

    @Override
    public void collectRequiredHeaders(Set<String> headers) {
        valueType.collectRequiredHeaders(headers);
        if(isString) {
            headers.add("string");
        } else {
            headers.add("vector");
        }
    }

    @Override
    public void collectRequiredEnums(Set<String> enums) {
        valueType.collectRequiredEnums(enums);
    }

    @Override
    public String toString() {
        return isString ? "std::string" : String.format("std::vector<%s>", valueType);
    }
}
