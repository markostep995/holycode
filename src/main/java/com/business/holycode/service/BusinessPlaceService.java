package com.business.holycode.service;

import com.business.holycode.domain.BusinessPlace;
import com.business.holycode.dto.BusinessPlaceDto;
import com.business.holycode.helperClasses.Day;
import com.business.holycode.dto.DayDto;
import com.business.holycode.helperClasses.Shift;
import com.business.holycode.helperClasses.Week;
import com.business.holycode.utils.JsonToDtoMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BusinessPlaceService {

    @Value("${holycode.businessPlaceApi}")
    private String businessPlaceApi;

    public BusinessPlaceDto findById(String id) {
        final String url = businessPlaceApi + id;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        try {
            BusinessPlace businessPlace = (BusinessPlace) JsonToDtoMapper.mapToDto(result, BusinessPlace.class);
            groupDaysWithSameOpeningHours(businessPlace);
            return mapBusinessPlaceToBusinessPlaceDTO(businessPlace);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BusinessPlaceDto mapBusinessPlaceToBusinessPlaceDTO(BusinessPlace businessPlace) {
        return BusinessPlaceDto.builder()
                .name(businessPlace.getName())
                .address(businessPlace.getAddress())
                .dayList(businessPlace.getDayList())
                .build();
    }

    private void groupDaysWithSameOpeningHours(BusinessPlace businessPlace) {
        ArrayList<Day> daysInWeek = createListOfDaysInWeek(businessPlace.getOpeningHours().getWeek());

        ArrayList<DayDto> daysDto = new ArrayList<>();
        int counter = 0;
        for (int i = 0; i < daysInWeek.size() - 1; i++) {
            String day = daysInWeek.get(i).getName();
            if (isTheSame(daysInWeek.get(i).getShifts(), daysInWeek.get(i + 1).getShifts())) {
                if (i == 5) {
                    daysDto.add(DayDto.builder().name(daysInWeek.get(i - counter).getName() + "-" + daysInWeek.get(i + 1).getName()).shiftList(businessPlace.getDaysInWeek().get(i).getShifts()).build());
                }
                counter++;
            } else {
                if (counter == 0) {
                    daysDto.add(DayDto.builder().name(day).shiftList(daysInWeek.get(i).getShifts()).build());
                    if (i == 5) {
                        daysDto.add(DayDto.builder().name(daysInWeek.get(i + 1).getName()).shiftList(daysInWeek.get(i + 1).getShifts()).build());
                    }
                    counter = 0;
                } else {
                    daysDto.add(DayDto.builder().name(daysInWeek.get(i - counter).getName() + "-" + daysInWeek.get(i).getName()).shiftList(daysInWeek.get(i).getShifts()).build());
                    counter = 0;
                }
            }
        }
        businessPlace.setDayList(daysDto);
    }

    private ArrayList<Day> createListOfDaysInWeek(Week week) {
        ArrayList<Day> daysInWeek = new ArrayList<>();
        Day monday = Day.builder().name("Monday").shifts(week.getMonday()).build();
        Day tuesday = Day.builder().name("Tuesday").shifts(week.getTuesday()).build();
        Day wednesday = Day.builder().name("Wednesday").shifts(week.getWednesday()).build();
        Day thursday = Day.builder().name("Thursday").shifts(week.getThursday()).build();
        Day friday = Day.builder().name("Friday").shifts(week.getFriday()).build();
        Day saturday = Day.builder().name("Saturday").shifts(week.getSaturday()).build();
        Day sunday = Day.builder().name("Sunday").shifts(week.getSunday()).build();
        daysInWeek.add(monday);
        daysInWeek.add(tuesday);
        daysInWeek.add(wednesday);
        daysInWeek.add(thursday);
        daysInWeek.add(friday);
        daysInWeek.add(saturday);
        daysInWeek.add(sunday);
        return daysInWeek;
    }

    private boolean isTheSame(ArrayList<Shift> shifts1, ArrayList<Shift> shifts2) {
        // if both are null they are the same
        if (shifts1 == null && shifts2 == null) return true;
        // if one of them is null and the other one is not, they are not the same
        if ((shifts1 == null && shifts2 != null) || (shifts1 != null && shifts2 == null)) return false;
        // if both of them are not null and they have the same numbers of shifts per day
        if (shifts1.size() != shifts2.size()) return false;
        if (shifts1 != null && shifts2 != null) {
            for (int i = 0; i < shifts1.size(); i++) {
                // if all shifts are the same conutinue and return true
                if (shifts1.get(i).getStart().equals(shifts2.get(i).getStart()) && shifts1.get(i).getEnd().equals(shifts2.get(i).getEnd()) && shifts1.get(i).getType().equals(shifts2.get(i).getType())) {
                    continue;
                } else {
                    // if shifts are not the same return false
                    return false;
                }
            }
        }
        return true;
    }


}
