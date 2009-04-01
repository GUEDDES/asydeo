package com.asydeo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
import com.hp.hpl.jena.query.QueryExecException;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QueryParseException;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.shared.Lock;
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

            try {
                m().enterCriticalSection(Lock.READ);
                
                try {
                    query = QueryFactory.create(sparqlQuery);
                    
                    if ( query.isSelectType() ) {
                        executeSelectQuery(query);
                    }
                }
                catch (QueryParseException e) {
                    System.out.println("Error: " + e.toString());
                }
                catch (Exception e) {
                    System.out.println("Error: " + e.toString());
                }
            }
            finally {
                m().leaveCriticalSection();
            }
        }

        return new ForwardResolution("/search.jsp");
    }
    
    private void executeSelectQuery(Query query) {
        QueryExecution qexec = null;
        
        if ( query != null ) {
            try {
                qexec = QueryExecutionFactory.create(
                          query, m());
            
                addSolutionsToResult( qexec.execSelect() );
            }
            catch (QueryExecException e) {
                System.out.println("Error: " + e.toString());
            }
            catch (Exception e) {
                System.out.println("Error: " + e.toString());
            }
            finally {
                qexec.close();
            }
        }
    }

    private void addSolutionsToResult(ResultSet results) {
        if ( results != null ) {
            while ( results.hasNext() ) {
                QuerySolution soln = results.nextSolution();
                
                // Iterate over the bind variables used in the query
                Iterator<String> it = soln.varNames();
                while ( it.hasNext() ) {
                    String varName = it.next();
                    
                    if ( soln.get(varName) != null ) {
                        if ( soln.get(varName).isResource() ) {
                            Resource resource = soln.getResource(varName);
                            Individual i =
                              m().getIndividual(resource.getURI());

                            OntView view = new OntView(i);
                            queryResult.add(view);
                        }
                    }
                }
            }
        }
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