package test.basic;

import java.net.URL;
import java.util.Collection;

import org.junit.Test;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.asydeo.view.OntView;
import com.asydeo.view.Util;
import com.asydeo.view.View;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import static org.junit.Assert.*;


public class TestOntView {

	@Test
	public void basic() {
		OntModel m = ModelFactory.createOntologyModel();
		URL url = getClass().getResource("/ontology/asydeo.owl");
		m.read(url.toString());
		Individual subject = m.createIndividual(m.createClass("http://asydeo.com/schema#ComputerSystem"));
		OntProperty p = m.getOntProperty("http://asydeo.com/schema#isHosting");
		OntView v = OntView.$(p, subject);
		Collection<OntView> items = v.getItems();
		for (OntView ontView : items) {
			System.out.println(ontView.getLabel());
		}
		
		v.getLabel();
		v.getComment();
		v.getURI();
		OntView.$(subject);
	}
}
