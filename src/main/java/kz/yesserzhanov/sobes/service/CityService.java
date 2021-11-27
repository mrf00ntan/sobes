package kz.yesserzhanov.sobes.service;

import kz.yesserzhanov.sobes.db.entity.City;

import java.util.List;

public interface CityService {
    List<City> getCitiesByStateId(Long stateId);
    City save(City city);
}
