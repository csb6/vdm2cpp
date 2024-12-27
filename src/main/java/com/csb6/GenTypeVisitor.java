package com.csb6;

import java.util.Set;

import com.fujitsu.vdmj.tc.types.TCBooleanType;
import com.fujitsu.vdmj.tc.types.TCCharacterType;
import com.fujitsu.vdmj.tc.types.TCNamedType;
import com.fujitsu.vdmj.tc.types.TCNaturalOneType;
import com.fujitsu.vdmj.tc.types.TCNaturalType;
import com.fujitsu.vdmj.tc.types.TCRecordType;
import com.fujitsu.vdmj.tc.types.TCSeqType;
import com.fujitsu.vdmj.tc.types.TCType;
import com.fujitsu.vdmj.tc.types.TCUnionType;
import com.fujitsu.vdmj.tc.types.visitors.TCTypeVisitor;

public class GenTypeVisitor extends TCTypeVisitor<Void, Void> {

    public StringBuilder out;
    public Set<String> includes;

    GenTypeVisitor(StringBuilder out, Set<String> includes)
    {
        this.out = out;
        this.includes = includes;
    }

    @Override
    public Void caseBooleanType(TCBooleanType type, Void context) {
        out.append("bool");
        return null;
    }

    @Override
    public Void caseCharacterType(TCCharacterType type, Void arg) {
        out.append("char");
        return null;
    }

    @Override
    public Void caseNaturalOneType(TCNaturalOneType type, Void arg) {
        // TODO: need to make this a BigInt
        out.append("unsigned int");
        return null;
    }

    @Override
    public Void caseNaturalType(TCNaturalType type, Void arg) {
        // TODO: need to make this a BigInt
        out.append("unsigned int");
        return null;
    }

    @Override
    public Void caseSeqType(TCSeqType type, Void arg) {
        includes.add("vector");
        out.append("std::vector<");
        type.seqof.apply(this, null);
        out.append(">");
        return null;
    }

    @Override
    public Void caseRecordType(TCRecordType type, Void arg) {
        out.append(type.name);
        return null;
    }

    @Override
    public Void caseUnionType(TCUnionType type, Void arg) {
        includes.add("variant");
        out.append("std::variant<");
        for(var member : type.types) {
            member.apply(this, null);
            out.append(", ");
        }
        if(!type.types.isEmpty()) {
            out.delete(out.length() - 2, out.length());
        }
        out.append(">");
        return null;
    }

    @Override
    public Void caseNamedType(TCNamedType type, Void arg) {
        out.append(type.typename);
        return null;
    }

    @Override
    public Void caseType(TCType type, Void arg) {
        throw new GenerationError("Type '" + type.toString() + "' not translated (unsupported type)");
    }
}
