package com.csb6.cpp.types;

import java.util.Set;

import com.fujitsu.vdmj.mapper.Mappable;

public abstract class CPPType implements Mappable {

    public void collectRequiredHeaders(Set<String> headers) {}
    public void collectRequiredEnums(Set<String> enums) {}
}
