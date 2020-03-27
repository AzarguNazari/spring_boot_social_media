package com.hazar.socialmedia.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@Builder
@NoArgsConstructor
public class Message {
	
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
