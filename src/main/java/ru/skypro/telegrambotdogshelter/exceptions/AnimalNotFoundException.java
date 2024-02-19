package ru.skypro.telegrambotdogshelter.exceptions;

public class AnimalNotFoundException extends RuntimeException{
    public AnimalNotFoundException(String message){super(message);}
}
