package com.infotech.banking.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.infotech.banking.domain.security.Authority;
import com.infotech.banking.domain.security.UserRole;

@Entity
public class User implements UserDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userId",nullable=false,updatable = false)
	private Long userId;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	
	private boolean enabled=true;
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@OneToOne
	private PrimaryAccount primaryAccount;
	
	@OneToOne
	private SavingsAccount savingsAccount;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Appointment> appointmentList;
	
	@OneToMany(mappedBy="user",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<Recipient> recipientList;
	
	@OneToMany(mappedBy ="user",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonIgnore
	private Set<UserRole> userRoles=new HashSet<>();
	
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUserId() {
		return userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public PrimaryAccount getPrimaryAccount() {
		return primaryAccount;
	}

	public void setPrimaryAccount(PrimaryAccount primaryAccount) {
		this.primaryAccount = primaryAccount;
	}

	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void setSavingsAccount(SavingsAccount savingsAccount) {
		this.savingsAccount = savingsAccount;
	}

	public List<Appointment> getAppointmentList() {
		return appointmentList;
	}

	public void setAppointmentList(List<Appointment> appointmentList) {
		this.appointmentList = appointmentList;
	}

	public List<Recipient> getRecipientList() {
		return recipientList;
	}

	public void setRecipientList(List<Recipient> recipientList) {
		this.recipientList = recipientList;
	}

	

	/*
	 * @Override public String toString() { return "User [UserId=" + UserId +
	 * ", username=" + username + ", password=" + password + ", firstName=" +
	 * firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" +
	 * phone + ", enbaled=" + enbaled + ", primaryAccount=" + primaryAccount +
	 * ", savingsAccount=" + savingsAccount + ", appointmentList=" + appointmentList
	 * + ", recipientList=" + recipientList + ", userRoles=" + userRoles +
	 * ", getUserRoles()=" + getUserRoles() + ", getUserId()=" + getUserId() +
	 * ", getUsername()=" + getUsername() + ", getPassword()=" + getPassword() +
	 * ", getFirstName()=" + getFirstName() + ", getLastName()=" + getLastName() +
	 * ", getEmail()=" + getEmail() + ", getPhone()=" + getPhone() +
	 * ", isEnbaled()=" + isEnbaled() + ", getPrimaryAccount()=" +
	 * getPrimaryAccount() + ", getSavingsAccount()=" + getSavingsAccount() +
	 * ", getAppointmentList()=" + getAppointmentList() + ", getRecipientList()=" +
	 * getRecipientList() + ", getAuthorities()=" + getAuthorities() +
	 * ", isAccountNonExpired()=" + isAccountNonExpired() +
	 * ", isAccountNonLocked()=" + isAccountNonLocked() +
	 * ", isCredentialsNonExpired()=" + isCredentialsNonExpired() + ", isEnabled()="
	 * + isEnabled() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() +
	 * ", toString()=" + super.toString() + "]"; }
	 */
	
	 @Override
	    public String toString() {
	        return "User{" +
	                "userId=" + userId +
	                ", username='" + username + '\'' +
	                ", password='" + password + '\'' +
	                ", firstName='" + firstName + '\'' +
	                ", lastName='" + lastName + '\'' +
	                ", email='" + email + '\'' +
	                ", phone='" + phone + '\'' +
	                ", appointmentList=" + appointmentList +
	                ", recipientList=" + recipientList +
	                ", userRoles=" + userRoles +
	                '}';
	    }
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		Set<GrantedAuthority> authorities=new HashSet<>();
		userRoles.forEach(ur->authorities.add(new Authority(ur.getRole().getName())));
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() 
	{
		return true;
	}

	@Override
	public boolean isAccountNonLocked()
	{
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	@Override
	public boolean isEnabled() 
	{
		return enabled;
	}
	
	
}

