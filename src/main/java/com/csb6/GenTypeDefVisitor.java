package com.csb6;

import java.util.Set;

import com.fujitsu.vdmj.tc.types.TCNamedType;
import com.fujitsu.vdmj.tc.types.TCRecordType;
import com.fujitsu.vdmj.tc.types.TCType;
import com.fujitsu.vdmj.tc.types.visitors.TCTypeVisitor;

public class GenTypeDefVisitor extends TCTypeVisitor<Void, Void> {
    
    public StringBuilder out;
    public Set<String> includes;

    GenTypeDefVisitor(StringBuilder out, Set<String> includes)
    {
        this.out = out;
        this.includes = includes;
    }

    @Override
    public Void caseRecordType(TCRecordType type, Void arg) {
        checkRecursive(type);
        out.append(String.format("struct %s {\n", type.name));
        var fieldVisitor = new GenTypeVisitor(out, includes);
        for (var field : type.fields) {
            checkRecursive(field.type);
            out.append("  ");
            field.type.apply(fieldVisitor, null);
            out.append(String.format(" %s;\n", field.tag));
        }
        out.append("}");
        return null;
    }

    @Override
    public Void caseNamedType(TCNamedType type, Void arg) {
        checkRecursive(type);
        out.append(String.format("using %s = ", type.typename));
        type.type.apply(new GenTypeVisitor(out, includes), null);
        return null;
    }

    @Override
    public Void caseType(TCType type, Void arg) {
        throw new GenerationError("Type '" + type.toString() + "' not translated (unsupported type)");
    }

    private static void checkRecursive(TCType type) {
        if(type.isRecursive()) {
            throw new GenerationError("Recursive/infinite types are not supported ('" + type.toString()  + "')");
        }
    }
    
}
