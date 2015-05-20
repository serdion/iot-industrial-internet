package fi.iot.iiframework.xmltodataobject;


import fi.iot.iiframework.dataobject.DataObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class XmlToObject {



    public static DataObject convertXml(String url) {
        DataObject data = null;
        try {
            JAXBContext context = JAXBContext.newInstance(DataObject.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            data = (DataObject) unmarshaller.unmarshal(new URL(url));
            
            

        } catch (JAXBException ex) {
            Logger.getLogger(XmlToObject.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(XmlToObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;

    }
}