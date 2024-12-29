package com.csb6.cpp.types;

import java.util.HashSet;
import java.util.Set;

public class CPPOptionalType extends CPPType {

    public CPPType type;

    public CPPOptionalType(CPPType type) {
        this.type = type;
    }

    @Override
    public Set<String> requiredHeaders() {
        var headers = new HashSet<>(type.requiredHeaders());
        headers.add("optional");
        return headers;
    }

    @Override
    public Set<String> requiredEnums() {
        return type.requiredEnums();
    }

    @Override
    public String toString() {
        return String.format("std::optional<%s>", type);
    }
}
