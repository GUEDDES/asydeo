package com.asydeo.action;

import java.util.Collection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asydeo.view.OntView;

@UrlBinding("/asset/classes")
public class ListClassesAction extends BaseAction {
	
	@DefaultHandler
	public Resolution start() {			
		return new ForwardResolution("/listclasses.jsp");
	}
	
	public Collection<OntView> getList() {
		return new each(visibleClass().listSubClasses(true)) {
			void $() {result.add(OntView.$(item));}}.result();
	}


}
