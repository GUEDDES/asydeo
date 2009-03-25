package com.asydeo.action;

import static thewebsemantic.binding.Jenabean.load;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.LocalizableError;
import net.sourceforge.stripes.validation.ValidationErrors;
import net.sourceforge.stripes.validation.ValidationMethod;

import com.asydeo.domain.Role;
import com.asydeo.domain.User;

@UrlBinding("/admin/user")
public class UserAction extends BaseAction {

	User user;
	String id;
	String[] roles = new String[0];
	
	@ValidationMethod(on = "save")
	public void validateRegistration(ValidationErrors errors) {
		if ( !user.passwordsMatch() )
			errors.add("password", new LocalizableError("verifymatch"));
	}
	
	@DefaultHandler
	public Resolution start() {
		return new ForwardResolution("/listUsers.jsp");
	}
	
	@HandlesEvent("cancel") 
	public Resolution cancel() {
		return new ForwardResolution("/listUsers.jsp");
	}
	
	@HandlesEvent("delete")
	public Resolution delete() {		
		load(User.class,id).delete();
		return new ForwardResolution("/listUsers.jsp");
	}
	
	@HandlesEvent("save") 
	public Resolution save() throws NoSuchAlgorithmException {		
		user.hashPassword();
		for (String roleName : roles)
			user.getRoles().add(new Role(roleName));
		user.save();
		return new RedirectResolution(UserAction.class);
	}

	@HandlesEvent("edit")
	public Resolution edit() {
		user = load(User.class,id);
		user.setPasswordCheck(user.getPassword());
		ArrayList<String> roleNames = new ArrayList<String>();
		for (Role role : user.getRoles()) {
			roleNames.add(role.getName());
		}
		roles = roleNames.toArray(new String[0]);
		return new ForwardResolution("/newUser.jsp");
	}
	
	@HandlesEvent("new")
	public Resolution newUser() {
		return new ForwardResolution("/newUser.jsp");
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String[] getRoles() {
		return roles;
	}

	public void setRoles(String[] roles) {
		this.roles = roles;
	}
	
	public Collection<User> getUsers() {
		return load(User.class);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
}
