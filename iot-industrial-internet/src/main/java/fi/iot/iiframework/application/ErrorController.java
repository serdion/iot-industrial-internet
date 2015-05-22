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
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("error")
public class ErrorController {

    public String generalError(Model model, @RequestParam(required = false, defaultValue = "unknown") String type) {
        ViewParams params = new ViewParams("Hello error!", "Hei kaikki errorit maailmassa.");
        ViewUtils.addViewParamsToModel(model, params);

        return "default"; // TODO error view?
    }
}
