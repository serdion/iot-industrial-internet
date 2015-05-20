/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.iot.iitframework.dataobject;

import java.io.StringWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 *
 * @author ooppa
 */
public class DataObjectFactory {

    /**
     * Returns a randomly generated DataObject
     * @return DataObject
     */
    public static DataObject getRandomDataObject() {
        DataObject obj = new DataObject();

        obj.setHeader(getHeader());
        obj.setDevices(new ArrayList<Device>());
        
        for (int i = 0; i < 10; i++) {
            obj.getDevices().add(getDevice());
        }

        return obj;
    }
    
    private static Header getHeader(){
        Header header = new Header();

        header.setResponse("success");
        header.setUptime(randInt(1000, 10000));
        
        return header;
    }
    
    private static Device getDevice(){
        Device device = new Device();

        device.setId(uuid());
        device.setStatus(true);
        device.setSensors(new ArrayList<Sensor>());
        
        for (int i = 0; i < 10; i++) {
            device.getSensors().add(getSensor());
        }
        
        return device;
    }
    
    private static Sensor getSensor(){
        Sensor sensor = new Sensor();
        sensor.setId(uuid());
        sensor.setReadouts(new ArrayList<Readout>());
        
        long currtime = System.currentTimeMillis();
        
        for (int i = 0; i < 25; i++) {
            sensor.getReadouts().add(getReadout(currtime-i));
        }
        
        return sensor;
    }
    
    private static Readout getReadout(long currentTime){
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
