package com.samuel.javatwo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.samuel.javatwo.models.Message;
import com.samuel.javatwo.repositories.MessageRepository;

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
		return messageRepository.findOne(message_id);
	}

	public void remove(Message message_to_delete) {
		// TODO Auto-generated method stub
		messageRepository.delete(message_to_delete);
	}
}
