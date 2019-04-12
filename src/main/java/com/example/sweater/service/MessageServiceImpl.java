package com.example.sweater.service;

import com.example.sweater.domain.Message;
import com.example.sweater.repos.MessageRepo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepo messageRepo;
    @Override
    public void save(Message message) {
        try {
            population();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> findByTag(String tag) {
        return null;
    }

    @Override
    public List<Message> findAll() {
        return null;
    }

    public void population() throws IOException {
        Reader in = new FileReader("H:\\Spring\\importer\\src\\main\\resources\\test_case.csv");

        Iterable<CSVRecord> records = CSVFormat.newFormat(';').withHeader("ssoid", "ts", "grp", "type", "subtype", "url", "orgid", "formid", "code", "ltpa", "sponse", "ymdh").parse(in);

        for (CSVRecord record : records) {
            messageRepo.save(new Message(record.get("ssoid"),
                    record.get("ts"))/*,
                    record.get("grp"),
                    record.get("type"),
                    record.get("subtype"),
                    record.get("url"),
                    record.get("orgid"),
                    record.get("formid"),
                    record.get("code"),
                    record.get("ltpa"),
                    record.get("sponse")*/);

            //System.out.println(record);
        }
    }
}
