package com.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.app.model.Response;

public class BaseController {

	private static final Logger log = LoggerFactory.getLogger(BaseController.class);
    public Response failResponse() {
    	Response messageResponse =new Response();
    	messageResponse.setError("cannot be completed");
        return messageResponse;
    }
  
    public Response success(String solution) {
    	Response messageResponse =new Response();
    	messageResponse.setSolution(solution);
        return messageResponse;
    }
   }
