package com.hazar.socialmedia.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="message_replies")
@Data
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
