package com.hazar.socialmedia.repositories;

import com.hazar.socialmedia.models.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends CrudRepository<Message, Long>{
    List<Message> findAll();
}
