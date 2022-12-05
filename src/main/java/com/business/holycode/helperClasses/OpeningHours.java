package com.business.holycode.helperClasses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OpeningHours {
    @JsonProperty("days")
    private Week week;
}
