package com.asydeo.action;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.hp.hpl.jena.ontology.OntModel;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/admin/modelreload")
public class ReloadOntologyAction extends BaseAction {

	String uri;
	
	@DefaultHandler
	public Resolution dump() throws IOException {
		OntModel m = raw();
		uri = "http://opentravel.s3.amazonaws.com/asydeo.owl";
		m.removeAll();
		m.read(uri);
		context.getResponse().setContentType("text/plain");
		context.getResponse().setStatus(HttpURLConnection.HTTP_OK);
		context.getResponse().getWriter().write("success");
		return null;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
