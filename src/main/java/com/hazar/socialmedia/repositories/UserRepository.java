package com.hazar.socialmedia.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hazar.socialmedia.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	List<User> findAll();

	Optional<User> findByEmail(String email);
}