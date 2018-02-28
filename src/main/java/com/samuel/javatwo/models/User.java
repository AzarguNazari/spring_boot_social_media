package com.samuel.javatwo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;



@Entity
@Table(name="users")
public class User {
	
	public User () {
		
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@Size(min=2, message="Name must be more than 2 characters.")
	private String name;
	
	@Email(message="Email must be valid or unused.")
	@Column(unique=true)
	private String email;
	
	@Size(min=2, message="Pick a state.")
	private String state;
	
	@Size(min=2, message="City must be more than 2 characters.")
	private String city;
	
	@Size(min=2, message="Description must be more than 2 characters.")
	private String description;
	
	private String image_address;
	
	@Size(min=5, message="Password must be at least 5 characters")
	private String password;
	
	@Transient
	private String passwordConfirmation;
	
	private Date createdAt;
	
	private Date updatedAt;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "friendships", 
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> friends;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "friendships", 
    joinColumns = @JoinColumn(name = "friend_id"), 
    inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> userFriends;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "invitations", 
    joinColumns = @JoinColumn(name = "user_id"), 
    inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<User> invitedFriends;
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "invitations", 
    joinColumns = @JoinColumn(name = "friend_id"), 
    inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> invitedUserFriends;
    
	@ManyToMany(fetch=FetchType.EAGER)
	@JoinTable(
		name = "users_roles",
		joinColumns = @JoinColumn(name="user_id"),
		inverseJoinColumns = @JoinColumn(name= "role_id"))
	
	private List<Role> roles;
	
    @OneToMany(mappedBy="poster", fetch = FetchType.LAZY)
    private List<Status> statuses;
    
    @OneToMany(mappedBy="messagePoster", fetch = FetchType.LAZY)
    private List<Message> messages;
	
    @OneToMany(mappedBy="userWhoRepliedToMessage", fetch = FetchType.LAZY)
    private List<MessageReply> messageReplies;
	
    
    
	public String getImage_address() {
		return image_address;
	}
	public void setImage_address(String image_address) {
		this.image_address = image_address;
	}
	public List<MessageReply> getMessageReplies() {
		return messageReplies;
	}
	public void setMessageReplies(List<MessageReply> messageReplies) {
		this.messageReplies = messageReplies;
	}
	public List<Message> getMessages() {
		return messages;
	}
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	public List<Status> getStatuses() {
		return statuses;
	}
	public void setStatuses(List<Status> statuses) {
		this.statuses = statuses;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List<User> getInvitedFriends() {
		return invitedFriends;
	}
	public void setInvitedFriends(List<User> invitedFriends) {
		this.invitedFriends = invitedFriends;
	}
	public List<User> getInvitedUserFriends() {
		return invitedUserFriends;
	}
	public void setInvitedUserFriends(List<User> invitedUserFriends) {
		this.invitedUserFriends = invitedUserFriends;
	}
	public List<User> getFriends() {
		return friends;
	}
	public void setFriends(List<User> friends) {
		this.friends = friends;
	}
	public List<User> getUserFriends() {
		return userFriends;
	}
	public void setUserFriends(List<User> userFriends) {
		this.userFriends = userFriends;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}
	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}