package com.hazar.socialmedia.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="messages")
@Data
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
