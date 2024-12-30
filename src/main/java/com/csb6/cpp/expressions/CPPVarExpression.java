package com.csb6.cpp.expressions;

import java.util.ArrayList;
import java.util.List;

import com.fujitsu.vdmj.tc.lex.TCNameToken;

public class CPPVarExpression extends CPPExpression {

    public String name;

    public CPPVarExpression(TCNameToken name) {
        this.name = name.toString();
    }

    @Override
    public List<CPPExpression> flatten() {
        var list = new ArrayList<CPPExpression>();
        list.add(this);
        return list;
    }

    @Override
    public String toString() {
        return name;
    }

    // No need for collectRequiredEnums() or collectRequiredHeaders() overrides
    // since the definition of the variable will already have added them
}
