/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.application;

import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.services.DataSourceObjectService;
import fi.iot.iiframework.views.ViewParams;
import fi.iot.iiframework.views.ViewUtils;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("sources")
public class SourceController {
    
    @Autowired
    private DataSourceObjectService service;

    @RequestMapping("*")
    public String index(Model model) {
        return "redirect:/sources/example/view";
    }

    @RequestMapping("/{id}/view")
    public String view(Model model, @PathVariable String id) {
        ViewParams params = new ViewParams("List of all Sensors", "---");

        params.setNavtype("loggedin");
        params.setContenttype("view_source");

        ViewUtils.addViewParamsToModel(model, params);
        
        List<DataSourceObject> datasources = service.getAll();

        model.addAttribute("source", datasources.get(0));
        
        return "default";
    }
}
