package com.rental.app.rental.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.rental.app.rental.Dto.SearchParameters;
import com.rental.app.rental.entities.Rental;
import com.rental.app.rental.handlers.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;

import com.rental.app.rental.repositories.RentalRepository;

@ExtendWith(MockitoExtension.class)
class RentalServiceUnitTest {

    private static final Integer EXISTING_RENTAL_ID = 1;
    private static final Integer NON_EXISTING_RENTAL_ID = 1;

    private final Integer nearDistance = 100;

    @Mock
    private RentalRepository rentalRepository;

    @Mock
    private Pageable pageable;

    @InjectMocks
    private RentalService rentalService;

    @BeforeEach
    public void setup() {
        ReflectionTestUtils.setField(rentalService, "nearDistance", nearDistance);
    }

    @Test
    void testSearchByPrice() {
        Long minPrice = 9000L;
        Long maxPrice = 75000L;
        SearchParameters searchParameters = new SearchParameters();
        HashMap<String, Long> prices = new HashMap<>();
        prices.put("min", 9000L);
        prices.put("max", 75000L);
        searchParameters.setPrice(prices);

        rentalService.search(searchParameters, pageable);

        verify(rentalRepository).findAllByPricePerDayIsBetween(minPrice, maxPrice, pageable);
    }

    @Test
    void testSearchByIds() {
        List<Integer> ids = mock(List.class);
        SearchParameters searchParameters = new SearchParameters();
        searchParameters.setIds(ids);

        rentalService.search(searchParameters, pageable);

        verify(rentalRepository).findAllByIdIn(ids, pageable);
    }

    @Test
    void testSearchByNear() {
        double lat = 33.64;
        double lng = -117.93;
        String nearRequestParam = lat + "," + lng;
        SearchParameters SearchParameters = new SearchParameters();
        SearchParameters.setNear(nearRequestParam);

        rentalService.search(SearchParameters, pageable);

        verify(rentalRepository).findNearRentals(nearDistance, lat, lng, pageable);
    }

    @Test
    void testSearchWithoutRequestParams() {
        SearchParameters searchParameters = new SearchParameters();
        rentalService.search(searchParameters, pageable);

        verify(rentalRepository).findAll(pageable);
    }

    @Test
    void testFindByExistingId() throws NotFoundException {
        Rental expected = mock(Rental.class);
        when(rentalRepository.findById(EXISTING_RENTAL_ID)).thenReturn(Optional.of(expected));

        Rental actual = rentalService.findById(EXISTING_RENTAL_ID);

        assertEquals(expected, actual);
    }

    @Test
    void testFindByNonExistingId() {
        when(rentalRepository.findById(NON_EXISTING_RENTAL_ID)).thenReturn(Optional.empty());

        Assertions.assertThrows(NotFoundException.class, () -> rentalService.findById(NON_EXISTING_RENTAL_ID));
    }
}
