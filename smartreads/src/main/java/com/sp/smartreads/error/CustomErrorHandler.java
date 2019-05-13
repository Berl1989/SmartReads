package com.sp.smartreads.error;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CustomErrorHandler implements ErrorController {

  @RequestMapping("/error")
  @ResponseBody
  public ResponseEntity<Object> handleError(HttpServletRequest request) {
      String errorMessage = "Bad request. Incorrect resource URI";
      ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, errorMessage);
      return new ResponseEntity<>(apiError, apiError.getStatus());
  }

  @Override
  public String getErrorPath() {
      return "/error";
  }
}