package com.csb6.cpp.types;

import java.util.Set;

public class CPPOptionalType extends CPPType {

    public CPPType type;

    public CPPOptionalType(CPPType type) {
        this.type = type;
    }

    @Override
    public void collectRequiredHeaders(Set<String> headers) {
        type.collectRequiredHeaders(headers);
        headers.add("optional");
    }

    @Override
    public void collectRequiredEnums(Set<String> enums) {
        type.collectRequiredEnums(enums);
    }

    @Override
    public String toString() {
        return String.format("std::optional<%s>", type);
    }
}
