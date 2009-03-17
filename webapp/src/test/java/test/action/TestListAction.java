package test.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;

import org.junit.Test;

import com.asydeo.action.ListAction;
import com.asydeo.view.OntView;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;

public class TestListAction {

	@Test
	public void basic() {
		ListAction action = new ListAction();		
		Resolution r = action.start();
		assertTrue(r instanceof ForwardResolution);
		ForwardResolution f = (ForwardResolution)r;
		assertEquals(f.getPath(), "/list.jsp");
	}
	
	@Test
	public void testDelete() {
		final OntModel om = ModelFactory.createOntologyModel();
		om.createIndividual("http://foo#bar", om.createClass("dodad"));
		om.setNsPrefix("foo", "http://foo#");
		ListAction action = new ListAction() {
			OntModel m = om;
			public OntModel m() {
				return m;
			}
		};
		action.setDeleteUri("foo:bar");		
		action.delete();
		assertNull(om.getIndividual("http://foo#bar"));
	}
	
	@Test
	public void testList() {
		final OntModel om = ModelFactory.createOntologyModel();
		OntClass cls = om.createClass("http://foo#dodad");
		om.setNsPrefix("foo", "http://foo#");
		cls.createIndividual("http://foo#1").setLabel("example", null);
		cls.createIndividual("http://foo#2").setLabel("example", null);
		cls.createIndividual("http://foo#3").setLabel("example", null);

		ListAction action = new ListAction() {
			OntModel m = om;
			public OntModel m() {
				return m;
			}
		};
		action.setUri("foo:dodad");
		Collection<OntView> list = action.getList();
		assertEquals(3, list.size());
		assertEquals("example", list.iterator().next().getLabel());
		
	}
	
	@Test
	public void testGetters() {
		ListAction action = new ListAction();
		action.setDeleteUri("delete");
		action.setUri("uri");
		assertEquals("delete", action.getDeleteUri());
		assertEquals("uri", action.getUri());
	}
}
