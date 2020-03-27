package com.samuel.javatwo.models;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@Data
@Builder
@NoArgsConstructor
public class MessageReply {
	
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

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }	
}
