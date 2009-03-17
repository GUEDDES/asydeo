package com.asydeo.servlet;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.asydeo.ontology.Asydeo;
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
		 model.setNsPrefix(Asydeo.PREFIX, Asydeo.NS);
		 OntModel om = ModelFactory.createOntologyModel( OntModelSpec.OWL_MEM_MINI_RULE_INF, model);
		 om.setNsPrefix(Asydeo.PREFIX, Asydeo.NS);
		 ctx.setAttribute("model", om);
		 

		 OntModel raw = readOWL();
		 ctx.setAttribute("rawmodel", raw);
		 om.addSubModel(raw);
	}

	public OntModel readOWL() {
		OntModel raw = ModelFactory.createOntologyModel(OntModelSpec.OWL_LITE_MEM);
		InputStream is = getClass().getResourceAsStream("/ontology/asydeo.owl");
		raw.read(is, "RDF/XML");
		return raw;
	}

}
