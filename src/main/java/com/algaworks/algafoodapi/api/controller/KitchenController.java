package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.model.KitchenXmlWrapper;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import org.mockito.hamcrest.MockitoHamcrest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public List<Kitchen> list(){
        return kitchenRepository.list();
    };

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public KitchenXmlWrapper listXml(){
        return new KitchenXmlWrapper(kitchenRepository.list());
    }


//    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> search(@PathVariable Long kitchenId) {
        Kitchen kitchen = kitchenRepository.search(kitchenId);

//        return ResponseEntity.status(HttpStatus.OK).body(kitchen);
//        return ResponseEntity.ok(kitchen);

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/kitchens");

        return ResponseEntity
                .status(HttpStatus.FOUND)
                .headers(headers)
                .build();
    }
}
