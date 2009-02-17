package com.asydeo.action;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asydeo.view.Util;
import com.asydeo.view.View;
import com.hp.hpl.jena.shared.Lock;

@UrlBinding("/asset/edit")
public class EditAction extends BaseAction {
	
	String uri;
	String classUri;
	
	@DefaultHandler
	public Resolution start() {			
		return new ForwardResolution("/edit.jsp");
	}
	
	@HandlesEvent("update")
	public Resolution update() {
		try {
			m().enterCriticalSection(Lock.WRITE);
			for (View view : getViews())
				view.apply(context.getRequest());
		} finally {
			m().leaveCriticalSection();
		}
		return null;
	}
	
	public View[] getViews() {
		return Util.getView(individual(uri));
	}

	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getClassUri() {
		return classUri;
	}

	public void setClassUri(String classUri) {
		this.classUri = classUri;
	}

}
