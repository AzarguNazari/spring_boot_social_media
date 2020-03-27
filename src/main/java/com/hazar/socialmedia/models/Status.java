package com.hazar.socialmedia.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="statuses")
@Data
@NoArgsConstructor
public class Status {
	
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

	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
