package test.basic;

import net.sourceforge.stripes.mock.MockHttpServletRequest;

import org.junit.Test;

import com.asydeo.view.CommaSeperatedList;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class TestCommaSeperatedList {

	@Test
	public void basic() {
		CommaSeperatedList view = new CommaSeperatedList();
		OntModel m = ModelFactory.createOntologyModel();
		Individual i = m.createIndividual("http://foo", m.createClass("http://bar"));
		OntProperty p = m.createOntProperty("http://baz");
		view.setIndividual(i);
		view.setOntProperty(p);
		view.getContent();	

		view.apply("foo");
	}
}
