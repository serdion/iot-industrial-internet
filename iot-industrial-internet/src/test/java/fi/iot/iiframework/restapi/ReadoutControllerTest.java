/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.restapi.filters.CriterionFactory;
import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.services.domain.InformationSourceObjectProvider;
import fi.iot.iiframework.services.domain.ReadoutService;
import fi.iot.iiframework.services.domain.SensorService;
import java.util.ArrayList;
import java.util.HashSet;
import org.hibernate.criterion.Criterion;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@Ignore
public class ReadoutControllerTest {
    
    private InformationSource sourceA;
    private InformationSource sourceB;

    private Sensor sensorA;
    private Sensor sensorB;

    private Readout readoutA;
    private Readout readoutB;
    private Readout readoutC;

    private ReadoutController controller;
    
    @Mock
    private SensorService sensorservice;

    @Mock
    private ReadoutService readoutservice;
    
    @Mock
    private CriterionFactory criterionfactory;
    
    @Mock
    private RestAPIHelper helper;

    @Before
    public void setUp() throws ResourceNotFoundException {
        controller = new ReadoutController();
        MockitoAnnotations.initMocks(this);
        
        initLogin();
        initContext();
        
        controller.setReadoutservice(readoutservice);
        controller.setSensorservice(sensorservice);
        
        when(sensorservice.get(Matchers.eq(sensorA.getId()))).thenReturn(sensorA);
        when(sensorservice.get(Matchers.eq(sensorB.getId()))).thenReturn(sensorB);
        
        when(helper.returnOrException(Matchers.eq(sensorA))).thenReturn(sensorA);
        when(helper.returnOrException(Matchers.eq(sensorB))).thenReturn(sensorB);
        
        ArrayList<Readout> readouts = new ArrayList<>();
        readouts.add(readoutA);
        readouts.add(readoutB);
        
        when(readoutservice.getBy(Matchers.eq(sensorA))).thenReturn(readouts);
        when(readoutservice.getBy(Matchers.eq(sensorB))).thenReturn(readouts); // same <.<
        
        when(criterionfactory.getReadoutCriterion(Matchers.any())).thenReturn(new ArrayList<Criterion>());
        
    }
    
    private void initContext(){
        sourceA = new InformationSource();
        sourceA.setId(1l);
        sourceA.setSensors(new HashSet<>());
        
        sourceB = new InformationSource();
        sourceB.setId(2l);
        sourceB.setSensors(new HashSet<>());

        sensorA = InformationSourceObjectProvider.provideSensor();
        sensorB = InformationSourceObjectProvider.provideSensor();
        sensorA.setSource(sourceA);
        sensorB.setSource(sourceB);

        readoutA = InformationSourceObjectProvider.provideReadout();
        readoutB = InformationSourceObjectProvider.provideReadout();
        readoutC = InformationSourceObjectProvider.provideReadout();

        readoutA.setValue(21.0);
        readoutB.setValue(22.0);
        readoutC.setValue(23.0);

        sensorA.getReadouts().add(readoutA);
        sensorA.getReadouts().add(readoutB);
        sensorB.getReadouts().add(readoutC);

        sourceA.getSensors().add(sensorA);
        sourceB.getSensors().add(sensorB);
        
        readoutA.setSensor(sensorA);
        readoutB.setSensor(sensorA);
        readoutC.setSensor(sensorB);

        // Don't save this sourceService.save(sourceB);
    }
    
    /*
    Login as a moderator, broken if the in-memory logins are changed!
    */
    private void initLogin(){
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("moderator", "moderator")
        );
    }

    @Test
    public void testListReadoutsList() throws Exception {

    }

    @Test
    public void testListReadoutsAmount() throws Exception {

    }

    @Test
    public void testListReadoutsFromTo() throws Exception {

    }

    @Test
    public void testGetReadout() {

    }

}
