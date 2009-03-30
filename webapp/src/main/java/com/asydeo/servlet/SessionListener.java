package com.asydeo.servlet;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.asydeo.domain.AsydeoSession;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		arg0.getSession().setAttribute(RequestConstants.SESSION, new AsydeoSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}

}
