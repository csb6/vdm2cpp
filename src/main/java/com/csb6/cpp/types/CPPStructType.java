package com.csb6.cpp.types;

import java.util.Set;

import com.fujitsu.vdmj.tc.lex.TCNameToken;

public class CPPStructType extends CPPDefType {

    public TCNameToken name;
    public CPPFieldList fields;

    public CPPStructType(TCNameToken name, CPPFieldList fields) {
        this.name = name;
        this.fields = fields;
    }

    @Override
    public void collectRequiredHeaders(Set<String> headers) {
        for(var field : fields) {
            field.type.collectRequiredHeaders(headers);
        }
    }

    @Override
    public void collectRequiredEnums(Set<String> enums) {
        for(var field : fields) {
            field.type.collectRequiredEnums(enums);
        }
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
