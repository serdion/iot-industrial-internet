/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.application;

import fi.iot.iiframework.views.ViewParams;
import fi.iot.iiframework.views.ViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelpController {
    
    @RequestMapping("/help")
    public String help(Model model) {
        ViewParams params = new ViewParams("Help", "need help?");
        params.setNavtype("loggedin");
        ViewUtils.addViewParamsToModel(model, params);

        return "default";
    }
}
