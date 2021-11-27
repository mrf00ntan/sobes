package kz.yesserzhanov.sobes.db.repository;

import kz.yesserzhanov.sobes.db.entity.City;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CityRepository {
    private static List<City> cities = new ArrayList<>();

    public City save(City city) {
        cities.add(city);
        return city;
    }
}
