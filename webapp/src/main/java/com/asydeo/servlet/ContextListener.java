package com.asydeo.servlet;

import java.io.InputStream;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;


public class ContextListener implements ServletContextListener {

	
	public void contextDestroyed(ServletContextEvent ev) {
		OntModel m = (OntModel)ev.getServletContext().getAttribute("model");
		m.close();
	}

	public void contextInitialized(ServletContextEvent ev) {
		 String directory = "databases/DB1" ;
		 Model model = TDBFactory.createModel(directory);
		// TDBFactory.createModel(dir)
		 OntModel om = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MICRO_RULE_INF, model);
		 InputStream is = getClass().getClassLoader().getResourceAsStream("/ontology/asydeo.owl");
		 om.read(is, "RDF/XML");
		 ev.getServletContext().setAttribute("model", om);

	}

}
