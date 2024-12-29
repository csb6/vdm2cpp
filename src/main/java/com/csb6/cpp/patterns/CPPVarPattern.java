package com.csb6.cpp.patterns;

import com.fujitsu.vdmj.tc.patterns.TCIdentifierPattern;
import com.fujitsu.vdmj.tc.patterns.TCIgnorePattern;

public class CPPVarPattern extends CPPPattern {

    public String name;

    public CPPVarPattern(TCIdentifierPattern pattern) throws Exception {
        this.name = pattern.name.getName();
    }

    public CPPVarPattern(TCIgnorePattern pattern) {
        this.name = "";
    }

    @Override
    public String toString() {
        return name;
    }
}
