package com.csb6.cpp.types;

import com.fujitsu.vdmj.tc.types.TCTypeSet;

public class CPPTypeSet extends CPPTypeList {

    // Assumes TCTypeSet has already eliminated duplicates
    public CPPTypeSet(TCTypeSet types) throws Exception {
        super(types);
    }
}
