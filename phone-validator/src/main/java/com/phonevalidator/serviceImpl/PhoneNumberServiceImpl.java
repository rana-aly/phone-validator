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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

    @Autowired
    DozerBeanMapper mapper;

    @Autowired
    PhoneValidatorService phoneValidatorService;

    @Autowired
    CountryRepo countryRepo;

    @Autowired
    PhoneNumberRepo phoneNumberRepo;

    @Override
    public boolean save(PhoneNumberDTO phoneNumberDTO) {
        // mapping
        PhoneNumber phoneNumber = mapper.map(phoneNumberDTO, PhoneNumber.class);
        Country country = countryRepo.findByName(phoneNumberDTO.getCountryName());
        // validation
        State numberState = phoneValidatorService.validate(phoneNumber.getPhone(), country.getRegex());
        //save repo
        phoneNumber.setCountry(country);
        phoneNumber.setState(numberState);
        phoneNumberRepo.save(phoneNumber);
        return true;
    }

    @Override
    public List<ListingPhoneNumberDTO> getAll() {
        List<PhoneNumber> phoneNumberEntities = phoneNumberRepo.findAll();
        return getPhoneNumberDto(phoneNumberEntities);
    }

    @Override
    public List<ListingPhoneNumberDTO> getAll(String state, String country) {
        List<PhoneNumber> phoneNumberEntities = phoneNumberRepo.search(State.valueOf(state), country);
        return getPhoneNumberDto(phoneNumberEntities);
    }

    private List<ListingPhoneNumberDTO> getPhoneNumberDto(List<PhoneNumber> phoneNumberEntities){
        List<ListingPhoneNumberDTO> phoneNumberDTOS = new ArrayList<>();
        phoneNumberEntities.forEach(phoneNumber -> {
            ListingPhoneNumberDTO dto = mapper.map(phoneNumber, ListingPhoneNumberDTO.class);
            dto.setCountryName(phoneNumber.getCountry().getName());
            phoneNumberDTOS.add(dto);
        });
        return phoneNumberDTOS;
    }

}
