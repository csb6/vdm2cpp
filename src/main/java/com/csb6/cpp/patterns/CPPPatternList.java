package com.csb6.cpp.patterns;

import java.util.ArrayList;

import com.csb6.CPPGenPlugin;
import com.fujitsu.vdmj.mapper.ClassMapper;
import com.fujitsu.vdmj.mapper.Mappable;
import com.fujitsu.vdmj.tc.patterns.TCPatternList;

public class CPPPatternList extends ArrayList<CPPPattern> implements Mappable {

    public CPPPatternList(TCPatternList patterns) throws Exception {
        super();
        var mapper = ClassMapper.getInstance(CPPGenPlugin.TC_CPP_MAPPINGS);
        for(var pattern : patterns) {
            add((CPPPattern)mapper.convert(pattern));
        }
    }
}
