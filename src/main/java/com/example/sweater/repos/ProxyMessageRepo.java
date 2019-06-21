package com.example.sweater.repos;

import com.example.sweater.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProxyMessageRepo extends JpaRepository<Message, Long> {
    List<Message> findByTs(String ts);

    @Override
    List<Message> findAll();

    @Override
    @Transactional
    public Message save(Message message);
}
