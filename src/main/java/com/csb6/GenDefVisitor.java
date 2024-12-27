package com.csb6;

import java.util.Set;

import com.fujitsu.vdmj.tc.definitions.TCDefinition;
import com.fujitsu.vdmj.tc.definitions.TCTypeDefinition;
import com.fujitsu.vdmj.tc.definitions.visitors.TCDefinitionVisitor;

public class GenDefVisitor extends TCDefinitionVisitor<Void, Void> {

    public StringBuilder out;
    public Set<String> includes;

    public GenDefVisitor(StringBuilder out, Set<String> includes) {
        this.out = out;
        this.includes = includes;
    }

    @Override
    public Void caseTypeDefinition(TCTypeDefinition node, Void arg) {
        node.type.apply(new GenTypeDefVisitor(out, includes), null);
        out.append(";\n");
        return null;
    }

    @Override
    public Void caseDefinition(TCDefinition node, Void arg) {
        throw new GenerationError("Definition '" + node.name + "' not translated (unsupported definition type)");
    }

}
