package test.action;

import java.util.HashMap;
import java.util.Map;

import net.sourceforge.stripes.controller.DispatcherServlet;
import net.sourceforge.stripes.controller.StripesFilter;
import net.sourceforge.stripes.mock.MockRoundtrip;
import net.sourceforge.stripes.mock.MockServletContext;

import org.junit.BeforeClass;
import org.junit.Test;

import com.asydeo.action.DeleteAction;
import com.asydeo.action.DumpAction;
import com.asydeo.action.ListClassesAction;

public class MiscTests {

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
	public void basic() throws Exception {
		MockRoundtrip test = new MockRoundtrip(ctx, DumpAction.class);	
		test.execute();
	}
	
	@Test
	public void basic2() throws Exception {
		MockRoundtrip test = new MockRoundtrip(ctx, DeleteAction.class);	
		test.execute();
	}
	
	@Test
	public void basic3() throws Exception {
		MockRoundtrip test = new MockRoundtrip(ctx, DeleteAction.class);	
		test.execute();
	}
	
	@Test
	public void basic4() throws Exception {
		MockRoundtrip test = new MockRoundtrip(ctx, ListClassesAction.class);	
		test.execute();
		
		ListClassesAction action = new ListClassesAction();
		action.setContext(new MockContext());
		action.getList();
	}
}
