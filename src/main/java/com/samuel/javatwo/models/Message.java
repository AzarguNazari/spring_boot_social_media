package com.samuel.javatwo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="messages")
public class Message {
	public Message() {
		
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@Size(min=2, message="Message must be more than 2 characters.")
	private String message_body;
	
	private long user_wall_id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User messagePoster;
	
    @OneToMany(mappedBy="messageReplyingTo", fetch = FetchType.LAZY)
    private List<MessageReply> repliedMessageMessages;
    
	private Date createdAt;
	
	private Date updatedAt;

	

	

	public List<MessageReply> getRepliedMessageMessages() {
		return repliedMessageMessages;
	}

	public void setRepliedMessageMessages(List<MessageReply> repliedMessageMessages) {
		this.repliedMessageMessages = repliedMessageMessages;
	}

	public long getUser_wall_id() {
		return user_wall_id;
	}

	public void setUser_wall_id(long user_wall_id) {
		this.user_wall_id = user_wall_id;
	}

	public User getMessagePoster() {
		return messagePoster;
	}

	public void setMessagePoster(User messagePoster) {
		this.messagePoster = messagePoster;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMessage_body() {
		return message_body;
	}

	public void setMessage_body(String message_body) {
		this.message_body = message_body;
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
	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	
}
