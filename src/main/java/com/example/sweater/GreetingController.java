package com.example.sweater;

import com.example.sweater.domain.Message;
import com.example.sweater.repos.MessageRepo;
import com.example.sweater.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class GreetingController {
    @Autowired
    private MessageService messageService;

    @GetMapping("/greeting")
    public String greeting(
            @RequestParam(name="name", required=false, defaultValue="World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "greeting";
    }

    @GetMapping
    public String main(Map<String, Object> model) {
        Iterable<Message> messages = messageService.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping
    public String add(@RequestParam String ssoid, @RequestParam Long ts, @RequestParam String grp,
                      @RequestParam String type,@RequestParam String subtype,@RequestParam String url,
                      @RequestParam String orgid, @RequestParam String formid, @RequestParam String code, 
                      @RequestParam String ltpa, @RequestParam String sudirresponse, @RequestParam String ymdh,
                      Map<String, Object> model) {
        Message message = new Message(ssoid, ts, grp, type, subtype, url, orgid, formid, code, ltpa, sudirresponse, ymdh);

        messageService.save(message);

        Iterable<Message> messages = messageService.findAll();

        model.put("messages", messages);

        return "main";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageService.findByTs(filter);
        } else {
            messages = messageService.findAll();
        }

        model.put("messages", messages);


        return "main";
    }

    @PostMapping("populate_db")
    public String populate() {
        messageService.population();
        return "main";
    }

    @PostMapping("userAnalitic")
    public String userAnalitic() {
        messageService.findBiggestPunkt();
        return "userAnalitic";
    }




}
