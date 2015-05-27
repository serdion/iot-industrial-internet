    /*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.controllers;

import fi.iot.iiframework.views.ViewParams;
import fi.iot.iiframework.views.ViewUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/visual")
public class VisualizationController {
    
    /*
     * Redirect to the realtime view.
     */
    @RequestMapping("*")
    public String index(Model model) {
        return "redirect:/visual/now";
    }

    @RequestMapping("/now")
    public String now(Model model) {
        ViewParams params = new ViewParams("Visualization", "Visualization");
        params.setContenttype("visual_realtime");
        params.setNavtype("loggedin");
        params.setFootertype("flot");
        ViewUtils.addViewParamsToModel(model, params);

        return "default";
    }
    
    @RequestMapping("/history")
    public String history(Model model) {
        ViewParams params = new ViewParams("Visualization", "Visualization");
        params.setContenttype("visual_history");
        params.setNavtype("loggedin");
        params.setFootertype("morris");
        ViewUtils.addViewParamsToModel(model, params);

        return "default";
    }
}
