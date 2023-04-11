package com.example.helloworld.services;

import com.example.helloworld.repository.MessageRepository;
import com.example.helloworld.services.exceptions.MessageNotFoundException;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloworld.models.Message;

@Service
public class MessageService {

  private MessageRepository messageRepository;

  @Autowired
  public MessageService(MessageRepository messageRepository) {
    this.messageRepository = messageRepository;
  }

  public Message findById(UUID uuid) throws MessageNotFoundException {
    return messageRepository.findById(uuid).orElseThrow(MessageNotFoundException::new);
  }
  public Message getPublicMessage() {
    final var text = "This is a public message.";

    return Message.from(text);
  }

  public Message getProtectedMessage() {
    final var text = "This is a protected message.";

    return Message.from(text);
  }

  public Message getAdminMessage() {
    final var text = "This is an admin message.";

    return Message.from(text);
  }
}
