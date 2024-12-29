package com.csb6.cpp.patterns;

import java.util.ArrayList;

import com.csb6.CPPGenPlugin;
import com.fujitsu.vdmj.mapper.ClassMapper;
import com.fujitsu.vdmj.mapper.Mappable;
import com.fujitsu.vdmj.tc.patterns.TCPatternListList;

public class CPPPatternListList extends ArrayList<CPPPatternList> implements Mappable {

    public CPPPatternListList(TCPatternListList patterns) throws Exception {
        super();
        var mapper = ClassMapper.getInstance(CPPGenPlugin.TC_CPP_MAPPINGS);
        for(var patternList : patterns) {
            add((CPPPatternList)mapper.convert(patternList));
        }
    }
}
