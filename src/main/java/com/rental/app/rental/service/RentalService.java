package com.rental.app.rental.service;

import com.rental.app.rental.components.OffsetBasedPageRequest;
import com.rental.app.rental.Dto.SearchParameters;
import com.rental.app.rental.handlers.NotFoundException;
import com.rental.app.rental.entities.Rental;
import com.rental.app.rental.repositories.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;


@Service
public class RentalService {

    @Value("${rental.near.distance:100}")
    private Integer nearDistance;

    @Autowired
    private RentalRepository rentalRepository;

    public Rental findById(Integer id) throws NotFoundException {
        return rentalRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Page<Rental> search(SearchParameters searchParameters, Pageable pageable) {
        if (searchParameters.getPrice() != null && !searchParameters.getPrice().isEmpty()) {
            return rentalRepository
                    .findAllByPricePerDayIsBetween(searchParameters.getPrice().get("min"), searchParameters.getPrice().get("max"), pageable);
        } else if (!CollectionUtils.isEmpty(searchParameters.getIds())) {
            return rentalRepository.findAllByIdIn(searchParameters.getIds(), pageable);
        } else if (isNotEmpty(searchParameters.getNear())) {
            String[] coordinates = searchParameters.getNear().split(",");
            return rentalRepository.findNearRentals(nearDistance, Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]), pageable);
        } else if (searchParameters.getSort() != null && searchParameters.getSort().equals("price")) {
            Pageable sortedPageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("pricePerDay"));
            return rentalRepository.findAll(sortedPageable);
        } else if (searchParameters.getPage() != null && !searchParameters.getPage().isEmpty()) {
            Pageable offsetLimitPageable = new OffsetBasedPageRequest(searchParameters.getPage().get("offset"), searchParameters.getPage().get("offset"));
            return rentalRepository.findAll(offsetLimitPageable);
        }

        return rentalRepository.findAll(pageable);
    }
}