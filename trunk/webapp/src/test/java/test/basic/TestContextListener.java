package test.basic;

import org.junit.Test;

import com.asydeo.servlet.ContextListener;
import com.hp.hpl.jena.ontology.OntModel;

import static org.junit.Assert.*;

public class TestContextListener {
	
	@Test
	public void testReadOWL() {
		
		ContextListener c = new ContextListener();
		OntModel om = c.readOWL();
		assertNotNull(om);
		String uri = om.getNsPrefixURI("asydeo");
		assertNotNull(uri);
	}

}
