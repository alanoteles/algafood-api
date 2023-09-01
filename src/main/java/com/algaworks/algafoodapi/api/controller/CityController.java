package com.algaworks.algafoodapi.api.controller;

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

@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CityRegisterService cityRegisterService;

    @GetMapping
    public List<City> list() {
        return cityRepository.list();
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<City> search(@PathVariable Long cityId) {
        City city = cityRepository.search(cityId);

        if (city != null) {
            return ResponseEntity.ok(city);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public City add(@RequestBody City city){
        return cityRegisterService.save(city);
    }


    @PutMapping("{cityId}")
    public ResponseEntity<City> update(@PathVariable Long cityId,
                                       @RequestBody City city){

        City currentCity = cityRepository.search(cityId);

        if(currentCity != null) {
            BeanUtils.copyProperties(city, currentCity, "id");
            cityRegisterService.save(currentCity);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }


}
