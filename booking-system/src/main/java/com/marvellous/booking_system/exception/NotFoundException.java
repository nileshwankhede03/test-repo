package com.marvellous.booking_system.exception;

public class NotFoundException extends RuntimeException 
{
    public NotFoundException(String msg) 
    { 
    	super(msg); 
    }
}
