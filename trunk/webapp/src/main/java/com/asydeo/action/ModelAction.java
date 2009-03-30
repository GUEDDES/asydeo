package com.asydeo.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/asset/model")
public class ModelAction extends BaseAction {
	
	String modelName;
	@DefaultHandler
	public Resolution start() {
		return new ForwardResolution("/home.jsp").addParameter("modelName", getModelName());
	}


}
