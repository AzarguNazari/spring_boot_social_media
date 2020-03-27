package com.hazar.socialmedia.services;

import com.hazar.socialmedia.models.MessageReply;
import com.hazar.socialmedia.repositories.MessageReplyRepository;
import org.springframework.stereotype.Service;

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
		messageReplyRepository.deleteById(reply_ID_to_delete);
	}
}
