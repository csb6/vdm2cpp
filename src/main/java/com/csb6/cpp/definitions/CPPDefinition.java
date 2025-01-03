package com.csb6.cpp.definitions;

import java.util.Set;

import com.fujitsu.vdmj.mapper.Mappable;

public abstract class CPPDefinition implements Mappable {

    public abstract void collectRequiredHeaders(Set<String> headers);

    public abstract void collectRequiredEnums(Set<String> enums);
}
