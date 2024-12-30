package com.csb6.cpp.expressions;

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
}
