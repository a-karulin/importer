package com.example.sweater.service;

import com.example.sweater.domain.Message;
import com.example.sweater.repos.MessageRepo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepo messageRepo;
    @Override
    public void save(Message message) {
        messageRepo.save(message);
    }

    public void population() {
        try {Reader in = new FileReader("H:\\Spring\\importer\\src\\main\\resources\\test_case.csv");

            Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader("ssoid", "ts", "grp", "type", "subtype", "url", "orgid", "formid", "code", "ltpa", "sudirresponse", "ymdh").parse(in);
            int i=0;
            for (CSVRecord record : records) {
                if(record.get("ssoid").equals("ssoid")){
                    continue;
                }else{
                    messageRepo.save(new Message(record.get("ssoid"),
                            Long.parseLong(record.get("ts")),
                            record.get("grp"),
                            record.get("type"),
                            record.get("subtype"),
                            record.get("url"),
                            record.get("orgid"),
                            record.get("formid"),
                            record.get("code"),
                            record.get("ltpa"),
                            record.get("sudirresponse"),
                            record.get("ymdh")));


                    //System.out.println(record);
                    if (i>=1000){
                        break;
                    }else {i++;}}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> findByTs(String ts) {
        return messageRepo.findByTs(ts);
    }

    @Override
    public List<Message> findAll() {
        return messageRepo.findAll();
    }

    public void findBiggestPunkt(){
        List<Long> ts= new ArrayList<>();

        for(int i= 0; i < findAll().size(); i++){
            System.out.println(ts.add(findAll().get(i).getTs()));

        }

    }


}
