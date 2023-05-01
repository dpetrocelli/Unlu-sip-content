package com.example.helloworld.models;

import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Data
@NoArgsConstructor
public class Message {
  @Id
  @Column(name = "id", nullable = false)
  private UUID id;

  private String content;
  @CreatedDate
  @Column(name = "created_date")
  private LocalDateTime createdDate;

  @LastModifiedDate
  @Column(name = "last_modified_date")
  private LocalDateTime lastModifiedDate;

  private Message(String content) {
    this.content = content;
  }

  public static Message from(final String text) {
    return new Message(text);
  }

  @PrePersist
  public void prePersist() {
    this.id = UUID.randomUUID();
    createdDate = lastModifiedDate = LocalDateTime.now();
  }

  @PreUpdate
  public void preUpdate() {
    lastModifiedDate = LocalDateTime.now();
  }
}
