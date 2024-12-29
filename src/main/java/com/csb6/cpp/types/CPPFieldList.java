package com.csb6.cpp.types;

import com.csb6.CPPGenPlugin;
import com.fujitsu.vdmj.mapper.MappedList;
import com.fujitsu.vdmj.tc.types.TCField;
import com.fujitsu.vdmj.tc.types.TCFieldList;

public class CPPFieldList extends MappedList<TCField, CPPField> {

    public CPPFieldList() {
		super();
	}

	public CPPFieldList(TCFieldList from) throws Exception {
		super(CPPGenPlugin.TC_CPP_MAPPINGS, from);
	}
}
