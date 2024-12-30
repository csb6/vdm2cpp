package com.csb6.cpp.expressions;

import java.util.List;

import com.fujitsu.vdmj.mapper.Mappable;

public abstract class CPPExpression implements Mappable {

    public abstract List<CPPExpression> flatten();

}
