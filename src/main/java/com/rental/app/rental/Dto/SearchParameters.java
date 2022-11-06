package com.rental.app.rental.Dto;


import java.util.HashMap;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;


@Data
@NoArgsConstructor
public class SearchParameters {

    @Pattern(regexp = "^(-?\\d+(\\.\\d+)?),(-?\\d+(\\.\\d+)?)$",
            message = "Coordinate format must be like : 33.64,-117.93")
    private String near;

    private List<Integer> ids;

    private HashMap<String, Long> price;

    private HashMap<String, Integer> page;

}
