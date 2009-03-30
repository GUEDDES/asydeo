package com.asydeo.action;

import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asydeo.ontology.Asydeo;
import com.asydeo.view.OntView;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Resource;

import thewebsemantic.binding.Jenabean;


@UrlBinding("/asset/search")
public class SearchAction extends BaseAction {

    String uri;
    String sparqlQuery;
    ArrayList<OntView> queryResult = new ArrayList<OntView>();

    @DefaultHandler
    public Resolution start() {
        if ( sparqlQuery == null || sparqlQuery.isEmpty() ) {
            sparqlQuery = "PREFIX " + Asydeo.PREFIX + ": <" +
            Asydeo.NS + ">" + System.getProperty("line.separator");
        }
        else {
            Query query = null;
            QueryExecution qexec = null;

            try {
                query = QueryFactory.create(sparqlQuery);
            }
            catch (Exception e) {
                System.out.println("Error: " + e.toString());
            }
            
            if ( query != null && query.isSelectType() ) {
                try {
                    qexec = QueryExecutionFactory.create(
                              query, m());
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.toString());
                }
                
                try {
                    ResultSet rs = qexec.execSelect();
                    
                    while ( rs.hasNext() ) {
                        QuerySolution rb = rs.nextSolution();
                        Resource resource = rb.getResource("x");
                        Individual i = m().
                                         getIndividual(resource.getURI());

                        OntView view = new OntView(i);
                        queryResult.add(view);
                    }
                }
                catch (Exception e) {
                }
                finally {
                    qexec.close();
                }
            }
        }

        return new ForwardResolution("/search.jsp");
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public String getSparqlQuery() {
        return sparqlQuery;
    }

    public void setSparqlQuery(String sparqlQuery) {
        this.sparqlQuery = sparqlQuery;
    }
    
    public Collection<OntView> getQueryResult() {
        return queryResult;
    }
}