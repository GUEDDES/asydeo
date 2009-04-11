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
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.shared.Lock;

import thewebsemantic.binding.Jenabean;

import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;


@UrlBinding("/asset/search")
public class SearchAction extends BaseAction {

    String asydeoPrefix = "PREFIX " + Asydeo.PREFIX + ": <" + Asydeo.NS + ">";
    String rdfsPrefix = "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>";
    
    String uri;
    
    @Validate(required = true, on = {"textQuery"})
      String searchStr;
    @Validate(required = true, on = {"sparqlQuery"})
      String sparqlStr;
    
    ArrayList<OntView> queryResult = new ArrayList<OntView>();

    
    @DefaultHandler
    public Resolution start() {
        if ( sparqlStr == null || sparqlStr.isEmpty() ) {
            sparqlStr = asydeoPrefix + System.getProperty("line.separator");
        }
        
        return new ForwardResolution("/search.jsp");
    }
    
    @HandlesEvent("sparqlQuery")
    public Resolution sparqlQuery() {
        _sparqlQuery();
        
        return new ForwardResolution("/search.jsp");
    }
    
    @HandlesEvent("sparqlQueryResults")
    public Resolution sparqlQueryResults() {
        _sparqlQuery();
        
        return new ForwardResolution("/searchResults.jsp");
    }
    
    @HandlesEvent("textQuery")
    public Resolution textQuery() {
        _textQuery();
        
        return new ForwardResolution("/search.jsp");
    }
    
    @HandlesEvent("textQueryResults")
    public Resolution textQueryResults() {
        _textQuery();

        return new ForwardResolution("/searchResults.jsp");
    }
    
    private void _sparqlQuery() {
        query(sparqlStr);
    }
    
    private void _textQuery() {
        sparqlStr = asydeoPrefix + System.getProperty("line.separator") +
        rdfsPrefix + System.getProperty("line.separator") +
        "SELECT * " +
        "WHERE { " +
        "?x " + "rdfs:label ?label . " +
        "FILTER regex(?label, \"" + searchStr + "\", \"i\")" +
          "}";

        query(sparqlStr);
    }
    
    private void query(String sparql) {
        if ( sparql != null && ! sparql.isEmpty() ) {
            Query query = null;

            try {
                m().enterCriticalSection(Lock.READ);
                
                try {
                    query = QueryFactory.create(sparql);
                    
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
            /*
            String xmlResult = ResultSetFormatter.asXMLString(results);
            System.out.println(xmlResult);
            System.out.println("--------------");
            String textResult = ResultSetFormatter.asText(results);
            System.out.println(textResult);
            */
            
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
    
    public String getSparqlStr() {
        return sparqlStr;
    }

    public void setSparqlStr(String sparqlStr) {
        this.sparqlStr = sparqlStr;
    }
    
    public String getSearchStr() {
        return searchStr;
    }
    
    public void setSearchStr(String searchStr) {
        this.searchStr = searchStr;
    }
    
    public Collection<OntView> getQueryResult() {
        return queryResult;
    }
}