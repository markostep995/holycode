package com.business.holycode.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Getter
@Setter
@SuperBuilder
public class BusinessPlaceDto {
    String name;

    String address;

    ArrayList<DayDto> dayList;

    boolean open;
}
