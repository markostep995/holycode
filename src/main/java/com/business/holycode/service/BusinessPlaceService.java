package com.business.holycode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class BusinessPlaceService {

    @Value("${holycode.businessPlaceApi}")
    private String businessPlaceApi;

    public void findById(String id) {
        final String uri = businessPlaceApi + id;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(uri, String.class);

        System.out.println(result);
    }
}
