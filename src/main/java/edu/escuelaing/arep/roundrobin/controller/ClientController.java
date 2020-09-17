package edu.escuelaing.arep.roundrobin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.escuelaing.arep.roundrobin.entity.Message;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/")
public class ClientController {
    private final String[] restServicesAdd =  {"http://localhost:8081/message/add",
                                               "http://localhost:8082/message/add",
                                               "http://localhost:8083/message/add"};

    private final String[] restServicesGet =  {"http://localhost:8081/message/get",
                                               "http://localhost:8082/message/get",
                                               "http://localhost:8083/message/get"};

    private int nService = 0;

    @PostMapping("/add")
    public void saveMessage(@RequestBody String addMessage) throws UnsupportedEncodingException {
        System.out.println(nService);
        // Decode UTF8
        String message = URLDecoder.decode(addMessage, StandardCharsets.UTF_8.toString());

        // Se puede poner como un @bean para desacoplar.
        RestTemplate restTemplate = new RestTemplate();

        // Creando llamada para el otro microservicio -header-
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        // Crando objeto para microservicio
        Message newMessage = new Message(message);
        HttpEntity<Message> request = new HttpEntity<>(newMessage, headers);

        // Envio de request
        ResponseEntity<Message> response = restTemplate.postForEntity(restServicesAdd[service()], request, Message.class);

        //Estado de request
        System.out.println(response.getStatusCode());
    }

    @GetMapping("/messages")
    public List<Message> showList() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        //Simple Get
        ResponseEntity<String> response = restTemplate.getForEntity(restServicesGet[service()], String.class );
        String json = response.getBody();
        //List<Message> messages = new ObjectMapper().readValue(json, new TypeReference<List<Message>>() {});
        // Se puede retornar lo de arriba directamente
        return new ObjectMapper().readValue(json, new TypeReference<List<Message>>() {});
    }

    /**
     * Balanceador de carga (Elige que servicio usar para responder la llamada)
     * @return numero que define el servicio a utiliza ( 0, 1, 2 )
     */
    private int service(){
        if(nService == 3){
            this.nService = 0;
        } else {
            this.nService++;
        }
        return nService - 1;
    }

}
