/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.domain;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.Sensor;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author atte
 */
public class InformationSourceObjectProvider {
    
    public static List<Sensor> provideSensorsWithChildren(InformationSource parent) {
        List<Sensor> sensors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Sensor s = provideSensor();
            s.setSource(parent);
            for (int j = 0; j < 3; j++) {
                s.getReadouts().add(provideReadout(s));
            }
            sensors.add(s);
        }

        return sensors;
    }

    public static List<Sensor> provideSensorsWithChildren() {
        List<Sensor> sensors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Sensor s = provideSensor();
            for (int j = 0; j < 3; j++) {
                s.getReadouts().add(provideReadout(s));
            }
            sensors.add(s);
        }

        return sensors;
    }

    public static Sensor provideSensor() {
        Sensor sensor = new Sensor();
        sensor.setName(getUUID());
        sensor.setReadouts(new HashSet<>());
        sensor.setQuantity("Temperature");
        sensor.setUnit("°C");

        return sensor;
    }

    private static Readout provideReadout(Sensor s) {
        Readout r = provideReadout();
        r.setSensor(s);
        return r;
    }
    
    static int index = 0;

    public static Readout provideReadout() {
        Readout readout = new Readout();
        readout.setTime(System.currentTimeMillis() + index++);

        // This might fix the problems with different locales 
        // expecting decimal separator to be comma or period
        
        NumberFormat nformat = NumberFormat.getNumberInstance(Locale.ENGLISH);
        DecimalFormat df = (DecimalFormat) nformat;
        
        String format = df.format(randDouble(22.1));

        readout.setValue(Double.parseDouble(format));

        return readout;
    }

    private static double randDouble(double min) {
        Random random = new Random();
        return min + random.nextDouble();
    }

    private static int randInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) + min;
    }

    private static String getUUID() {
        return UUID.randomUUID().toString();
    }

}
