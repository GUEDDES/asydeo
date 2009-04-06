package com.asydeo.servlet;

import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import thewebsemantic.binding.Jenabean;

import com.asydeo.domain.Role;
import com.asydeo.domain.User;
import com.asydeo.ontology.Asydeo;
import static com.asydeo.servlet.RequestConstants.*;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.tdb.TDBFactory;

public class ContextListener implements ServletContextListener {

	public void contextDestroyed(ServletContextEvent ev) {
		OntModel m = (OntModel) ev.getServletContext().getAttribute(CURRENT_MODEL);
		m.close();
		Jenabean.instance().model().close();
	}

	public void contextInitialized(ServletContextEvent ev) {
		ServletContext ctx = ev.getServletContext();
		createUserRoleDB();
		
		
		OntModel raw = readOWL();
		ctx.setAttribute(RAWMODEL, raw);
		
		OntModel om = createModel("databases/current", raw);
		ctx.setAttribute(CURRENT_MODEL, om);

		OntModel discovered = createModel("databases/discovered", raw);
		ctx.setAttribute(DISCOVERED_MODEL, discovered);
		
		OntModel planned = createModel("databases/planned", raw);
		ctx.setAttribute(PLANNED_MODEL, planned);
			
	


	}

	private OntModel createModel(String directory, Model m) {
		Model model = TDBFactory.createModel(directory);
		model.setNsPrefix(Asydeo.PREFIX, Asydeo.NS);
		OntModel om = ModelFactory.createOntologyModel(
				OntModelSpec.OWL_MEM_MICRO_RULE_INF, model);
		om.setNsPrefix(Asydeo.PREFIX, Asydeo.NS);
		om.addSubModel(m);
		return om;
	}

	public void createUserRoleDB() {
		Model m = TDBFactory.createModel("databases/userroles");
		Jenabean.instance().bind(m);
		User u = new User();
		u.setEmail("taylor.cowan@sabre.com");
		u.setPassword("admin");
		u.hashPassword();
		u.setUsername("admin");
		u.getRoles().add(new Role("admin"));
		u.save();
	}

	public OntModel readOWL() {
		OntModel raw = ModelFactory
				.createOntologyModel(OntModelSpec.OWL_LITE_MEM);
		InputStream is = getClass().getResourceAsStream("/ontology/asydeo.owl");
		raw.read(is, "RDF/XML");
		return raw;
	}

}
