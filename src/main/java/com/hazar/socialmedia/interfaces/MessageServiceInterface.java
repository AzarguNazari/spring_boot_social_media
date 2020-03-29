package com.hazar.socialmedia.interfaces;

import com.hazar.socialmedia.models.Message;

import java.util.List;
import java.util.Optional;

public interface MessageServiceInterface {
    void saveMessage(Message message);

    void deleteMessage(Long messageID);

    Optional<Message> getMessage(Long messageID);

    List<Message> getMessages();
}
