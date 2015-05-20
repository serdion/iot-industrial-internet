/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ErrorController {
    
    @RequestMapping("/error")
    public String generalError(Model model, @RequestParam(required=false, defaultValue="unknown") String type) {
        
        
        return "/WEB-INF/views/error.jsp";
    }
}
