/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.services.domain;

import fi.iot.iiframework.domain.InformationSourceObject;
import fi.iot.iiframework.domain.Device;
import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.Sensor;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author atte
 */
public class InformationSourceObjectProvider {

    public static InformationSourceObject provideInformationSourceObjectWithChildren() {
        InformationSourceObject o = provideInformationSourceObject();
        for (int i = 0; i < 3; i++) {
            o.getDevices().add(proviceDeviceWithChildren(o));
        }
        return o;
    }

    public static InformationSourceObject provideInformationSourceObject() {
        java.util.Locale.setDefault(Locale.ENGLISH);
        InformationSourceObject obj = new InformationSourceObject();

        obj.setId(getUUID());
        obj.setDevices(new HashSet<>());

        return obj;
    }

    private static Device proviceDeviceWithChildren(InformationSourceObject parent) {
        Device d = provideDevice();
        d.setSource(parent);
        for (int i = 0; i < 3; i++) {
            d.getSensors().add(provideSensorWithChildren(d));
        }
        return d;
    }

    private static Sensor provideSensorWithChildren(Device parent) {
        Sensor s = provideSensor();
        s.setDevice(parent);
        for (int i = 0; i < 3; i++) {
            s.getReadouts().add(provideReadout(s));
        }
        return s;
    }

    public static Device provideDevice() {
        Device device = new Device();

        device.setDeviceId(getUUID());
        device.setStatus(true);
        device.setSensors(new HashSet<>());

        return device;
    }

    public static Sensor provideSensor() {
        Sensor sensor = new Sensor();
        sensor.setSensorId(getUUID());
        sensor.setReadouts(new HashSet<>());

        return sensor;
    }
    
    private static Readout provideReadout(Sensor s) {
        Readout r = provideReadout();
        r.setSensor(s);
        return r;
    }

    public static Readout provideReadout() {
        Readout readout = new Readout();
        readout.setTime(System.currentTimeMillis() + randInt(-100, 100));
        readout.setQuantity("Temperature");
        readout.setUnit("°C"); // Celsius

        DecimalFormat df = new DecimalFormat("#.00");
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
