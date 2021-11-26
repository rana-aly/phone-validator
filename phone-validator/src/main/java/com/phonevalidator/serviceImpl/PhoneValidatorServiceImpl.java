package com.phonevalidator.serviceImpl;

import com.phonevalidator.enums.State;
import com.phonevalidator.service.PhoneValidatorService;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PhoneValidatorServiceImpl implements PhoneValidatorService {

    @Override
    public State validate(String phoneNumber, String regex) {
        Pattern pattern = Pattern.compile(regex); //regex
        Matcher matcher = pattern.matcher(phoneNumber); // number
        return matcher.matches() ? State.VALID : State.INVALID;
    }
}
