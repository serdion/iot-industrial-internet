/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.views;

import org.springframework.ui.Model;

/**
 * Utilities for managing the views in the system.
 */
public class ViewUtils {

    /**
     * Adds view parameters to the given model
     *
     * @param model Model in which the parameters should be added
     * @param params Parameters to add
     */
    public static void addViewParamsToModel(Model model, ViewParams params) {
        model.addAttribute("viewparams", params);
    }

}
