package com.asydeo.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;


@UrlBinding("/search/sparql")
public class SparqlSearchAction extends SearchAction {

    String sparql;
    
    
    @DefaultHandler
    public Resolution start() {
        if ( sparql != null && ! sparql.isEmpty() ) {
            sparqlSearch();
        
            return new ForwardResolution("/sparqlSearch.jsp");
        }
        else {
            return new ForwardResolution("/sparqlSearch.jsp");
        }
    }
    
    public String getSparql() {
        return sparql;
    }
    
    public void setSparql(String sparql) {
        this.sparql = sparql;
    }
    
    private void sparqlSearch() {
        query(sparql);
    }
}