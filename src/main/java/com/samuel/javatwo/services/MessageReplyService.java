package com.samuel.javatwo.services;

import org.springframework.stereotype.Service;

import com.samuel.javatwo.models.Message;
import com.samuel.javatwo.models.MessageReply;
import com.samuel.javatwo.repositories.MessageReplyRepository;

@Service
public class MessageReplyService {
	private MessageReplyRepository messageReplyRepository;
	
	public MessageReplyService(MessageReplyRepository messageReplyRepository) {
		this.messageReplyRepository = messageReplyRepository;
	}

	public void saveMessageReply(MessageReply messageReply) {
		// TODO Auto-generated method stub
		messageReplyRepository.save(messageReply);
	}

	public void remove(Long reply_ID_to_delete) {
		// TODO Auto-generated method stub
		messageReplyRepository.delete(reply_ID_to_delete);
	}
}
