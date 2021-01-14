package com.fbmoll.teaching.dataaccess.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                //return "error-404";
                log.error("url error not found:" + statusCode);
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                //return "error-500";
                log.error("url error:" + statusCode);
            }
        }
        return "error";
    }

    @Override
    public String getErrorPath() {
        return null;
    }
}
