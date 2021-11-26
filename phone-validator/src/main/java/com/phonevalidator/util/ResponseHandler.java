package com.phonevalidator.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class ResponseHandler {

    public <T> ResponseEntity<T> handleJsonOKStatus(T body) {
        return new ResponseEntity<>(body, createJSONTypeHeaders(MediaType.APPLICATION_JSON), HttpStatus.OK);
    }

    private HttpHeaders createJSONTypeHeaders(MediaType mediaType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(mediaType);
        headers.setDate(System.currentTimeMillis());
        return headers;
    }
}
