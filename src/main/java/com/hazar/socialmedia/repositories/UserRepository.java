package com.hazar.socialmedia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hazar.socialmedia.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	List<User> findAll();
	User findByEmail(String email);
	
	@Query(value = "SELECT * FROM friendships WHERE friend_id != ?1", nativeQuery=true)
	List<User> selectNotFriends(Long id);
	
	@Query(value = "SELECT * FROM friendships", nativeQuery=true)
	List<User> selectAllFriendships();
	
	List<User> findByFriendsNotLike(User loggedUser);
	
	List<User> findByNameContaining(String string);
	
	List<User> findByStateContaining(String string);
	
	List<User> findByCityContaining(String city);
	
	@Query(value = "SELECT email FROM users", nativeQuery=true)
	List<String> findAllEmails();

}
