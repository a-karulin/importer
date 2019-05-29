package com.example.sweater.service;

import com.example.sweater.domain.Message;

import java.util.List;

public interface MessageService {

    void save(Message message);
    List<Message> findByTs(String ts);
    List<Message> findAll();
    public void population();
    public void findBiggestPunkt();
}
