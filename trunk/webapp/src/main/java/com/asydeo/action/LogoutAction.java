package com.asydeo.action;

import java.io.IOException;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/auth/logout")
public class LogoutAction extends BaseAction {

	@DefaultHandler
	public Resolution start() throws IOException {
		context.getRequest().getSession().invalidate();
		return new ForwardResolution("/login.jsp");
	}
	

}
