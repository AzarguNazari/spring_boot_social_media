package com.hazar.socialmedia.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hazar.socialmedia.models.MessageReply;

@Repository
public interface MessageReplyRepository extends CrudRepository <MessageReply, Long>{
	
}
