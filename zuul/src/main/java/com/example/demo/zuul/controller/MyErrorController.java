package com.example.demo.zuul.controller;

import java.net.SocketTimeoutException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.zuul.bean.ErrorResponse;

@Controller
public class MyErrorController implements ErrorController{

	@Value("${error.path:/error}")
    private String errorPath;
     
    @Override
    public String getErrorPath() {
        return errorPath;
    }
    
    @RequestMapping(value = "${error.path:/error}", produces = "application/json;charset=UTF-8")
    public @ResponseBody ResponseEntity<ErrorResponse> error(HttpServletRequest request)  {
        int status = getErrorStatus(request);
        String errorMessage = getErrorMessage(request);
    	ErrorResponse resp = new ErrorResponse();
		String error = "Internal Server Error";
		String exception = null;
    	try{
    		throw getRootException(request);
		} catch (SocketTimeoutException e) {
			status = HttpStatus.REQUEST_TIMEOUT.value();
			error = "Request Timeout";
			exception = e.getClass().getName();
		} catch (Throwable t) {
			exception = t.getClass().getName();
		}
    	resp.setError(error);
    	resp.setMessage(errorMessage);
    	resp.setStatus(status);
    	resp.setException(exception);
    	resp.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(status).body(resp);
    }
 
    private int getErrorStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
        return statusCode != null ? statusCode : HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
 
    private String getErrorMessage(HttpServletRequest request) {
        final Throwable exc = (Throwable) request.getAttribute("javax.servlet.error.exception");
        return exc != null ? exc.getMessage() : "Unexpected error occurred";
    }
    
	private Throwable getRootException(HttpServletRequest request) {
        Throwable rootException = (Throwable) request.getAttribute("javax.servlet.error.exception");
		while (rootException.getCause() != null) {
			rootException = rootException.getCause();
		}
		return rootException;
	}

}
