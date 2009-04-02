package test.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.mock.MockRoundtrip;
import net.sourceforge.stripes.mock.MockServletContext;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.asydeo.action.ASDContext;
import com.asydeo.action.EditAction;
import com.asydeo.action.Filters;
import com.asydeo.model.StatementBean;
import com.asydeo.ontology.Asydeo;
import com.asydeo.view.OntView;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;
import com.hp.hpl.jena.vocabulary.OWL;
import com.hp.hpl.jena.vocabulary.RDFS;

public class TestEditAction {
	
	static MockServletContext ctx;
	
	@BeforeClass
	public static void before() {
		MockServletContext context = new MockServletContext("test");

		// Add the Stripes Filter
		Map<String,String> filterParams = new HashMap<String,String>();
		filterParams.put("ActionResolver.Packages", "com.asydeo.action");
		filterParams.put("ActionBeanContext.Class", "test.action.MockContext");
		context.addFilter(StripesFilter.class, "StripesFilter", filterParams);

		// Add the Stripes Dispatcher
		context.setServlet(DispatcherServlet.class, "StripesDispatcher", null);
		
		ctx = context;
	}
	@Test
	public void testBasic() {
		EditAction action = new EditAction();
		action.setUri("uri");
		assertEquals("uri", action.getUri());
		StatementBean bean = new StatementBean();
		action.setBean(bean);
		assertEquals(bean, action.getBean());
		
		action.setClassUri("classUri");
		assertEquals("classUri", action.getClassUri());
	}
	
	@Test
	public void testStart() {
		EditAction action = new EditAction();		
		Resolution r = action.start();
		assertTrue(r instanceof ForwardResolution);
		ForwardResolution f = (ForwardResolution)r;
		assertEquals(f.getPath(), "/edit.jsp");
	}
	
	@Test
	public void testFilters() {
		final OntModel m = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MICRO_RULE_INF);
		m.setNsPrefix(Asydeo.PREFIX, Asydeo.NS);
		InputStream is = getClass().getResourceAsStream("/ontology/asydeo.owl");
		m.read(is, "RDF/XML");
		OntClass c = m.getOntClass(Asydeo.NS + "ComputerSystem");
		
		EditAction e = new EditAction();
		e.setContext( new ASDContext(){
			public OntModel getModel() {
				return m;
			}
		});
		
		e.setUri("foo");
		e.setClassUri(c.getURI());
		for (OntView v : e.getFunctionalProperties()) {
			System.out.println("\t" + v.getURI());
		}
		
		ExtendedIterator it = c.listDeclaredProperties(false);
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
	
	@Test
	public void testUpdate() throws Exception {
		MockRoundtrip test = new MockRoundtrip(ctx, EditAction.class);	
		test.setParameter("uri", "foo:1");
		test.execute("update");
	}
	
	@Test
	public void testUnrelate() throws Exception {
		MockRoundtrip test = new MockRoundtrip(ctx, EditAction.class);	
		test.setParameter("bean.s", "foo:1");
		test.setParameter("bean.v", "foo:property");
		test.setParameter("bean.o", "foo:2");
		test.execute("unrelate");
	}
}
