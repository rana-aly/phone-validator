package com.phonevalidator.repositories;

import com.phonevalidator.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface CountryRepo extends CrudRepository<Country, Integer> {

    Country findByName(@Param("name") String name);
}
