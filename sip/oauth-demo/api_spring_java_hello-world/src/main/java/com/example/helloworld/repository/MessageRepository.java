package com.example.helloworld.repository;

import com.example.helloworld.models.Message;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message, UUID> {


  List<Message> findByContent(String content);

}
