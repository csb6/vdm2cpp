package com.csb6.cpp.types;

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
    public void collectRequiredHeaders(Set<String> headers) {
        resultType.collectRequiredHeaders(headers);
        for (var paramType : paramTypes) {
            paramType.collectRequiredHeaders(headers);
        }
        headers.add("functional");
    }

    @Override
    public void collectRequiredEnums(Set<String> enums) {
        resultType.collectRequiredEnums(enums);
        for (var paramType : paramTypes) {
            paramType.collectRequiredEnums(enums);
        }
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
