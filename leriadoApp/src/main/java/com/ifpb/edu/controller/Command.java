package com.ifpb.edu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ifpb.edu.controller.exception.CommandException;

public interface Command {
	public void execute(HttpServletRequest request,HttpServletResponse response) throws CommandException;
}
