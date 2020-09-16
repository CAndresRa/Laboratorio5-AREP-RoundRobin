package edu.escuelaing.arep.roundrobin.controller;

import edu.escuelaing.arep.roundrobin.entity.Message;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@RestController
@RequestMapping("/")
public class ClientController {
    private final String[] restServices =  {"http://localhost:8080/message/add"};

    @PostMapping("/add")
    public void saveMessage(@RequestBody String message){
        System.out.println("Llego el mensaje: " + message);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        Message newMessage = new Message(message);
        HttpEntity<Message> request = new HttpEntity<>(newMessage, headers);
        ResponseEntity<Message> response = restTemplate.postForEntity(restServices[0], request, Message.class);
        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("Message Created");
            System.out.println(response.getBody());
        } else {
            System.out.println("Request Failed");
            System.out.println(response.getStatusCode());
        }

    }





}
