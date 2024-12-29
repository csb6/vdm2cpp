package com.csb6.cpp.types;

import java.util.Set;
import java.util.HashSet;

import com.fujitsu.vdmj.tc.lex.TCNameToken;

public class CPPStructType extends CPPDefType {

    public TCNameToken name;
    public CPPFieldList fields;

    public CPPStructType(TCNameToken name, CPPFieldList fields) {
        this.name = name;
        this.fields = fields;
    }

    @Override
    public Set<String> requiredHeaders() {
        var headers = new HashSet<String>();
        for(var field : fields) {
            headers.addAll(field.type.requiredHeaders());
        }
        return headers;
    }

    @Override
    public Set<String> requiredEnums() {
        var enums = new HashSet<String>();
        for(var field : fields) {
            enums.addAll(field.type.requiredEnums());
        }
        return enums;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public String toDefString() {
        var s = new StringBuilder();
        s.append(String.format("struct %s {\n", name));
        for(var field : fields) {
            s.append(String.format("  %s;\n", field));
        }
        s.append("};");
        return s.toString();
    }
}
