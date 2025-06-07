package com.working.httpServer.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.working.httpServer.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ConfigManager {
    private static ConfigManager myConfigManager;
    private static Configration myCurrentConfig;

    private ConfigManager(){

    }

    private static ConfigManager getConfigManager(){
        if( myConfigManager == null){
            myConfigManager = new ConfigManager();
        }
        return myConfigManager;
    }

    /*
    * used to load a file from the path provided
    *  */
    private void loadConfig(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        StringBuffer sb = new StringBuffer();
        int i;
        while((i= fileReader.read())!=-1){
            sb.append((char)i);
        }
        JsonNode conf = Json.parse(sb.toString());
        myCurrentConfig = Json.fromJson(conf,Configration.class);
    }

    /*
    *returns the current loaded configration
     */
    private void getCurrentConfig(){

    }
}
