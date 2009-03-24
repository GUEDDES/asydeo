package test.basic;

import org.junit.Test;

import com.asydeo.action.Filters;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.OWL;
import static org.junit.Assert.*;

public class TestFilters {

	@Test
	public void basic() {
		OntModel m = ModelFactory.createOntologyModel();
		OntProperty op = m.createOntProperty("foo");
		op.addRDFType(OWL.FunctionalProperty);
		
		assertFalse(Filters.nonfunctional.accept(op));
		
		op = m.createOntProperty("bar");
		op.addRDFType(OWL.ObjectProperty);
		assertTrue(Filters.nonfunctional.accept(op));
		
		assertFalse(Filters.nonfunctional.accept("not"));
	}
}
