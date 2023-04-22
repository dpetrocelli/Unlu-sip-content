package com.example.helloworld.services;

import com.example.helloworld.controllers.dtos.MessageDto;
import com.example.helloworld.repository.MessageRepository;
import com.example.helloworld.services.exceptions.MessageNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.helloworld.models.Message;

@Service
public class MessageService {

  private MessageRepository messageRepository;

  private ObjectMapper objectMapper;

  @Autowired
  public MessageService(MessageRepository messageRepository, ObjectMapper mapper) {
    this.messageRepository = messageRepository;
    this.objectMapper = mapper;
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

  public Message saveMessage(MessageDto messageDto) {
    Message message = objectMapper.convertValue(messageDto, Message.class);
    return messageRepository.save(message);
  }
}
