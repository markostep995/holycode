package com.business.holycode.controller;

import com.business.holycode.service.BusinessPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/business")
public class BusinessPlaceController {

    @Autowired
    private BusinessPlaceService businessPlaceService;

    @GetMapping("{id}")
    public void findById(@PathVariable final String id){
        businessPlaceService.findById(id);
    }

}
