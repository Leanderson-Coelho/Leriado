package com.ifpb.edu.controller.exception;

public class CommandException extends Exception{
	private int statusCode;
	
	public CommandException(int statusCode, String msg) {
		super(msg);
		this.statusCode = statusCode;
	}
}
