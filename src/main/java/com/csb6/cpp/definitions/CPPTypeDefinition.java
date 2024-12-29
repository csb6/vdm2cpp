package com.csb6.cpp.definitions;

import java.util.Set;

import com.csb6.cpp.types.CPPDefType;

public class CPPTypeDefinition extends CPPDefinition {

    public CPPDefType type;

    public CPPTypeDefinition(CPPDefType type) {
        this.type = type;
    }

    @Override
    public Set<String> requiredHeaders() {
        return type.requiredHeaders();
    }

    @Override
    public Set<String> requiredEnums() {
        return type.requiredEnums();
    }

    @Override
    public String toString() {
        return type.toDefString();
    }
}
