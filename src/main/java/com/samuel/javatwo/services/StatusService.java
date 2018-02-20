package com.samuel.javatwo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.samuel.javatwo.models.Status;
import com.samuel.javatwo.repositories.StatusRepository;

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
		return statusRepository.findOne(status_to_delete_id);
	}

	public void remove(Status status_to_delete) {
		// TODO Auto-generated method stub
		statusRepository.delete(status_to_delete);
	}
    public List<Status> findWallStatuses(Long user_wall_id) {
    	return statusRepository.findWallStatuses(user_wall_id);
    }
}
