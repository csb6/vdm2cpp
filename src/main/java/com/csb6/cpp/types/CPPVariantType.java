package com.csb6.cpp.types;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CPPVariantType extends CPPType {

    public CPPTypeSet types;

    public CPPVariantType(CPPTypeSet types) {
        this.types = types;
    }

    @Override
    public Set<String> requiredHeaders() {
        var headers = new HashSet<String>();
        for(var type : types) {
            headers.addAll(type.requiredHeaders());
        }
        headers.add("variant");
        return headers;
    }

    @Override
    public Set<String> requiredEnums() {
        var enums = new HashSet<String>();
        for(var type : types) {
            enums.addAll(type.requiredEnums());
        }
        return enums;
    }

    @Override
    public String toString() {
        var s = new StringBuilder();
        s.append("std::variant<");
        s.append(types.stream()
            .map(t -> t.toString())
            .collect(Collectors.joining(", ")));
        s.append(">");
        return s.toString();
    }
}