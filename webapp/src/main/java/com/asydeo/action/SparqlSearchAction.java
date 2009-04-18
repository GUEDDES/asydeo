package com.asydeo.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;


@UrlBinding("/search/sparql")
public class SparqlSearchAction extends SearchAction {

    String q;     // query string

    
    @DefaultHandler
    public Resolution start() {
        if ( q != null && ! q.isEmpty() ) {
            sparqlSearch();
        }
        
        return new ForwardResolution("/sparqlSearch.jsp");
    }
    
    private void sparqlSearch() {
        query(q);
    }
    
    public String getQ() {
        return q;
    }
    
    public void setQ(String q) {
        this.q = q;
    }
}