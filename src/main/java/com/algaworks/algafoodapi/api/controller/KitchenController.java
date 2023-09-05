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
import java.util.Optional;

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
        return kitchenRepository.findAll();
    };



//    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> search(@PathVariable Long kitchenId) {
        Optional<Kitchen> kitchen = kitchenRepository.findById(kitchenId);

        return kitchen.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//        return ResponseEntity.status(HttpStatus.OK).body(kitchen);
//        return ResponseEntity.ok(kitchen);

//        HttpHeaders headers = new HttpHeaders();
//        headers.add(HttpHeaders.LOCATION, "http://localhost:8080/kitchens");
//
//        return ResponseEntity
//                .status(HttpStatus.FOUND)
//                .headers(headers)
//                .build();

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen add(@RequestBody Kitchen kitchen){
        return kitchenRegisterService.save(kitchen);
    }

    @PutMapping("/{kitchenId}")
    public ResponseEntity<Kitchen> update(@PathVariable Long kitchenId,
                                          @RequestBody Kitchen kitchen){

        Optional<Kitchen> currentKitchen = kitchenRepository.findById(kitchenId);

        if( currentKitchen.isPresent()) {
            BeanUtils.copyProperties(kitchen, currentKitchen.get(), "id");
            Kitchen updatedKitchen = kitchenRegisterService.save(currentKitchen.get());

            return ResponseEntity.ok(updatedKitchen);
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
