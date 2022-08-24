package com.example.contactsproject.controller.exceptions;

public class FileEmptyException extends Exception{

    public FileEmptyException(String errorMessage) {
        super(errorMessage);
    }

}