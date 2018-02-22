package com.samuel.javatwo.services;

import org.springframework.stereotype.Service;

import com.samuel.javatwo.models.Role;
import com.samuel.javatwo.repositories.RoleRepository;

@Service
public class RoleService {
	private RoleRepository roleRepository;
	
	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Role findOne(Long ROLE_ID) {
		// TODO Auto-generated method stub
		return roleRepository.findOne(ROLE_ID);
	}
	
	
}
