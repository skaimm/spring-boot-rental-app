package com.rental.app.rental.Dto;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
public class ErrorDTO {

    public ErrorDTO(HttpStatus status, HttpServletRequest request, String message) {
        this.error = status.getReasonPhrase();
        this.status = status.value();
        this.timestamp = ZonedDateTime.now(ZoneOffset.UTC);
        this.message = message;
        if (StringUtils.isEmpty(request.getQueryString())) {
            this.path = request.getServletPath();
        } else {
            this.path = request.getServletPath() + "?" + request.getQueryString();
        }
    }

    private String error;

    private int status;

    private ZonedDateTime timestamp;

    private String path;

    private String message;
}
