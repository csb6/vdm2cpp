package com.csb6.cpp.expressions;

import java.util.ArrayList;
import java.util.List;

import com.fujitsu.vdmj.tc.expressions.TCAndExpression;
import com.fujitsu.vdmj.tc.expressions.TCDivideExpression;
import com.fujitsu.vdmj.tc.expressions.TCEquivalentExpression;
import com.fujitsu.vdmj.tc.expressions.TCGreaterEqualExpression;
import com.fujitsu.vdmj.tc.expressions.TCGreaterExpression;
import com.fujitsu.vdmj.tc.expressions.TCLessEqualExpression;
import com.fujitsu.vdmj.tc.expressions.TCLessExpression;
import com.fujitsu.vdmj.tc.expressions.TCOrExpression;
import com.fujitsu.vdmj.tc.expressions.TCPlusExpression;
import com.fujitsu.vdmj.tc.expressions.TCSubtractExpression;
import com.fujitsu.vdmj.tc.expressions.TCTimesExpression;

public class CPPBinaryExpression extends CPPAbstractBinaryExpression {

    public String operator;
    public CPPExpression left, right;

    public CPPBinaryExpression(CPPExpression left, TCAndExpression expr, CPPExpression right) {
        this(left, "&&", right);
    }

    public CPPBinaryExpression(CPPExpression left, TCOrExpression expr, CPPExpression right) {
        this(left, "||", right);
    }

    public CPPBinaryExpression(CPPExpression left, TCEquivalentExpression expr, CPPExpression right) {
        this(left, "==", right);
    }

    public CPPBinaryExpression(CPPExpression left, TCDivideExpression expr, CPPExpression right) {
        this(left, "/", right);
    }

    public CPPBinaryExpression(CPPExpression left, TCGreaterEqualExpression expr, CPPExpression right) {
        this(left, ">=", right);
    }

    public CPPBinaryExpression(CPPExpression left, TCGreaterExpression expr, CPPExpression right) {
        this(left, ">", right);
    }

    public CPPBinaryExpression(CPPExpression left, TCLessEqualExpression expr, CPPExpression right) {
        this(left, "<=", right);
    }

    public CPPBinaryExpression(CPPExpression left, TCLessExpression expr, CPPExpression right) {
        this(left, "<", right);
    }

    public CPPBinaryExpression(CPPExpression left, TCPlusExpression expr, CPPExpression right) {
        this(left, "+", right);
    }

    public CPPBinaryExpression(CPPExpression left, TCSubtractExpression expr, CPPExpression right) {
        this(left, "-", right);
    }

    public CPPBinaryExpression(CPPExpression left, TCTimesExpression expr, CPPExpression right) {
        this(left, "*", right);
    }

    private CPPBinaryExpression(CPPExpression left, String op, CPPExpression right) {
        this.left = left;
        this.operator = op;
        this.right = right;
    }

    @Override
    public List<CPPExpression> flatten() {
        var list = new ArrayList<CPPExpression>();
        list.add(this);
        return list;
    }

    @Override
    public String toString() {
        return String.format("(%s %s %s)", left, operator, right);
    }

}
