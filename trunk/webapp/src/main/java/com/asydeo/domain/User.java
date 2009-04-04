package com.asydeo.domain;

import java.net.URI;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;


import thewebsemantic.Id;
import thewebsemantic.binding.RdfBean;

/**
 * 
 *
 */
public class User extends RdfBean<User> implements Principal {
	@Id
	String username;
	String passwordHash;
	String password;
	String email;
	Collection<URI> collectedItems;
	
	transient String passwordCheck;
	Collection<Role> roles = new ArrayList<Role>();
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswordHash() {
		return passwordHash;
	}
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	public Collection<Role> getRoles() {
		return roles;
	}
	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
	
	public String getName() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordCheck() {
		return passwordCheck;
	}
	public void setPasswordCheck(String passwordCheck) {
		this.passwordCheck = passwordCheck;
	}
	
	public boolean passwordsMatch() {
		return passwordCheck.equals(password);
	}
	
	public void hashPassword() {
		try {
			passwordHash = hash(password);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected String hash(String s) throws NoSuchAlgorithmException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		md.update(s.getBytes());
		return Hex.toHex(md.digest());
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Collection<URI> getCollectedItems() {
		return collectedItems;
	}
	public void setCollectedItems(Collection<URI> collectedItems) {
		this.collectedItems = collectedItems;
	}

}
