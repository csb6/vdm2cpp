package com.csb6.cpp.expressions;

import java.util.ArrayList;
import java.util.List;

import com.fujitsu.vdmj.tc.expressions.TCBooleanLiteralExpression;
import com.fujitsu.vdmj.tc.expressions.TCIntegerLiteralExpression;
import com.fujitsu.vdmj.tc.expressions.TCRealLiteralExpression;
import com.fujitsu.vdmj.tc.expressions.TCStringLiteralExpression;

public class CPPLiteralExpression extends CPPExpression {

    String value;

    public CPPLiteralExpression(TCBooleanLiteralExpression lit) {
        this.value = lit.toString();
    }

    public CPPLiteralExpression(TCIntegerLiteralExpression lit) {
        this.value = lit.toString();
    }

    public CPPLiteralExpression(TCRealLiteralExpression lit) {
        this.value = lit.toString();
    }

    public CPPLiteralExpression(TCStringLiteralExpression lit) {
        this.value = lit.toString();
    }

    @Override
    public List<CPPExpression> flatten()
    {
        var list = new ArrayList<CPPExpression>();
        list.add(this);
        return list;
    }

    @Override
    public String toString()
    {
        return value;
    }
}
