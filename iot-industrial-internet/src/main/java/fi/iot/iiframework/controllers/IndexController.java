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

    @Secured({"ROLE_VIEWER", "ROLE_MODERATOR"})
    @RequestMapping("/")
    public String index(Model model) {
        return "ng";
    }

    @RequestMapping("/login")
    public String login(Model model, @RequestParam(required = false) Map<String, String> params) {
        model.addAttribute("haserror", params.containsKey("error"));
        return "login";
    }

}
