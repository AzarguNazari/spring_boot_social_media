package com.samuel.javatwo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="message_replies")
public class MessageReply {
	
	public MessageReply() {
		
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@Size(min=2, message="Message reply must be more than 2 characters.")
	private String messageReplyBody;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User userWhoRepliedToMessage; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="message_id")
	private Message messageReplyingTo; 
	
	private Date createdAt;
	
	private Date updatedAt;
	
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	
	public User getUserWhoRepliedToMessage() {
		return userWhoRepliedToMessage;
	}
	public void setUserWhoRepliedToMessage(User userWhoRepliedToMessage) {
		this.userWhoRepliedToMessage = userWhoRepliedToMessage;
	}
	public String getMessageReplyBody() {
		return messageReplyBody;
	}
	public void setMessageReplyBody(String messageReplyBody) {
		this.messageReplyBody = messageReplyBody;
	}
	public Message getMessageReplyingTo() {
		return messageReplyingTo;
	}
	public void setMessageReplyingTo(Message messageReplyingTo) {
		this.messageReplyingTo = messageReplyingTo;
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
