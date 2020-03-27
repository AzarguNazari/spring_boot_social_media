package com.hazar.socialmedia.services;

import java.util.List;

import com.hazar.socialmedia.models.Message;
import com.hazar.socialmedia.repositories.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
	private MessageRepository messageRepository;
	
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
    public void saveMessage(Message message) {
    	
    	messageRepository.save(message);
    }
    public List<Message> findWallMessages(Long user_wall_id) {
    	return messageRepository.findWallMessages(user_wall_id);
    }

	public Message findOne(Long message_id) {
		// TODO Auto-generated method stub
		return messageRepository.findById(message_id).get();
	}

	public void remove(Message message_to_delete) {
		// TODO Auto-generated method stub
		messageRepository.delete(message_to_delete);
	}
}
