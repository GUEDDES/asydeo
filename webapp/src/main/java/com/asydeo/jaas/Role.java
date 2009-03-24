package com.asydeo.jaas;

import java.security.Principal;

public class Role implements Principal {

	public String getName() {
		return "admin";
	}

}
