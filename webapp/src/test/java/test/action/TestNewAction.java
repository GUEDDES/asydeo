package test.action;

import java.util.HashMap;
import java.util.Map;

import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.mock.MockRoundtrip;
import net.sourceforge.stripes.mock.MockServletContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.asydeo.action.NewAction;

public class TestNewAction {
	
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
	public void testStart() throws Exception {
		MockRoundtrip test = new MockRoundtrip(ctx, NewAction.class);	
		test.execute();
	}

	@Test
	public void test() throws Exception {
		MockRoundtrip test = new MockRoundtrip(ctx, NewAction.class);	
		test.setParameter("classUri", "http://asydeo.com/schema#ConfigurableItem");
		test.setParameter("name", "name");
		test.execute("create");
		test.execute("create");
	}
}
