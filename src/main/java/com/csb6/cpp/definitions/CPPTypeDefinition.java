package com.csb6.cpp.definitions;

import java.util.Set;

import com.csb6.cpp.types.CPPDefType;

public class CPPTypeDefinition extends CPPDefinition {

    public CPPDefType type;

    public CPPTypeDefinition(CPPDefType type) {
        this.type = type;
    }

    @Override
    public void collectRequiredHeaders(Set<String> headers) {
        type.collectRequiredHeaders(headers);
    }

    @Override
    public void collectRequiredEnums(Set<String> enums) {
        type.collectRequiredEnums(enums);
    }

    @Override
    public String toString() {
        return type.toDefString();
    }
}
