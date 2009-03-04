package com.asydeo.servlet;

import java.io.InputStream;

import javax.servlet.ServletContext;
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
		 ServletContext ctx = ev.getServletContext();
		 String directory = "databases/DB1" ;
		 Model model = TDBFactory.createModel(directory);
		 
		 OntModel om = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MINI_RULE_INF, model);
		 readOWL(om);
		 ctx.setAttribute("model", om);
		 
		 OntModel raw = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM);
		 readOWL(raw);
		 ctx.setAttribute("rawmodel", raw);
	}

	private void readOWL(OntModel om) {
		InputStream is = getClass().getClassLoader().getResourceAsStream("/ontology/asydeo.owl");
		om.read(is, "RDF/XML");
	}

}
