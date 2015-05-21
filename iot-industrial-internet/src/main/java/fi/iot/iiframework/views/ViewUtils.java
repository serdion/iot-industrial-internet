/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.views;

import org.springframework.ui.Model;

/**
 *
 * @author ooppa
 */
public class ViewUtils {
    
    public static void addToModelFromViewParams(Model model, ViewParams params){
        model.addAttribute("viewparams", params);
    }
    
}
