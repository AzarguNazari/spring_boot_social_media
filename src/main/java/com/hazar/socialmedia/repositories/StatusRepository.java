package com.hazar.socialmedia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hazar.socialmedia.models.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long>{

	@Query(value = "SELECT * FROM STATUSES WHERE WALL_ID = ?1", nativeQuery = true)
	List<Status> findWallStatuses(Long user_wall_id);


}
