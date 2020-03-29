package com.facebook.example.repositories;

import com.facebook.example.models.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleRepository extends CrudRepository<Role, Long>{
    List<Role> findAll();
    
    List<Role> findByName(String name);
}
