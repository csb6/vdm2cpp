package com.csb6.cpp.types;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CPPFunctionType extends CPPType {

    public CPPType resultType;
    public CPPTypeList paramTypes;

    public CPPFunctionType(CPPType resultType, CPPTypeList paramTypes) {
        this.resultType = resultType;
        this.paramTypes = paramTypes;
    }

    @Override
    public Set<String> requiredHeaders() {
        var headers = new HashSet<>(resultType.requiredHeaders());
        for (var paramType : paramTypes) {
            headers.addAll(paramType.requiredHeaders());
        }
        headers.add("functional");
        return headers;
    }

    @Override
    public Set<String> requiredEnums() {
        var enums = new HashSet<>(resultType.requiredEnums());
        for (var paramType : paramTypes) {
            enums.addAll(paramType.requiredEnums());
        }
        return enums;
    }

    @Override
    public String toString() {
        var s = new StringBuilder();
        s.append(String.format("std::function<%s(", resultType));
        s.append(paramTypes.stream()
            .map(t -> t.toString())
            .collect(Collectors.joining(", ")));
        s.append(")>");
        return s.toString();
    }

}
