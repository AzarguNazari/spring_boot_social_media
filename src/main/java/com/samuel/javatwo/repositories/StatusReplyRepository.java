package com.samuel.javatwo.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.samuel.javatwo.models.StatusReply;

@Repository
public interface StatusReplyRepository extends CrudRepository <StatusReply, Long> {

}
