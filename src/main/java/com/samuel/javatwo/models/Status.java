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
@Table(name="statuses")
public class Status {
	public Status() {
		
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@Size(min=2, message="Status must be more than 2 characters.")
	private String status_body;
	
	
	//User ID of the wall it's on
	private long wall_id;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User poster;
    
    @OneToMany(mappedBy="statusReplyingTo", fetch = FetchType.LAZY)
    private List<StatusReply> repliedStatusMessages;
    
	private Date createdAt;
	
	private Date updatedAt;
	
	
	public long getWall_id() {
		return wall_id;
	}
	public void setWall_id(long wall_id) {
		this.wall_id = wall_id;
	}
	public List<StatusReply> getRepliedStatusMessages() {
		return repliedStatusMessages;
	}
	public void setRepliedStatusMessages(List<StatusReply> repliedStatusMessages) {
		this.repliedStatusMessages = repliedStatusMessages;
	}
	public User getPoster() {
		return poster;
	}
	public void setPoster(User poster) {
		this.poster = poster;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStatus_body() {
		return status_body;
	}
	public void setStatus_body(String status_body) {
		this.status_body = status_body;
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
