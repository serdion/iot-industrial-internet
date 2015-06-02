/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.controllers;

import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.SysError;
import fi.iot.iiframework.views.ViewParams;
import fi.iot.iiframework.views.ViewUtils;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("syserrors")
public class ErrorController {

    @RequestMapping("*")
    public String index(Model model) {
        return "redirect:/syserrors/view";
    }

    @RequestMapping("/view")
    public String list(Model model) {
        ViewParams params = new ViewParams("ttt", "ttt");
        // just testing "" ""

        StringBuilder print = new StringBuilder("Here comes the errors!");
        params.setPagetitle("List of errors");
        params.setContent("print");

        List<SysError> errors = ErrorLogger.getAllErrors();
        for (SysError error : errors) {
            print.append("\n" + error.getType().getName() + " "
                    + error.getSeverity() + " " + error.getDescription() + " " + error.getTime());

        }
        model.addAllAttributes(errors);
        

        params.setContent(print.toString());

        ViewUtils.addViewParamsToModel(model, params);
        params.setNavtype("loggedin");
        return "default";
    }

}
