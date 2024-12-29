package com.csb6.cpp.definitions;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.csb6.cpp.types.CPPFunctionType;
import com.csb6.cpp.types.CPPType;
import com.csb6.cpp.types.CPPTypeList;
import com.fujitsu.vdmj.tc.lex.TCNameToken;

public class CPPFunctionDefinition extends CPPDefinition {

    public CPPType resultType;
    public TCNameToken name;
    public CPPTypeList paramTypes;

    // TODO: parameter names and body
    public CPPFunctionDefinition(CPPFunctionType type, TCNameToken name, boolean isCurried) throws Exception {
        this.resultType = type.resultType;
        this.name = name;
        this.paramTypes = type.paramTypes;

        if(isCurried) {
            throw new Exception("Cannot translate function '" + name + "' (curried functions are not supported)");
        }
    }

    @Override
    public Set<String> requiredHeaders() {
        var headers = new HashSet<>(resultType.requiredHeaders());
        for(var type : paramTypes) {
            headers.addAll(type.requiredHeaders());
        }
        return headers;
    }

    @Override
    public Set<String> requiredEnums() {
        var enums = new HashSet<>(resultType.requiredEnums());
        for(var type : paramTypes) {
            enums.addAll(type.requiredEnums());
        }
        return enums;
    }

    @Override
    public String toString() {
        var s = new StringBuilder();
        s.append(String.format("%s %s(", resultType, name));
        s.append(paramTypes.stream()
            .map(t -> t.toString())
            .collect(Collectors.joining(", ")));
        s.append(");");
        return s.toString();
    }
}
