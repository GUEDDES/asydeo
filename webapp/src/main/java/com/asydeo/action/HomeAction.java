package com.asydeo.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/guest/home")
public class HomeAction extends BaseAction {

	@DefaultHandler
	public Resolution start() {
		return new ForwardResolution("/home.jsp");
	}
}
