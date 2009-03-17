package test.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.mock.MockRoundtrip;
import net.sourceforge.stripes.mock.MockServletContext;

import org.junit.Before;
import org.junit.Test;

import com.asydeo.action.EditAction;
import com.asydeo.model.StatementBean;

public class TestEditAction {
	
	MockServletContext ctx;
	
	@Before
	public void before() {
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
