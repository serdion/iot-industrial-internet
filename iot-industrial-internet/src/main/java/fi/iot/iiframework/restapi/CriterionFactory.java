/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import fi.iot.iiframework.restapi.filters.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;

@Component
public class CriterionFactory {

    private HashMap<String, GeneralFilter> acceptedReadoutFilters;
    private HashMap<String, GeneralFilter> acceptedSysErrorFilters;

    public CriterionFactory() {
        acceptedReadoutFilters = new HashMap<>();
        acceptedSysErrorFilters = new HashMap<>();

        initAcceptedReadoutFilters();
        initAcceptedSysErrorFilters();
    }

    private void initAcceptedReadoutFilters() {
        acceptedReadoutFilters.put("unit", new Equals("unit"));
        acceptedReadoutFilters.put("quantity", new Equals("quantity"));

        acceptedReadoutFilters.put("more", new MoreThan("value"));
        acceptedReadoutFilters.put("less", new LessThan("value"));

        acceptedReadoutFilters.put("after", new After("time"));
        acceptedReadoutFilters.put("before", new Before("time"));
    }

    private void initAcceptedSysErrorFilters() {
        acceptedReadoutFilters.put("type", new EqualsErrorType("type"));

        acceptedReadoutFilters.put("after", new After("errordate"));
        acceptedReadoutFilters.put("before", new Before("errordate"));

        acceptedReadoutFilters.put("higher", new MoreThan("severity"));
        acceptedReadoutFilters.put("lower", new LessThan("severity"));
        acceptedReadoutFilters.put("severity", new Equals("severity"));
    }

    /**
     * Returns a matching list of Readout Criterion based on given params.
     *
     * @param params List of filters as parameters
     * @return List of Readout Criterion
     */
    public List<Criterion> getReadoutCriterion(Map<String, String> params) {
        return getCriterion(params, acceptedReadoutFilters);
    }

    /**
     * Returns a matching list of SysError Criterion based on given params.
     *
     * @param params List of filters as parameters
     * @return List of SysError Criterion
     */
    public List<Criterion> getSysErrorCriterion(Map<String, String> params) {
        return getCriterion(params, acceptedSysErrorFilters);
    }

    private List<Criterion> getCriterion(Map<String, String> params, HashMap<String, GeneralFilter> filters) {
        ArrayList<Criterion> crits = new ArrayList<>();

        for (Map.Entry<String, String> entrySet : params.entrySet()) {
            String name = entrySet.getKey();
            String value = entrySet.getValue();

            try {
                GeneralFilter filter = acceptedReadoutFilters.get(name);
                Criterion criterion = filter.createCriterion(value);

                if (criterion != null) {
                    crits.add(criterion);
                }

            } catch (ArrayIndexOutOfBoundsException exp) {
                ErrorLogger.log(ErrorType.NOT_ACCEPTED, ErrorSeverity.MEDIUM, "Wrong amount of parameters while trying to add a filter.");
            } catch (NullPointerException exp) {
                // No filters
            }
        }

        return crits;
    }

}
