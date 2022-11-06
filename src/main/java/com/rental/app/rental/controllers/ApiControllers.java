package com.rental.app.rental.controllers;

import com.rental.app.rental.Dto.RentalDTO;
import com.rental.app.rental.Dto.SearchParameters;
import com.rental.app.rental.handlers.NotFoundException;
import com.rental.app.rental.components.RentalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/rvs", produces = APPLICATION_JSON_VALUE)
public class ApiControllers {

    /*- `rvs`
    - `rvs?price[min]=9000&price[max]=75000`
    - `rvs?page[limit]=3&page[offset]=6`
    - `rvs?ids=2000,51155,54318`
    - `rvs?near=33.64,-117.93` // within 100 miles
    - `rvs?sort=price`
    - `rvs/<RV_ID>`*/

    @Autowired
    private RentalMapper rentalMapper;

    @GetMapping
    @ResponseBody
    public Page<RentalDTO> search(SearchParameters searchParameters, Pageable pageable) {
        return rentalMapper.search(searchParameters, pageable);
    }

    @GetMapping(path = "/{id}")
    @ResponseBody
    public RentalDTO findById(@PathVariable Integer id) throws NotFoundException {
        return rentalMapper.findById(id);
    }
}
