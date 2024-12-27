package com.csb6;

import java.util.HashSet;
import java.util.List;

import com.csb6.visitors.GenDefVisitor;
import com.fujitsu.vdmj.lex.Dialect;
import com.fujitsu.vdmj.messages.VDMMessage;
import com.fujitsu.vdmj.plugins.AnalysisEvent;
import com.fujitsu.vdmj.plugins.AnalysisPlugin;
import com.fujitsu.vdmj.plugins.EventListener;
import com.fujitsu.vdmj.plugins.PluginRegistry;
import com.fujitsu.vdmj.plugins.analyses.TCPluginSL;
import com.fujitsu.vdmj.plugins.events.CheckCompleteEvent;
import com.fujitsu.vdmj.tc.modules.TCModuleList;

public class CPPGenPlugin extends AnalysisPlugin implements EventListener {

    public static CPPGenPlugin factory(Dialect dialect)
    {
        if(dialect == Dialect.VDM_SL) {
            return new CPPGenPlugin();
        } else {
            return null;
        }
    }

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
            for(var module : moduleList) {
                System.out.println("Processing module '" + module.name + "'...");
                var includes = new HashSet<String>();
                var enums = new HashSet<String>();
                var output = new StringBuilder();
                try {
                    for (var def : module.defs) {
                        def.apply(new GenDefVisitor(output, includes, enums), null);
                    }
                } catch(GenerationError err) {
                    System.out.println("Error: " + err.getMessage());
                    continue;
                }

                includes.stream()
                    .sorted()
                    .forEach(i -> System.out.println(String.format("#include <%s>", i)));
                System.out.println();
                System.out.println("enum {");
                enums.stream()
                    .sorted()
                    .forEach(e -> System.out.println(String.format("  %s,", e)));
                System.out.println("};\n");
                System.out.println(output.toString());
            }
            return null;
        } else {
            throw new Exception("Unhandled event: " + event);
        }
    }

}