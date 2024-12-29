package com.csb6.cpp.types;

import java.util.Set;

import com.fujitsu.vdmj.mapper.Mappable;

public abstract class CPPType implements Mappable {

    public Set<String> requiredHeaders() {
        return Set.of();
    }

    public Set<String> requiredEnums() {
        return Set.of();
    }
}
