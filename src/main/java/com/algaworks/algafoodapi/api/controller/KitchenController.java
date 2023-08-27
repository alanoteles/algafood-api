package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntityBeingUsedException;
import com.algaworks.algafoodapi.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi.domain.model.Kitchen;
import com.algaworks.algafoodapi.domain.repository.KitchenRepository;
import com.algaworks.algafoodapi.domain.service.KitchenRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenRepository kitchenRepository;

    @Autowired
    private KitchenRegisterService kitchenRegisterService;

//    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping
    public List<Kitchen> list(){
        return kitchenRepository.list();
    };



//    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> search(@PathVariable Long kitchenId) {
        Kitchen kitchen = kitchenRepository.search(kitchenId);

        if (kitchen != null){
           return ResponseEntity.ok(kitchen);
        }
//        return ResponseEntity.status(HttpStatus.OK).body(kitchen);
//        return ResponseEntity.ok(kitchen);

//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/kitchens");
//
//        return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .headers(headers)
//                .build();

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen add(@RequestBody Kitchen kitchen){
        return kitchenRegisterService.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId,
                                          @RequestBody Kitchen kitchen){

        Kitchen currentKitchen = kitchenRepository.search(kitchenId);

        if( currentKitchen != null) {
            BeanUtils.copyProperties(kitchen, currentKitchen, "id");

            currentKitchen = kitchenRegisterService.save(currentKitchen);

            return ResponseEntity.ok(currentKitchen);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> delete(@PathVariable Long kitchenId) {
        try {
            kitchenRegisterService.delete(kitchenId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();

        } catch (EntityBeingUsedException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
