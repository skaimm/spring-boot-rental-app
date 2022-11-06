package com.rental.app.rental.repositories;


import com.rental.app.rental.entities.Rental;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    //Using the haversine formula to find the great circle distance between two points
    //3959 is the radius of the Earth in miles
    @Query(value =
            "SELECT * "
                    + "FROM "
                    + "("
                    + "   SELECT *, (3959 * acos(cos(radians(:lat)) * cos(radians(lat)) * cos(radians(lng) - radians(:lng)) + sin(radians(:lat)) * sin( radians(lat)))) AS distance "
                    + "   FROM rentals"
                    + ") r "
                    + "WHERE distance < :distance \n-- #pageable\n",
            countQuery = "SELECT count(*) "
                    + "FROM "
                    + "("
                    + "   SELECT *, (3959 * acos(cos(radians(:lat)) * cos(radians(lat)) * cos(radians(lng) - radians(:lng)) + sin(radians(:lat)) * sin( radians(lat)))) AS distance "
                    + "   FROM rentals"
                    + ") r "
                    + "WHERE distance < :distance",
            nativeQuery = true)
    Page<Rental> findNearRentals(int distance, double lat, double lng, Pageable pageable);

    Page<Rental> findAllByPricePerDayIsBetween(Long min, Long max, Pageable pageable);

    Page<Rental> findAllByIdIn(List<Integer> ids, Pageable pageable);

    Page<Rental> findAll(Pageable pageable);
}