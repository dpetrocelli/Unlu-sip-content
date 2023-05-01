package com.example.helloworld.controllers;

import com.example.helloworld.models.Message;
import com.example.helloworld.services.MessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

class MessageControllerTest {

  private MessageController messageController;
  private MessageService messageService;

  @BeforeEach
  void setUp() {
    this.messageService = Mockito.mock(MessageService.class);
    this.messageController = new MessageController(messageService);
  }

  @Test
  public void getPublicSuccess() {
    Mockito.when(messageService.getPublicMessage()).thenReturn(Message.from("Success message"));
    ResponseEntity<Object> response = messageController.getPublic();
    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertNotNull(response.getBody());
    Assertions.assertTrue(response.getBody() instanceof Message);
    Assertions.assertEquals("Success message", ((Message) response.getBody()).getContent());
  }

  @Test
  public void getPublicUnexpectedError() {
    Mockito.when(messageService.getPublicMessage()).thenThrow(new RuntimeException("Ups..."));
    ResponseEntity<Object> response = messageController.getPublic();
    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    Assertions.assertNotNull(response.getBody());
    Assertions.assertEquals("Unknown error", response.getBody());
  }

}