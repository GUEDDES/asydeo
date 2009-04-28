package com.asydeo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import com.asydeo.ontology.Asydeo;
import static com.asydeo.util.AsydeoConfig.*;
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


public class SearchAction extends BaseAction {

    String uri;
    int numQueryResults = 0;
    float elapsedQueryTime = 0F;
    
    ArrayList<OntView> queryResult = new ArrayList<OntView>();


    protected void query(String sparql) {
        if ( sparql != null && ! sparql.isEmpty() ) {
            Query query = null;
            long startQueryTime = System.currentTimeMillis();
            
            sparql = sparql.trim();

            // If a PREFIX is not defined, add them
            if ( ! sparql.toUpperCase().startsWith("PREFIX") ) {
                sparql = getDefaultPrefixString() +
                         System.getProperty("line.separator") +
                         getAsydeoPrefixString() +
                         System.getProperty("line.separator") +
                         getRdfsPrefixString() +
                         System.getProperty("line.separator") +
                         sparql;
            }

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
            
            elapsedQueryTime =
              (System.currentTimeMillis() - startQueryTime) / 1000F;
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
            
            numQueryResults = queryResult.size();
        }
    }
    
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    
    public int getNumQueryResults() {
        return numQueryResults;
    }
    
    public float getElapsedQueryTime() {
        return elapsedQueryTime;
    }
    
    public Collection<OntView> getQueryResult() {
        return queryResult;
    }
}