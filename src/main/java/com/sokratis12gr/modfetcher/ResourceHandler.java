package com.sokratis12gr.modfetcher;

import com.sokratis12gr.modfetcher.util.NamedRegistry;

public class ResourceHandler {
    
    public static NamedRegistry<String> RESOURCES = new NamedRegistry<>();
    
    public ResourceHandler() {
        
        RESOURCES.clear();
        RESOURCES.registerValue("Command_Syntax", "http://i.imgur.com/lqocx7O.png");
    }
}
