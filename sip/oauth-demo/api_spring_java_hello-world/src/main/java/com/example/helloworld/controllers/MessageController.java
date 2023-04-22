package com.example.helloworld.controllers;

import com.example.helloworld.controllers.dtos.MessageDto;
import com.example.helloworld.services.exceptions.MessageNotFoundException;
import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.helloworld.models.Message;
import com.example.helloworld.services.MessageService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/messages")
@Slf4j
public class MessageController {

  private final MessageService messageService;

  @GetMapping("/public")
  public ResponseEntity<Object> getPublic() {
    try {
      Message publicMessage = messageService.getPublicMessage();
      return new ResponseEntity<>(publicMessage, HttpStatus.OK);
    }
    catch (Exception e) {
      return new ResponseEntity<>("Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/protected")
  public Message getProtected() {
    return messageService.getProtectedMessage();
  }

  @GetMapping("/admin")
  @PreAuthorize("hasAuthority('read:admin-messages')")
  public Message getAdmin() {
    return messageService.getAdminMessage();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> findById(@PathVariable UUID id) {
    try {
      Message message = messageService.findById(id);
      return new ResponseEntity<>(message, HttpStatus.OK);
    } catch (MessageNotFoundException e) {
      return new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>("Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping
  public ResponseEntity<Object> saveMessage(@RequestBody MessageDto message) {
    try {
      Message savedMessage = messageService.saveMessage(message);
      return new ResponseEntity<>(savedMessage, HttpStatus.OK);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return new ResponseEntity<>("Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
