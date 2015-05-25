/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.application;

import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.views.ViewParams;
import fi.iot.iiframework.views.ViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("errors")
public class ErrorController {

    public String generalError(Model model, @RequestParam(required = false, defaultValue = "unknown") String type) {
        
        ErrorType errorType = ErrorType.getType(type);
        
        ViewParams params = new ViewParams("Error occured!", "Hei kaikki errorit maailmassa.");
        ViewUtils.addViewParamsToModel(model, params);

        return "default";
    }
    
    @RequestMapping("/list")
    public String listErrors(Model model){
        ViewParams params = new ViewParams("Errors listed", "Listana");
        ViewUtils.addViewParamsToModel(model, params);

        return "default";
    }
}
