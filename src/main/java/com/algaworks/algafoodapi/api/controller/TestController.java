package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teste")
public class TestController {

    @Autowired
    private KitchenRepository kitchenRepository;

//    @GetMapping("/search")
//    public List<Kitchen> searchByName(@RequestParam("name") String name){
//
//
////        List<Kitchen> result =  kitchenRepository.searchByName(name);
//
////        if( result == null){
////            return ResponseEntity.notFound().build();
////        }
//        return result;

//    }
}
