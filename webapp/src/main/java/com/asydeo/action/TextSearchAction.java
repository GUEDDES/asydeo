package com.asydeo.action;

import static com.asydeo.util.AsydeoConfig.*;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


@UrlBinding("/search/text")
public class TextSearchAction extends SearchAction {

    String q;     // query string
    
    @DefaultHandler
    public Resolution start() {
        if ( q != null && ! q.isEmpty() ) {
            textSearch();
        }
        
        return new ForwardResolution("/textSearch.jsp");
    }
    
    public void textSearch() {
        String sparqlStr = getAsydeoPrefixString() +
          System.getProperty("line.separator") +
          getRdfsPrefixString() + System.getProperty("line.separator") +
          "SELECT * " +
          "WHERE { " +
          "?x " + getRdfsPrefix() + ":label ?label . " +
          "FILTER regex(?label, \"" + q + "\", \"i\")" +
          "}";

        query(sparqlStr);
    }
    
    public String getQ() {
        return q;
    }
    
    public void setQ(String q) {
        this.q = q;
    }
}