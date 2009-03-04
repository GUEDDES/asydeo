package com.asydeo.model;

public class AddRelation {
	String s;
	String v;
	String[] o = new String[0];
	String classUri;
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String[] getO() {
		if (o == null)
			return new String[0];
		return o;
	}
	public void setO(String[] o) {
		this.o = o;
	}
	public String getClassUri() {
		return classUri;
	}
	public void setClassUri(String classUri) {
		this.classUri = classUri;
	}

}
