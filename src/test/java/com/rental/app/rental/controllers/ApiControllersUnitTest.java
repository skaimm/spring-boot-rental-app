package com.rental.app.rental.controllers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.rental.app.rental.Dto.SearchParameters;
import com.rental.app.rental.components.RentalMapper;
import com.rental.app.rental.handlers.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class ApiControllersUnitTest {

    private static final Integer RENTAL_ID = 1;

    @Mock
    private RentalMapper rentalMapper;

    @InjectMocks
    private ApiControllers apiControllers;

    @Test
    void testSearch() {
        SearchParameters searchParameters = mock(SearchParameters.class);
        Pageable pageable = mock(Pageable.class);

        rentalMapper.search(searchParameters, pageable);

        verify(rentalMapper).search(searchParameters, pageable);
    }

    @Test
    void testFindById() throws NotFoundException {
        apiControllers.findById(RENTAL_ID);
        verify(rentalMapper).findById(RENTAL_ID);
    }
}

