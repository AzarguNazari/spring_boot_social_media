package com.hazar.socialmedia.services;

import com.hazar.socialmedia.models.StatusReply;
import com.hazar.socialmedia.repositories.StatusReplyRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusReplyService {
	private StatusReplyRepository statusReplyRepository;
	
	public StatusReplyService(StatusReplyRepository statusReplyRepository) {
		this.statusReplyRepository = statusReplyRepository;
	}

	public void saveStatusReply(StatusReply status_to_reply_to) {
		// TODO Auto-generated method stub
		statusReplyRepository.save(status_to_reply_to);
	}

	public void delete(Long status_reply_id) {
		// TODO Auto-generated method stub
		statusReplyRepository.deleteById(status_reply_id);
	}

	public void removeStatusReplies(Long status_id) {
		// TODO Auto-generated method stub
		statusReplyRepository.removeStatusReplies(status_id);
	}
}
