package com.rental.app.rental.components;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rental.app.rental.Dto.RentalDTO;
import com.rental.app.rental.Dto.SearchParameters;
import com.rental.app.rental.entities.Rental;
import com.rental.app.rental.handlers.NotFoundException;
import com.rental.app.rental.service.RentalService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;


@ExtendWith(MockitoExtension.class)
class RentalMapperUnitTest {

    private static final Integer RENTAL_ID = 1;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private RentalService rentalService;

    @InjectMocks
    private RentalMapper rentalMapper;

    @Test
    void testSearch() {
        SearchParameters searchParameters = mock(SearchParameters.class);
        Pageable pageable = mock(Pageable.class);
        Rental returnedRental = mock(Rental.class);
        Page<Rental> searchResult = new PageImpl<>(Lists.newArrayList(returnedRental));
        when(rentalService.search(searchParameters, pageable)).thenReturn(searchResult);

        rentalMapper.search(searchParameters, pageable);

        verify(modelMapper).map(returnedRental, RentalDTO.class);
    }

    @Test
    void testFindById() throws NotFoundException {
        Rental returnedRental = mock(Rental.class);
        when(rentalService.findById(RENTAL_ID)).thenReturn(returnedRental);

        rentalMapper.findById(RENTAL_ID);

        verify(modelMapper).map(returnedRental, RentalDTO.class);
    }
}