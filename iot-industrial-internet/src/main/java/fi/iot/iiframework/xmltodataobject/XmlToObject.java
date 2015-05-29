package fi.iot.iiframework.xmltodataobject;

import fi.iot.iiframework.dataobject.DataSourceObject;
import fi.iot.iiframework.dataobject.Device;
import fi.iot.iiframework.dataobject.Readout;
import fi.iot.iiframework.dataobject.Sensor;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlToObject {

    /**
     * Converts XML representation of DataObject to actual object.
     *
     * @param url URL to load the XML from
     * @return DataObject based on the XML
     * @throws javax.xml.bind.JAXBException
     * @throws java.net.MalformedURLException
     */
    public static DataSourceObject convertXml(String url) throws JAXBException, MalformedURLException, IOException {
        DataSourceObject dso = null;
        JAXBContext jaxbc = JAXBContext.newInstance(DataSourceObject.class, Device.class, Sensor.class, Readout.class);
        Unmarshaller marshaller = jaxbc.createUnmarshaller();
        InputStream input = new URL(url).openStream();
        dso = (DataSourceObject) marshaller.unmarshal(input);

        return dso;
    }
}
