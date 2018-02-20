package com.samuel.javatwo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.samuel.javatwo.models.Status;

@Repository
public interface StatusRepository extends CrudRepository<Status, Long>{


}
