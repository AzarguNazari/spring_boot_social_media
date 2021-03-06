package com.facebook.example.services;

import com.facebook.example.interfaces.RoleServiceInterface;
import com.facebook.example.models.Role;
import com.facebook.example.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements RoleServiceInterface {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Optional<Role> getRole(Long ROLE_ID) {
		return roleRepository.findById(ROLE_ID);
	}

	@Override
	public void saveRole(Role role) {
		roleRepository.save(role);
	}

	@Override
	public void deleteRole(Long roleID) {
		roleRepository.deleteById(roleID);
	}

	@Override
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}
}
