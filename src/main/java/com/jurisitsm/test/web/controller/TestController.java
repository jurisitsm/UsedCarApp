package com.jurisitsm.test.web.controller;

import com.jurisitsm.test.repository.CarRepository;
import com.jurisitsm.test.web.dto.CarDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class TestController {

    @Autowired
    private CarRepository carRepository;

    @GetMapping("/cars")
    public List<CarDto> getCars(){
        return carRepository.findAll().stream()
                .map(car -> new CarDto(car.getBrand(), car.getColor()))
                .collect(Collectors.toList());
    }
}
