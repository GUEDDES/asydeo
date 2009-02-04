package com.asydeo.action;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;



public class BaseAction implements ActionBean {
	protected ASDContext context;
	
	public ASDContext getContext() {
		return context;
	}

	public void setContext(ActionBeanContext c) {
		context = (ASDContext)c;
	}
}
