package com.asydeo.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/asset/model")
public class ModelAction extends BaseAction {
	
	@DefaultHandler
	public Resolution start() {
		return new ForwardResolution("/model.jsp");
	}
	
	@HandlesEvent("chooseModel")
	public Resolution chooseModel() {
		return new RedirectResolution(ModelAction.class);
	}

}
