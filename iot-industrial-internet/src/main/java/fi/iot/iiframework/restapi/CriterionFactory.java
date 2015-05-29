/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.restapi.filters.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;

@Component
public class CriterionFactory {
    
    private HashMap<String, ReadoutFilter> acceptedReadoutFilters;

    public CriterionFactory() {
        acceptedReadoutFilters = new HashMap<>();
        initAcceptedReadoutFilters();
    }
    
    public void initAcceptedReadoutFilters(){
        acceptedReadoutFilters.put("unit", new Equals("unit"));
        acceptedReadoutFilters.put("quantity", new Equals("quantity"));
        acceptedReadoutFilters.put("value", new Equals("value"));
        
        acceptedReadoutFilters.put("moretthan", new MoreThan("value"));
        acceptedReadoutFilters.put("lessthan", new LessThan("value"));
        
        acceptedReadoutFilters.put("after", new After("time"));
        acceptedReadoutFilters.put("before", new Before("time"));
    }

    public List<Criterion> getReadoutCriterion(Map<String, String> params) {
        ArrayList<Criterion> crits = new ArrayList<>();
        
        for(Map.Entry<String, String> entrySet : params.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            
            ReadoutFilter readoutFilter = acceptedReadoutFilters.get(key);
            crits.add(readoutFilter.createCriterion(value));
        }

        return crits;
    }

}
