package com.hazar.socialmedia.services;

import com.hazar.socialmedia.models.Role;
import com.hazar.socialmedia.repositories.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
	private RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Role findOne(Long ROLE_ID) {
		// TODO Auto-generated method stub
		return roleRepository.findById(ROLE_ID).get();
	}
	
	
}
