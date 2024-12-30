package com.csb6.cpp.definitions;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.csb6.cpp.expressions.CPPExpression;
import com.csb6.cpp.patterns.CPPPatternList;
import com.csb6.cpp.patterns.CPPPatternListList;
import com.csb6.cpp.types.CPPFunctionType;
import com.csb6.cpp.types.CPPType;
import com.csb6.cpp.types.CPPTypeList;
import com.fujitsu.vdmj.tc.lex.TCNameToken;

public class CPPFunctionDefinition extends CPPDefinition {

    public CPPType resultType;
    public TCNameToken name;
    public CPPTypeList paramTypes;
    public CPPPatternList params;
    public CPPExpression body;

    public CPPFunctionDefinition(CPPFunctionType type, TCNameToken name, CPPPatternListList paramPatternList, boolean isCurried, CPPExpression body) throws Exception {
        this.resultType = type.resultType;
        this.name = name;
        this.paramTypes = type.paramTypes;
        this.body = body;

        if(isCurried) {
            throw new Exception("Cannot translate function '" + name + "' (curried functions are not supported)");
        }

        if(paramPatternList.size() != 1) {
            throw new Exception("Cannot translate function '" + name + "' (functions with multiple pattern lists are not supported)");
        }

        this.params = paramPatternList.get(0);
        if(this.params.size() != this.paramTypes.size()) {
            throw new Exception(String.format("Function '%s': Number of parameters and parameter types do not match", name));
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
        s.append(IntStream.range(0, params.size())
            .mapToObj(i -> {
                var paramName = params.get(i).toString();
                if(paramName.isEmpty()) {
                    return paramTypes.get(i).toString();
                } else {
                    return String.format("%s %s", paramTypes.get(i), paramName);
                }
            })
            .collect(Collectors.joining(", ")));
        s.append(")\n{\n");
        var stmtList = body.flatten();
        if(stmtList.size() > 1) {
            for(var stmt : stmtList.subList(0, stmtList.size() - 1)) {
                s.append(String.format("  %s;\n", stmt));
            }
        }
        s.append(String.format("  return %s;\n", stmtList.getLast()));
        s.append("}");
        return s.toString();
    }
}
