package com.csb6.visitors;

import java.util.Set;

import com.csb6.GenerationError;
import com.fujitsu.vdmj.tc.definitions.TCDefinition;
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
    public Void caseTypeDefinition(TCTypeDefinition node, Void arg) {
        node.type.apply(new GenTypeDefVisitor(out, includes, enums), null);
        out.append(";\n");
        return null;
    }

    @Override
    public Void caseDefinition(TCDefinition node, Void arg) {
        throw new GenerationError("Definition '" + node.name + "' not translated (unsupported definition type)");
    }

}
