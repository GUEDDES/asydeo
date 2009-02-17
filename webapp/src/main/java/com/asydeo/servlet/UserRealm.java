package com.asydeo.servlet;

import java.security.Principal;

import org.mortbay.jetty.Request;

public class UserRealm implements org.mortbay.jetty.security.UserRealm {

	
	public Principal authenticate(String arg0, Object arg1, Request arg2) {
		return new Principal() {
			public String getName() {
				return "admin";
			}		
		};
	}

	
	public void disassociate(Principal arg0) {
		// TODO Auto-generated method stub

	}

	
	public String getName() {
		return "asydeo";
	}

	
	public Principal getPrincipal(String arg0) {
		return new Principal() {
			public String getName() {
				return "admin";
			}		
		};
	}

	
	public boolean isUserInRole(Principal arg0, String arg1) {
		return true;
	}

	
	public void logout(Principal arg0) {
		// TODO Auto-generated method stub

	}

	
	public Principal popRole(Principal arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Principal pushRole(Principal arg0, String arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean reauthenticate(Principal arg0) {
		return true;
	}

}
