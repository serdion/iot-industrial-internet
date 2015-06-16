/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@RestController
@RequestMapping("1.0/stats")
public class StatController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @RequestMapping(value = "/paths", produces = "application/json")
    @ResponseBody
    public Object[] index() {
        Set<String> links = new HashSet<>();

        requestMappingHandlerMapping.getHandlerMethods().entrySet().stream().forEach((entrySet) -> {
            links.addAll(entrySet.getKey().getPatternsCondition().getPatterns());
        });

        return links.stream().filter((p) -> p.contains("1.0")).toArray();
    }
}
