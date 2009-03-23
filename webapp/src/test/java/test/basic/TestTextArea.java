package test.basic;

import org.junit.Test;

import com.asydeo.view.TextArea;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class TestTextArea {

	@Test
	public void basic() {
		TextArea view = new TextArea();
		OntModel m = ModelFactory.createOntologyModel();
		Individual i = m.createIndividual("http://foo", m.createClass("http://bar"));
		OntProperty p = m.createOntProperty("http://baz");
		view.setIndividual(i);
		view.setOntProperty(p);
		view.getContent();	

	}
}
