package com.csb6.cpp.types;

import java.util.ArrayList;

import com.csb6.CPPGenPlugin;
import com.fujitsu.vdmj.mapper.ClassMapper;
import com.fujitsu.vdmj.mapper.Mappable;
import com.fujitsu.vdmj.tc.types.TCType;
import com.fujitsu.vdmj.tc.types.TCTypeList;

public class CPPTypeList extends ArrayList<CPPType> implements Mappable {

    // Needed so ClassMapper can match TCTypeList
    public CPPTypeList(TCTypeList types) throws Exception {
        this((Iterable<TCType>)types);
    }

    public CPPTypeList(Iterable<TCType> types) throws Exception {
        super();
        var mapper = ClassMapper.getInstance(CPPGenPlugin.TC_CPP_MAPPINGS);
        for(var type : types) {
            add((CPPType)mapper.convert(type));
        }
    }
}
