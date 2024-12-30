package com.csb6.cpp.types;

import java.util.Set;

import com.fujitsu.vdmj.tc.lex.TCNameToken;

public class CPPTypedef extends CPPDefType {

    public TCNameToken name;
    public CPPType type;

    public CPPTypedef(TCNameToken name, CPPType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public String toDefString() {
        return String.format("using %s = %s;", name, type);
    }

    @Override
    public void collectRequiredHeaders(Set<String> headers) {
        type.collectRequiredHeaders(headers);
    }

    @Override
    public void collectRequiredEnums(Set<String> enums) {
        type.collectRequiredEnums(enums);
    }

}
