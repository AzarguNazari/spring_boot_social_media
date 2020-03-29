package com.hazar.socialmedia.services;

import com.hazar.socialmedia.interfaces.MessageServiceInterface;
import com.hazar.socialmedia.models.Message;
import com.hazar.socialmedia.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MessageService implements MessageServiceInterface {

	@Autowired
	private MessageRepository messageRepository;

	public void saveMessage(Message message) {
    	messageRepository.save(message);
    }

	@Override
	public void deleteMessage(Long messageID) {
		messageRepository.deleteById(messageID);
	}

	@Override
	public Optional<Message> getMessage(Long messageID) {
		return messageRepository.findById(messageID);
	}

	@Override
	public List<Message> getMessages() {
		return messageRepository.findAll();
	}
}
