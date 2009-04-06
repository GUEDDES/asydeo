package com.asydeo.domain;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;

import thewebsemantic.Id;

public class Profile {
	@Id
	String id;
	Collection<URI> collectedItems = new ArrayList<URI>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Collection<URI> getCollectedItems() {
		return collectedItems;
	}
	public void setCollectedItems(Collection<URI> collectedItems) {
		this.collectedItems = collectedItems;
	}
}
