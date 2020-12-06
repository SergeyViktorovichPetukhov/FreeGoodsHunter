package com.sergo.wic.utils;

import com.sergo.wic.dto.CoordinatesDto;
import com.sergo.wic.dto.ItemDto;

import java.util.ArrayList;
import java.util.List;

public class Constants {
    public static final String PHONE_REGEX = "^((d{0,3}|\\+d{0,3})[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
    public static final String WEB_SITE_VERIFICATION_MESSAGE = "";
    public static final String VERIFICATION_UNSUCCESS = "could not verify data from website";
    public static final List<ItemDto> BRYANSK_ITEMS = new ArrayList<>();
    public static final double ACCEPTABLE_RADIUS = 0.7;
    static {
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.307040, 34.298223),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.307055, 34.299081),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.307645, 34.299421),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.306554, 34.300853),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.306413, 34.301207),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.306096, 34.301137),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.305769, 34.301899),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.305352, 34.301658),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.304862, 34.301470),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.304060, 34.300962),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.303845, 34.300823),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.303540, 34.300584),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.303243, 34.300364),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.302938, 34.300002),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.302625, 34.299948),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.302354, 34.299642),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.301625, 34.298359),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.301413, 34.298938),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.301233, 34.299120),RandomString.getAlphaNumericString(6)));
        BRYANSK_ITEMS.add(new ItemDto(new CoordinatesDto(53.300566, 34.299388),RandomString.getAlphaNumericString(6)));
    }

}
