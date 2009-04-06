package com.asydeo.action;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Collection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.asydeo.domain.Profile;
import com.asydeo.domain.User;

@UrlBinding("/asset/collect")
public class CollectItemAction extends BaseAction {

	String uri;
	
	@DefaultHandler
	public Resolution remember() throws IOException {
		context.getResponse().setContentType("text/plain");
		context.getResponse().setStatus(HttpURLConnection.HTTP_OK);
		context.getResponse().getWriter().write("success");
		String longuri = m().expandPrefix(uri);
		User u = context.getUser();
		Profile p = u.getProfile();
		URI aURI = URI.create(longuri);
		Collection<URI> c = p.getCollectedItems();
		if (! c.contains(aURI))
			c.add(aURI);
		return null;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
