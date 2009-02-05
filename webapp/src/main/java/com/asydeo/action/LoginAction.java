package com.asydeo.action;

import java.io.IOException;

import com.asydeo.model.Login;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

public class LoginAction extends BaseAction {

	Login login;
	
	@DefaultHandler
	public Resolution login() throws IOException {
		
		if ("admin".equals(login.getUsername())) {
			context.login("admin");
		}
		return null;
	}
}
