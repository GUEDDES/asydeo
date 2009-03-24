package com.asydeo.jaas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;

import org.mortbay.jetty.plus.jaas.spi.AbstractLoginModule;
import org.mortbay.jetty.plus.jaas.spi.UserInfo;
import org.mortbay.jetty.security.Credential;

import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;



public class MyLoginModule extends AbstractLoginModule {

	OntModel om;
	
	public UserInfo getUserInfo(String arg0) throws Exception {
		List<String> roles = new ArrayList<String>();
		roles.add("admin");
		Credential c = new Credential() {
			public boolean check(Object arg0) {
				return true;
			}
		};
		Individual i = om.getIndividual("http://users/" + arg0);
		if ( i == null)
			return null;
		return new UserInfo(arg0, c, roles);
	}


	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map sharedState, Map options) {
	       super.initialize(subject, callbackHandler, sharedState, options);
	       InitialContext ic;
		try {
			ic = new InitialContext();
			om = (OntModel)ic.lookup("java:model");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      
	}


}
