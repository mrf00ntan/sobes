package kz.yesserzhanov.sobes.controller;

import kz.yesserzhanov.sobes.db.entity.City;
import kz.yesserzhanov.sobes.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/integration")
public class CityController {
    private final CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody City save(@RequestBody City city){
        return cityService.save(city);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody List<City> getCities(@RequestParam(required = false) Long stateId){
        return cityService.getCitiesByStateId(stateId);
    }
}
