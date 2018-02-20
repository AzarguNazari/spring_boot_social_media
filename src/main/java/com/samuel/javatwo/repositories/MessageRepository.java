package com.samuel.javatwo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.samuel.javatwo.models.Message;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{

	  @Query(value = "SELECT * FROM MESSAGES WHERE USER_WALL_ID = ?1", nativeQuery = true)
	  List<Message> findWallMessages(Long user_wall_id);
		
}
