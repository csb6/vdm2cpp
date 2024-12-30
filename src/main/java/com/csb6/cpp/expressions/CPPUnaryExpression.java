package com.csb6.cpp.expressions;

import java.util.ArrayList;
import java.util.List;

import com.fujitsu.vdmj.tc.expressions.TCNotExpression;
import com.fujitsu.vdmj.tc.expressions.TCUnaryMinusExpression;
import com.fujitsu.vdmj.tc.expressions.TCUnaryPlusExpression;

public class CPPUnaryExpression extends CPPExpression {

    public String operator;
    public CPPExpression subexpr;

    public CPPUnaryExpression(TCUnaryPlusExpression expr, CPPExpression subexpr) {
        this.subexpr = subexpr;
        this.operator = "+";
    }

    public CPPUnaryExpression(TCUnaryMinusExpression expr, CPPExpression subexpr) {
        this.subexpr = subexpr;
        this.operator = "-";
    }

    public CPPUnaryExpression(TCNotExpression expr, CPPExpression subexpr) {
        this.subexpr = subexpr;
        this.operator = "!";
    }

    @Override
    public List<CPPExpression> flatten() {
        var list = new ArrayList<CPPExpression>();
        list.add(this);
        return list;
    }

    @Override
    public String toString() {
        return String.format("%s%s", operator, subexpr);
    }
}
