package com.hazar.socialmedia.services;

import java.util.List;

import com.hazar.socialmedia.models.Status;
import com.hazar.socialmedia.repositories.StatusRepository;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
	private StatusRepository statusRepository;
	
	public StatusService(StatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}

	public void saveStatus(Status status) {
		// TODO Auto-generated method stub
		statusRepository.save(status);
	}

	public void saveTheStatus(Status status) {
		// TODO Auto-generated method stub
		statusRepository.save(status);
	}

	public Status findOne(Long status_to_delete_id) {
		// TODO Auto-generated method stub
		return statusRepository.findById(status_to_delete_id).get();
	}

	public void remove(Status status_to_delete) {
		// TODO Auto-generated method stub
		statusRepository.delete(status_to_delete);
	}
    public List<Status> findWallStatuses(Long user_wall_id) {
    	return statusRepository.findWallStatuses(user_wall_id);
    }
}
