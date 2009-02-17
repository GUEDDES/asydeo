package com.asydeo.action;

import java.io.IOException;


import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/admin/delete")
public class DeleteAction extends BaseAction {

	@DefaultHandler
	public Resolution dump() throws IOException {
		context.getResponse().setContentType("text/plain");
		getContext().getModel().removeAll();
		return null;
	}
}
