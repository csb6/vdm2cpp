package com.csb6.cpp.types;

import java.util.Set;
import java.util.stream.Collectors;

public class CPPVariantType extends CPPType {

    public CPPTypeSet types;

    public CPPVariantType(CPPTypeSet types) {
        this.types = types;
    }

    @Override
    public void collectRequiredHeaders(Set<String> headers) {
        for(var type : types) {
            type.collectRequiredHeaders(headers);
        }
        headers.add("variant");
    }

    @Override
    public void collectRequiredEnums(Set<String> enums) {
        for(var type : types) {
            type.collectRequiredEnums(enums);
        }
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
