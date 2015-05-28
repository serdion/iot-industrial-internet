/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.application;

import org.springframework.stereotype.Component;

/*
 * Defines some static settings
 */
@Component
public class ApplicationSettings {

    private final int maxObjectsRetrievedFromDatabase = 1000;

    private final int defaultAmountOfReadoutsRetrievedFromDatabase = 100;
    
    private int defaultAmountOfDevicesRetrievedFromDatabase = 10;
    
    private int defaultAmountOfDataSourcesRetrievedFromDatabase = 10;
    
    private int defautAmountOfErrorsRetrievedFromDatabase = 25;

    public int getMaxObjectsRetrievedFromDatabase() {
        return maxObjectsRetrievedFromDatabase;
    }

    public int getDefaultAmountOfReadoutsRetrievedFromDatabase() {
        return defaultAmountOfReadoutsRetrievedFromDatabase;
    }

    public int getDefaultAmountOfDevicesRetrievedFromDatabase() {
        return defaultAmountOfDevicesRetrievedFromDatabase;
    }

    public int getDefaultAmountOfDataSourcesRetrievedFromDatabase() {
        return defaultAmountOfDataSourcesRetrievedFromDatabase;
    }

    public int getDefautAmountOfErrorsRetrievedFromDatabase() {
        return defautAmountOfErrorsRetrievedFromDatabase;
    }
    
    

}
