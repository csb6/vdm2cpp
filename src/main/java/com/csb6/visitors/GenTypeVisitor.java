package com.csb6.visitors;

import java.util.Set;

import com.csb6.GenerationError;
import com.fujitsu.vdmj.tc.types.TCBooleanType;
import com.fujitsu.vdmj.tc.types.TCCharacterType;
import com.fujitsu.vdmj.tc.types.TCFunctionType;
import com.fujitsu.vdmj.tc.types.TCInMapType;
import com.fujitsu.vdmj.tc.types.TCIntegerType;
import com.fujitsu.vdmj.tc.types.TCMapType;
import com.fujitsu.vdmj.tc.types.TCNamedType;
import com.fujitsu.vdmj.tc.types.TCNaturalOneType;
import com.fujitsu.vdmj.tc.types.TCNaturalType;
import com.fujitsu.vdmj.tc.types.TCOptionalType;
import com.fujitsu.vdmj.tc.types.TCProductType;
import com.fujitsu.vdmj.tc.types.TCQuoteType;
import com.fujitsu.vdmj.tc.types.TCRationalType;
import com.fujitsu.vdmj.tc.types.TCRealType;
import com.fujitsu.vdmj.tc.types.TCRecordType;
import com.fujitsu.vdmj.tc.types.TCSeq1Type;
import com.fujitsu.vdmj.tc.types.TCSeqType;
import com.fujitsu.vdmj.tc.types.TCSet1Type;
import com.fujitsu.vdmj.tc.types.TCSetType;
import com.fujitsu.vdmj.tc.types.TCType;
import com.fujitsu.vdmj.tc.types.TCUnionType;
import com.fujitsu.vdmj.tc.types.visitors.TCTypeVisitor;

public class GenTypeVisitor extends TCTypeVisitor<Void, Void> {

    public StringBuilder out;
    public Set<String> includes;
    public Set<String> enums;

    GenTypeVisitor(StringBuilder out, Set<String> includes, Set<String> enums)
    {
        this.out = out;
        this.includes = includes;
        this.enums = enums;
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
    public Void caseIntegerType(TCIntegerType type, Void arg) {
        // TODO: need to make this arbitrary precision
        out.append("int");
        return null;
    }

    @Override
    public Void caseRealType(TCRealType type, Void arg) {
        // TODO: need to make this arbitrary precision
        out.append("double");
        return null;
    }

    @Override
    public Void caseRationalType(TCRationalType type, Void arg) {
        // TODO: need to make this arbitrary precision
        out.append("double");
        return null;
    }

    @Override
    public Void caseQuoteType(TCQuoteType type, Void arg) {
        enums.add(type.value);
        out.append("int");
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
    public Void caseSeq1Type(TCSeq1Type type, Void arg) {
        return caseSeqType(type, arg);
    }

    @Override
    public Void caseSetType(TCSetType type, Void arg) {
        includes.add("set");
        out.append("std::set<");
        type.setof.apply(this, null);
        out.append(">");
        return null;
    }

    @Override
    public Void caseSet1Type(TCSet1Type type, Void arg) {
        return caseSetType(type, arg);
    }

    @Override
    public Void caseMapType(TCMapType type, Void arg) {
        includes.add("map");
        out.append("std::map<");
        type.from.apply(this, null);
        out.append(", ");
        type.to.apply(this, null);
        out.append(">");
        return null;
    }

    @Override
    public Void caseInMapType(TCInMapType type, Void arg) {
        return caseMapType(type, arg);
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
        applyList(type.types, type.types.size());
        out.append(">");
        return null;
    }

    @Override
    public Void caseOptionalType(TCOptionalType type, Void arg) {
        includes.add("optional");
        out.append("std::optional<");
        type.type.apply(this, null);
        out.append(">");
        return null;
    }

    @Override
    public Void caseProductType(TCProductType type, Void arg) {
        includes.add("tuple");
        out.append("std::tuple<");
        applyList(type.types, type.types.size());
        out.append(">");
        return null;
    }

    @Override
    public Void caseFunctionType(TCFunctionType type, Void arg) {
        includes.add("functional");
        out.append("std::function<");
        type.result.apply(this, null);
        out.append("(");
        applyList(type.parameters, type.parameters.size());
        out.append(")>");
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

    private void applyList(Iterable<TCType> list, int length)
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
