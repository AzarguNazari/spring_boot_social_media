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
@Table(name="statuse_replies")
public class StatusReply {
	public StatusReply() {
		
	}
	
	@Id
	@GeneratedValue
	public long id;
	
	@Size(min=2, message="Status reply must be more than 2 characters.")
	private String statusReplyBody;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id")
	private User userWhoRepliedToStatus;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="status_id")
	private Status statusReplyingTo;
	

	
	private Date createdAt;
	
	private Date updatedAt;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public User getUserWhoRepliedToStatus() {
		return userWhoRepliedToStatus;
	}
	public void setUserWhoRepliedToStatus(User userWhoRepliedToStatus) {
		this.userWhoRepliedToStatus = userWhoRepliedToStatus;
	}
	public String getStatusReplyBody() {
		return statusReplyBody;
	}
	public void setStatusReplyBody(String statusReplyBody) {
		this.statusReplyBody = statusReplyBody;
	}
	public Status getStatusReplyingTo() {
		return statusReplyingTo;
	}
	public void setStatusReplyingTo(Status statusReplyingTo) {
		this.statusReplyingTo = statusReplyingTo;
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
