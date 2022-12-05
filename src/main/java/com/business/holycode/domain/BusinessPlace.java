package com.business.holycode.domain;

import com.business.holycode.helperClasses.Day;
import com.business.holycode.helperClasses.OpeningHours;
import com.business.holycode.dto.DayDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;

@Getter
@Setter
public class BusinessPlace {
    @JsonProperty("displayed_what")
    String name;

    @JsonProperty("displayed_where")
    String address;

    @JsonProperty("opening_hours")
    OpeningHours openingHours;

    ArrayList<Day> daysInWeek;

    ArrayList<DayDto> dayList;


}
