package com.csb6.cpp.types;

import java.util.Set;

public class CPPEnumType extends CPPType {

    public String value;

    public CPPEnumType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public void collectRequiredEnums(Set<String> enums) {
        enums.add(value);
    }
}
