package com.phonevalidator.service;

import com.phonevalidator.enums.State;

public interface PhoneValidatorService {

    State validate(String phoneNumber, String regex);
}
