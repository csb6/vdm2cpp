package com.csb6.cpp.expressions;

import java.util.List;
import java.util.Set;

import com.fujitsu.vdmj.mapper.Mappable;

public abstract class CPPExpression implements Mappable {

    public void collectRequiredHeaders(Set<String> headers) {}

    public void collectRequiredEnums(Set<String> enums) {}

    public abstract List<CPPExpression> flatten();

}
