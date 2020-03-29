package com.facebook.example.interfaces;

import com.facebook.example.models.Message;

import java.util.List;
import java.util.Optional;

public interface MessageServiceInterface {
    void saveMessage(Message message);

    void deleteMessage(Long messageID);

    Optional<Message> getMessage(Long messageID);

    List<Message> getMessages();
}
