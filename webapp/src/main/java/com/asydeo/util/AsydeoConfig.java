package com.asydeo.util;


public class AsydeoConfig extends Config {
    
    public AsydeoConfig() {
        super();
    }
    
    public static String getAsydeoNS() {
        return getValue("ontology.asydeo.namespace");
    }
    public static String getAsydeoPrefix() {
        return getValue("ontology.asydeo.prefix");
    }
    public static String getAsydeoPrefixString() {
        return "PREFIX " + getAsydeoPrefix() + ": <" + getAsydeoNS() + ">";
    }
    
    public static String getDefaultPrefixString() {
        String ns = getValue("ontology.default.namespace");
        return "PREFIX : <" + ns + ">";
    }
    
    public static String getRdfsPrefix() {
        return getValue("ontology.other.rdfs.prefix");
    }
    // TODO: Modify to return a string of all "other" ontologies
    public static String getRdfsPrefixString() {
        String ns = getValue("ontology.other.rdfs.namespace");
        return "PREFIX " + getRdfsPrefix() + ": <" + ns + ">";
    }
    
    public static String getDefaultLang() {
        return getValue("user.default.language");
    }
}
