package com.asydeo.action;

import java.net.URI;
import java.util.Collection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/asset/home")
public class HomeAction extends BaseAction {

	@DefaultHandler
	public Resolution start() {
		if (context.isAuthenticated())
			return new ForwardResolution("/home.jsp").addParameter("modelName", getModelName());
		else
		    return new RedirectResolution(LoginAction.class);
	}
	
	public Collection<URI> getItems() {
		return context.getUser().getCollectedItems();
	}
}
