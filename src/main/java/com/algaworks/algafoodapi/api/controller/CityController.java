package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.domain.exception.EntityBeingUsedException;
import com.algaworks.algafoodapi.domain.exception.EntityNotFoundException;
import com.algaworks.algafoodapi.domain.model.City;
import com.algaworks.algafoodapi.domain.model.Estate;
import com.algaworks.algafoodapi.domain.repository.CityRepository;
import com.algaworks.algafoodapi.domain.service.CityRegisterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityRegisterService cityRegisterService;

    @GetMapping
    public List<City> list() {
        return cityRepository.findAll();
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> search(@PathVariable Long cityId) {
        Optional<City> city = cityRepository.findById(cityId);

        return city.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City add(@RequestBody City city){
            return cityRegisterService.save(city);
    }


    @PutMapping("{cityId}")
    public ResponseEntity<City> update(@PathVariable Long cityId,
                                       @RequestBody City city){

        Optional<City> currentCity = cityRepository.findById(cityId);

        if(currentCity.isPresent()) {
            BeanUtils.copyProperties(city, currentCity.get(), "id");
            City updatedCity = cityRegisterService.save(currentCity.get());

            return ResponseEntity.ok(updatedCity);
        }
        return ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> delete(@PathVariable Long cityId){
        try {
            cityRepository.deleteById(cityId);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityBeingUsedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
