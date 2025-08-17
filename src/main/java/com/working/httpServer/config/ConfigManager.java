package com.working.httpServer.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.working.httpServer.util.Json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//singleton class
public class ConfigManager {
    private static ConfigManager myConfigManager;
    private static Configration myCurrentConfig;

    private ConfigManager(){

    }

    public static ConfigManager getInstance(){
        if( myConfigManager == null){
            myConfigManager = new ConfigManager();
        }
        return myConfigManager;
    }

    /*
    * used to load a file from the path provided
    *  */
    public void loadConfig(String path)  {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(path);
        }catch (FileNotFoundException e){
            throw new HTTPConfigrationException(e);
        }
        StringBuffer sb = new StringBuffer();
        int i;
        try {
            while((i= fileReader.read())!=-1){
                sb.append((char)i);
            }
        }catch (Exception e){
            throw new HTTPConfigrationException(e);
        }
        JsonNode conf = null;
        try {
            conf = Json.parse(sb.toString());
        } catch (IOException e) {
            throw new HTTPConfigrationException("Error parsing the config file",e);
        }
        try {
            myCurrentConfig = Json.fromJson(conf,Configration.class);
        } catch (JsonProcessingException e) {
            throw new HTTPConfigrationException("Error parsing config file internal",e);
        }
    }

    /*
    *returns the current loaded configration
     */
    public Configration getCurrentConfig(){
        if(myCurrentConfig==null){
            throw new HTTPConfigrationException("No config set");
        }
        return myCurrentConfig;
    }
}
