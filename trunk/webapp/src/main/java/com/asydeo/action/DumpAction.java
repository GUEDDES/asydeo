package com.asydeo.action;

import java.io.IOException;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/admin/dump")
public class DumpAction extends BaseAction {

	@DefaultHandler
	public Resolution dump() throws IOException {
		context.getResponse().setContentType("text/plain");
		getContext().getModel().writeAll(context.getResponse().getWriter(), "N3", null);
		return null;
	}
}
