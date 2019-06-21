package com.example.sweater.repos;

import com.example.sweater.domain.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class MessageRepo {
    @Autowired
    private ProxyMessageRepo proxyMessageRepo;

    public Message save(Message message){
        proxyMessageRepo.save(message);
        return null;
    }
    public List<Message> findAll(){
        return proxyMessageRepo.findAll();
    }
    public List<Message> findByTs(String ts){
        return proxyMessageRepo.findByTs(ts);
    }


}

