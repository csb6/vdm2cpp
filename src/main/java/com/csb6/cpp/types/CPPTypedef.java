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
    public Set<String> requiredHeaders() {
        return type.requiredHeaders();
    }

    @Override
    public Set<String> requiredEnums() {
        return type.requiredEnums();
    }

}
