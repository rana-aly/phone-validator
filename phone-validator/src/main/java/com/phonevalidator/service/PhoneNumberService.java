package com.phonevalidator.service;

import com.phonevalidator.dto.ListingPhoneNumberDTO;
import com.phonevalidator.dto.PhoneNumberDTO;
import com.phonevalidator.model.PhoneNumber;

import java.util.ArrayList;
import java.util.List;

public interface PhoneNumberService {

     boolean save(PhoneNumberDTO phoneNumberDTO);

     List<ListingPhoneNumberDTO> getAll();

     List<ListingPhoneNumberDTO> getAll(String state , String country);

}
