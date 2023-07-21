package it.icarcnr.business.login.bean;

import java.io.Serializable;
import java.util.Date;

public class UserLoginBean implements Serializable {
	
	
	private Integer id;
	private String name;
	private String surname;
	private String username;

	private Boolean firstAccess;
	private Boolean expired;
	
	private Date expiryDate;
	
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Boolean getFirstAccess() {
		return firstAccess;
	}
	public void setFirstAccess(Boolean firstAccess) {
		this.firstAccess = firstAccess;
	}
	public Boolean getExpired() {
		return expired;
	}
	public void setExpired(Boolean expired) {
		this.expired = expired;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Boolean isEnabled() {
		return !(firstAccess || expired);
	}
}
