package com.asydeo.action;

import java.io.IOException;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/auth/login")
public class LoginAction extends BaseAction {

	@DefaultHandler
	public Resolution start() throws IOException {
		return new ForwardResolution("/login.jsp");
	}
	

}
