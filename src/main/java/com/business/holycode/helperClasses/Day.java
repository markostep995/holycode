package com.business.holycode.helperClasses;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;

@Getter
@Setter
@SuperBuilder
public class Day {
    private String name;

    private ArrayList<Shift> shifts;

}
