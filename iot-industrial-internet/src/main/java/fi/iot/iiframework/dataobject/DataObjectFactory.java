/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.dataobject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author ooppa
 */
public class DataObjectFactory {

    /**
     * Returns a randomly generated DataObject
     *
     * @return DataObject
     */
    public static DataSourceObject getRandomDataObject() {
        java.util.Locale.setDefault(Locale.ENGLISH);
        DataSourceObject obj = new DataSourceObject();

        obj.setId(UUID.randomUUID().toString());
        obj.setHeader(getHeader());
        obj.setDevices(new ArrayList<Device>());

        for (int i = 0; i < 10; i++) {
            obj.getDevices().add(getDevice());
        }

        return obj;
    }

    private static Header getHeader() {
        Header header = new Header();

        header.setResponse("success");
        header.setUptime(randInt(1000, 10000));

        return header;
    }

    private static Device getDevice() {
        Device device = new Device();

        device.setId(uuid());
        device.setStatus(true);
        device.setSensors(new ArrayList<Sensor>());

        for (int i = 0; i < 10; i++) {
            device.getSensors().add(getSensor());
        }

        return device;
    }

    private static Sensor getSensor() {
        Sensor sensor = new Sensor();
        sensor.setId(uuid());
        sensor.setReadouts(new ArrayList<Readout>());

        long currtime = System.currentTimeMillis();

        for (int i = 0; i < 25; i++) {
            sensor.getReadouts().add(getReadout(currtime - i));
        }

        return sensor;
    }

    private static Readout getReadout(long currentTime) {
        Readout readout = new Readout();
        readout.setTime("" + (currentTime - 1));
        readout.setQuantity("Temperature");
        readout.setUnit("C");

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

    private static String uuid() {
        return UUID.randomUUID().toString();
    }
}
