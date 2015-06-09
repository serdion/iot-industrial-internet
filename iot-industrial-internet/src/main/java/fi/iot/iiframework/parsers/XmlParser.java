package fi.iot.iiframework.parsers;

import fi.iot.iiframework.domain.InformationSourceObject;
import fi.iot.iiframework.domain.Device;
import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.springframework.stereotype.Component;

@Component
public class XmlParser implements Parser {

    @Override
    public InformationSourceObject parse(String location) {
        InformationSourceObject informationSourceObject = null;
        
        try {
            JAXBContext jaxbc = JAXBContext.newInstance(InformationSourceObject.class, Device.class, Sensor.class, Readout.class);
            Unmarshaller marshaller = jaxbc.createUnmarshaller();
            InputStream input = new URL(location).openStream();
            informationSourceObject = (InformationSourceObject) marshaller.unmarshal(input);
        } catch (MalformedURLException ex) {
            ErrorLogger.log(ErrorType.BAD_REQUEST, ErrorSeverity.MEDIUM, "Tried to parse XML to Object but URI given could not be formed correctly");
        } catch (JAXBException ex) {
            ErrorLogger.log(ErrorType.PARSE_ERROR, ErrorSeverity.MEDIUM, "Tried to parse XML to Object but failed due to an error: "+ ex.toString());
        } catch (IOException ex) {
            ErrorLogger.log(ErrorType.IO_ERROR, ErrorSeverity.MEDIUM, "Tried to parse XML to Object but failed due to an error: "+ ex.toString());
        } 

        return informationSourceObject;
    }
}
