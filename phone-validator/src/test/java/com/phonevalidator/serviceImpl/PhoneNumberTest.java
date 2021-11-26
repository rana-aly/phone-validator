package com.phonevalidator.serviceImpl;

import com.phonevalidator.dto.ListingPhoneNumberDTO;
import com.phonevalidator.dto.PhoneNumberDTO;
import com.phonevalidator.enums.State;
import com.phonevalidator.model.Country;
import com.phonevalidator.model.PhoneNumber;
import com.phonevalidator.repositories.CountryRepo;
import com.phonevalidator.repositories.PhoneNumberRepo;
import com.phonevalidator.service.PhoneNumberService;
import com.phonevalidator.service.PhoneValidatorService;
import org.dozer.DozerBeanMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = {"com"})
@TestPropertySource(locations="classpath:application.properties")
@SpringBootTest
class PhoneNumberTest {
    @Autowired
    PhoneNumberService phoneNumberService;

    @Autowired
    PhoneValidatorService phoneNumberValidatorService;

    @Autowired
    PhoneNumberRepo phoneNumberRepo;

    @Autowired
    CountryRepo countryRepo;

    int totalNumbers = 0;

    @BeforeEach
    void setUp(){
        totalNumbers = phoneNumberService.getAll().size();
    }

    @Test
    void getAll() {
        // test listing all numbers
        List<ListingPhoneNumberDTO>  result = phoneNumberService.getAll();
        assertEquals(totalNumbers, result.size());
    }

    @Test
    void search() {
        // Valid search
        List<ListingPhoneNumberDTO> result = phoneNumberService.getAll(State.VALID.toString(), "Cameroon");
        assertEquals(7, result.size());
        // Invalid Search
        result = phoneNumberService.getAll(State.VALID.toString(), "test");
        assertEquals(0, result.size());
    }

    @Test
    void save() {
        PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO();
        phoneNumberDTO.setPhone("invalid number");
        phoneNumberDTO.setName("invalid user");
        phoneNumberDTO.setCountryName("Morocco");
        phoneNumberService.save(phoneNumberDTO);
        PhoneNumber savedPhoneNumber = phoneNumberRepo.findByName("invalid user").get(0);
        assertEquals(savedPhoneNumber.getState(), State.INVALID);
    }

    @Test
    void validate() {
        Country country = countryRepo.findByName("Morocco");
        // invalid phone number
        State state = phoneNumberValidatorService.validate("ash" , country.getRegex());
        assertEquals(state, State.INVALID);
        // valid phone number
        state = phoneNumberValidatorService.validate("(212) 698054317" , country.getRegex());
        assertEquals(state, State.VALID);
    }

}