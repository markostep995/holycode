package com.business.holycode.helperClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Week {

    @JsonProperty("monday")
    private ArrayList<Shift> monday;

    @JsonProperty("tuesday")
    private ArrayList<Shift> tuesday;

    @JsonProperty("wednesday")
    private ArrayList<Shift> wednesday;

    @JsonProperty("thursday")
    private ArrayList<Shift> thursday;

    @JsonProperty("friday")
    private ArrayList<Shift> friday;

    @JsonProperty("saturday")
    private ArrayList<Shift> saturday;

    @JsonProperty("sunday")
    private ArrayList<Shift> sunday;
}
