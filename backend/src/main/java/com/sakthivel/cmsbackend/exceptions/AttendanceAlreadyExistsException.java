package com.sakthivel.cmsbackend.exceptions;

public class AttendanceAlreadyExistsException extends RuntimeException {
    public AttendanceAlreadyExistsException(String message) {
        super(message);
    }
}