package com.hazar.socialmedia.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hazar.socialmedia.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long>{
    List<Role> findAll();
    
    List<Role> findByName(String name);
}
