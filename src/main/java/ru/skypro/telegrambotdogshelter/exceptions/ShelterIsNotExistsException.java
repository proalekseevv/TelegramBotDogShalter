package ru.skypro.telegrambotdogshelter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShelterIsNotExistsException extends RuntimeException{

}
