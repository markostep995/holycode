package com.business.holycode.dto;

import com.business.holycode.helperClasses.Shift;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class DayDto {

    String name;

    List<Shift> shiftList;
}
