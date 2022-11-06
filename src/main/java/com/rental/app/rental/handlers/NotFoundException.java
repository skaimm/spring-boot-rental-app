package com.rental.app.rental.handlers;

public class NotFoundException extends Exception {

    public NotFoundException(Integer id) {
        super(String.format("Rental with id %d was not found!", id));
    }
}