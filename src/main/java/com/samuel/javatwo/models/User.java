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

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;



@Entity
@Table(name="users")
@Data
@Builder
@NoArgsConstructor
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Size(min=1, message="Name must be more than 1 character.")
	private String name;
	
	@Email(message="Email must be valid or unused.")
	@Column(unique=true)
	private String email;
	
	@Size(min=2, message="Pick a state.")
	private String state;
	
	@Size(min=2, message="City must be more than 1 character.")
	private String city;
	
	@Size(min=1, message="Please fill out a description about you.")
	private String description;
	
	@Size(min=1, message="Please copy image location of an image to use as a profile picture.")
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

	
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}