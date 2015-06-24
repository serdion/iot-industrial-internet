/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.controllers;

import java.util.Map;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    /**
     * Index controller for this application.
     *
     * @param model Model that is passed to the view
     * @return File: ng.html
     */
    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping("/")
    public String index(Model model) {
        return "ng";
    }

    /**
     * Login controller for this application.
     *
     * @param model Model that is passed to the view
     * @param params Parameters found in the URL of the page (marked with
     * question mark)
     * @return File: login.html
     */
    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("haserror", params.containsKey("error"));
        return "login";
    }

}
