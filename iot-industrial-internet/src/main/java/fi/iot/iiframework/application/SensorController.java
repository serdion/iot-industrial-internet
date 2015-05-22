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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sensors")
public class SensorController {

    @RequestMapping("*")
    public String index(Model model) {
        return "redirect:/sensors/list";
    }

    @RequestMapping("/list")
    public String list(Model model) {
        ViewParams params = new ViewParams("List of all Sensors", "---");
        ViewUtils.addViewParamsToModel(model, params);
        params.setNavtype("loggedin");
        return "default";
    }

    @RequestMapping("/{id}/view/")
    public String view(Model model, @PathVariable String id) {
        ViewParams params = new ViewParams("View Sensor with id "+id, "---");
        ViewUtils.addViewParamsToModel(model, params);
        params.setNavtype("loggedin");
        return "default";
    }

    @RequestMapping("/{id}/edit")
    public String edit(Model model, @PathVariable String id) {
        ViewParams params = new ViewParams("Edit Sensor with id "+id, "---");
        ViewUtils.addViewParamsToModel(model, params);
        params.setNavtype("loggedin");
        return "default";
    }
}
