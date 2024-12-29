package com.csb6.cpp.types;

import com.fujitsu.vdmj.mapper.Mappable;
import com.fujitsu.vdmj.tc.lex.TCNameToken;

public class CPPField implements Mappable {
    public TCNameToken name;
    public CPPType type;

    // TODO: create CPPNameToken class that takes into account namespaces, etc.
    public CPPField(TCNameToken name, CPPType type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("%s %s", type, name);
    }
}
