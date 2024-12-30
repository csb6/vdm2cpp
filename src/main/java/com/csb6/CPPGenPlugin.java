package com.csb6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.csb6.cpp.definitions.CPPDefinition;
import com.fujitsu.vdmj.lex.Dialect;
import com.fujitsu.vdmj.mapper.ClassMapper;
import com.fujitsu.vdmj.messages.VDMMessage;
import com.fujitsu.vdmj.plugins.AnalysisEvent;
import com.fujitsu.vdmj.plugins.AnalysisPlugin;
import com.fujitsu.vdmj.plugins.EventListener;
import com.fujitsu.vdmj.plugins.PluginRegistry;
import com.fujitsu.vdmj.plugins.analyses.TCPluginSL;
import com.fujitsu.vdmj.plugins.events.CheckCompleteEvent;
import com.fujitsu.vdmj.tc.modules.TCModule;
import com.fujitsu.vdmj.tc.modules.TCModuleList;

public class CPPGenPlugin extends AnalysisPlugin implements EventListener {

    public static final String TC_CPP_MAPPINGS = "tc-cpp.mappings";

    public static CPPGenPlugin factory(Dialect dialect)
    {
        if(dialect == Dialect.VDM_SL) {
            return new CPPGenPlugin();
        } else {
            return null;
        }
    }

    private ClassMapper mapper;

    @Override
    public String getName() {
        return "CPPGenPlugin";
    }

    @Override
    public String getDescription()
    {
        return "Generates C++ code that implements a VDM-SL specification.";
    }

    @Override
    public void init() {
        eventhub.register(CheckCompleteEvent.class, this);
    }

    @Override
    public List<VDMMessage> handleEvent(AnalysisEvent event) throws Exception {
        if(event instanceof CheckCompleteEvent) {
            TCPluginSL tcPlugin = PluginRegistry.getInstance().getPlugin("TC");
            TCModuleList moduleList = tcPlugin.getTC();
            mapper = ClassMapper.getInstance(TC_CPP_MAPPINGS);
            for(var module : moduleList) {
                try {
                    translateModule(module);
                } catch(Exception err) {
                    if(err.getMessage().startsWith("No mapping for")) {
                        System.err.println("Error: " + err.getMessage());
                    } else {
                        err.printStackTrace();
                    }
                    continue;
                }
            }
            return null;
        } else {
            throw new Exception("Unhandled event: " + event);
        }
    }

    private void translateModule(TCModule module) throws Exception {
        System.out.println("Processing module '" + module.name + "'...");

        mapper.init();

        // Collect CPPDefinitions
        var cppDefs = new ArrayList<CPPDefinition>();
        for (var def : module.defs) {
            cppDefs.add(mapper.convert(def));
        }

        // Collect and print headers
        var headers = new HashSet<String>();
        for(var def : cppDefs) {
            def.collectRequiredHeaders(headers);
        }
        headers.stream()
            .sorted()
            .forEach(header -> System.out.println(String.format("#include <%s>", header)));
        System.out.println();

        // Collect and print enums
        System.out.println("enum {");
        var enums = new HashSet<String>();
        for(var def : cppDefs) {
            def.collectRequiredEnums(enums);
        }
        enums.stream()
            .sorted()
            .forEach(e -> System.out.println(String.format("  %s,", e)));
        System.out.println("};");

        // Print CPPDefinitions
        for (var def : cppDefs) {
            System.out.println(def);
        }
    }
}