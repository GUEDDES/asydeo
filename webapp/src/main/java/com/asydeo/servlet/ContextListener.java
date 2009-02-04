package com.asydeo.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;


public class ContextListener implements ServletContextListener {

	
	public void contextDestroyed(ServletContextEvent ev) {
	}

	public void contextInitialized(ServletContextEvent ev) {
		 String directory = "databases/DB1" ;
		 Model model = TDBFactory.createModel(directory) ;
		 OntModel om = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF, model);
		 ev.getServletContext().setAttribute("model", om);
		 om.write(System.out, "N3");
	}

}
