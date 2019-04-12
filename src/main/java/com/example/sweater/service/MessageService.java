package com.example.sweater.service;

import com.example.sweater.domain.Message;

import java.util.List;

public interface MessageService {

    void save(Message message);
    List<Message> findByTag(String tag);
    List<Message> findAll();
}
