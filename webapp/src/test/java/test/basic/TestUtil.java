package test.basic;

import java.net.URL;

import org.junit.Test;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.asydeo.view.Util;
import com.asydeo.view.View;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import static org.junit.Assert.*;


public class TestUtil {

	@Test
	public void basic() {
		OntModel m = ModelFactory.createOntologyModel();
		URL url = getClass().getResource("/ontology/asydeo.owl");
		m.read(url.toString());
		Individual i = m.createIndividual(m.createClass("http://asydeo.com/schema#ComputerSystem"));
		View[] views = Util.getView(i);
		assertNotNull(views);
	}
	
	@Test
	public void basic1() {
		OntModel m = ModelFactory.createOntologyModel();
		URL url = getClass().getResource("/ontology/asydeo.owl");
		m.read(url.toString());
		Individual i = m.createIndividual(m.createClass("http://asydeo.com/schema#ComputerSystem"));
		View[] views = Util.getView(m, "http://asydeo.com/schema#ComputerSystem");
		assertNotNull(views);
	}
}
