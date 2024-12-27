package com.csb6.visitors;

import java.util.Set;

import com.csb6.GenerationError;
import com.fujitsu.vdmj.tc.definitions.TCDefinition;
import com.fujitsu.vdmj.tc.definitions.TCExplicitFunctionDefinition;
import com.fujitsu.vdmj.tc.definitions.TCLocalDefinition;
import com.fujitsu.vdmj.tc.definitions.TCTypeDefinition;
import com.fujitsu.vdmj.tc.definitions.visitors.TCDefinitionVisitor;

public class GenDefVisitor extends TCDefinitionVisitor<Void, Void> {

    public StringBuilder out;
    public Set<String> includes;
    public Set<String> enums;

    public GenDefVisitor(StringBuilder out, Set<String> includes, Set<String> enums) {
        this.out = out;
        this.includes = includes;
        this.enums = enums;
    }

    @Override
    public Void caseTypeDefinition(TCTypeDefinition def, Void arg) {
        def.type.apply(new GenTypeDefVisitor(out, includes, enums), null);
        out.append(";\n");
        return null;
    }

    @Override
    public Void caseLocalDefinition(TCLocalDefinition def, Void arg) {
        def.type.apply(new GenTypeVisitor(out, includes, enums), null);
        out.append(" " + def.name);
        return null;
    }

    @Override
    public Void caseExplicitFunctionDefinition(TCExplicitFunctionDefinition def, Void arg) {
        if(def.isCurried) {
            throw new GenerationError(def, "curried functions are not supported");
        }
        var typeVisitor = new GenTypeVisitor(out, includes, enums);
        def.type.result.apply(typeVisitor, null);
        out.append(String.format(" %s(", def.name));
        var paramList = def.paramDefinitionList.get(0);
        applyDefList(paramList, paramList.size());
        out.append(");\n");
        return null;
    }

    @Override
    public Void caseDefinition(TCDefinition def, Void arg) {
        throw new GenerationError(def);
    }

    private void applyDefList(Iterable<TCDefinition> list, int length)
    {
        for(var member : list) {
            member.apply(this, null);
            out.append(", ");
        }
        if(length > 0) {
            out.delete(out.length() - 2, out.length());
        }
    }
}
