package com.phonevalidator.controller;

import com.phonevalidator.dto.ListingPhoneNumberDTO;
import com.phonevalidator.dto.PhoneNumberDTO;
import com.phonevalidator.model.PhoneNumber;
import com.phonevalidator.repositories.PhoneNumberRepo;
import com.phonevalidator.util.ResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.phonevalidator.service.PhoneNumberService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PhoneNumberController {
    @Autowired
    PhoneNumberService phoneNumberService;

    @Autowired
    ResponseHandler responseHandler;

    @GetMapping("/numbers")
    public ResponseEntity<List> getNumbers() {
        List<ListingPhoneNumberDTO> body = phoneNumberService.getAll();
        return responseHandler.handleJsonOKStatus(body);
    }

    @GetMapping("/numbers/{state}/{country}")
    public ResponseEntity<List> searchNumbers(@PathVariable String state, @PathVariable String country) {
        List<ListingPhoneNumberDTO> body = phoneNumberService.getAll(state, country);
        return responseHandler.handleJsonOKStatus(body);
    }

    @PostMapping("/numbers")
    public boolean validatePhoneNumber (@RequestBody PhoneNumberDTO phoneNumberBody) {
        phoneNumberService.save(phoneNumberBody);
        return true;
    }

}
