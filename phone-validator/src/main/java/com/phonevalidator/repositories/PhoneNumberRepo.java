package com.phonevalidator.repositories;

import com.phonevalidator.enums.State;
import com.phonevalidator.model.PhoneNumber;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Iterator;
import java.util.List;

@Repository
public interface PhoneNumberRepo  extends CrudRepository<PhoneNumber, Integer> {

    @Query("SELECT phoneNumber FROM PhoneNumber phoneNumber INNER JOIN phoneNumber.country country WHERE country.name = :countryName AND phoneNumber.state = :state")
    List<PhoneNumber> search(@Param("state") State state, @Param("countryName") String countryName);

    @Query("SELECT phoneNumber FROM PhoneNumber phoneNumber")
    List<PhoneNumber> findAll();

    @Query("SELECT phoneNumber FROM PhoneNumber phoneNumber where phoneNumber.name = :name")
    List<PhoneNumber> findByName(@Param("name") String name);
}

