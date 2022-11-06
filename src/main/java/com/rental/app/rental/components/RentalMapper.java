package com.rental.app.rental.components;
import com.rental.app.rental.Dto.RentalDTO;
import com.rental.app.rental.Dto.SearchParameters;
import com.rental.app.rental.handlers.NotFoundException;
import com.rental.app.rental.service.RentalService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class RentalMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private RentalService rentalService;

    public Page<RentalDTO> search(SearchParameters searchParams, Pageable pageable) {
        return rentalService
                .search(searchParams, pageable)
                .map(rental -> modelMapper.map(rental, RentalDTO.class));
    }

    public RentalDTO findById(Integer id) throws NotFoundException {
        return modelMapper.map(rentalService.findById(id), RentalDTO.class);
    }
}
