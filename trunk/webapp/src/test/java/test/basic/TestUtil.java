package test.basic;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.junit.Test;

import com.asydeo.view.View;
import com.asydeo.view.ViewManager;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;


public class TestUtil {

	@Test
	public void basic() {
		OntModel m = ModelFactory.createOntologyModel();
		URL url = getClass().getResource("/ontology/asydeo.owl");
		m.read(url.toString());
		OntClass cls = m.createClass("http://asydeo.com/schema#ComputerSystem");
		Individual i = m.createIndividual(cls);
		ViewManager vm = new ViewManager();
		View[] views = vm.getView(m, i, cls);
		assertNotNull(views);
	}
	
	@Test
	public void basic1() {
		OntModel m = ModelFactory.createOntologyModel();
		URL url = getClass().getResource("/ontology/asydeo.owl");
		m.read(url.toString());
		Individual i = m.createIndividual(m.createClass("http://asydeo.com/schema#ComputerSystem"));
		ViewManager vm = new ViewManager();
		View[] views = vm.getView(m, "http://asydeo.com/schema#ComputerSystem");
		assertNotNull(views);
	}
}
