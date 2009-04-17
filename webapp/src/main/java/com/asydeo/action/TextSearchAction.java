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


@UrlBinding("/search/text")
public class TextSearchAction extends SearchAction {

    String text;
    
    
    @DefaultHandler
    public Resolution start() {
        if ( text != null && ! text.isEmpty() ) {
            textSearch();
        
            return new ForwardResolution("/textSearch.jsp");
        }
        else {
            return new ForwardResolution("/textSearch.jsp");
        }
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    private void textSearch() {
        String sparqlStr = asydeoPrefix +
          System.getProperty("line.separator") +
          rdfsPrefix + System.getProperty("line.separator") +
          "SELECT * " +
          "WHERE { " +
          "?x " + "rdfs:label ?label . " +
          "FILTER regex(?label, \"" + text + "\", \"i\")" +
          "}";

        query(sparqlStr);
    }
}