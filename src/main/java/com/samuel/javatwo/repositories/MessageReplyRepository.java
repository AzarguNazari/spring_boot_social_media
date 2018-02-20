package com.samuel.javatwo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.samuel.javatwo.models.MessageReply;

@Repository
public interface MessageReplyRepository extends CrudRepository <MessageReply, Long>{
	
}
