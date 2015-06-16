/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.restapi.exceptions.InvalidObjectException;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.services.domain.InformationSourceObjectProvider;
import fi.iot.iiframework.services.domain.InformationSourceService;
import fi.iot.iiframework.source.InformationSourceManagerImpl;
import java.util.HashSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class InformationSourceControllerTest {

    private InformationSourceController controller;
    
    private InformationSource sourceA;
    private InformationSource sourceB;

    private Sensor sensorA;
    private Sensor sensorB;

    private Readout readoutA;
    private Readout readoutB;
    private Readout readoutC;
    
    @Mock
    private InformationSourceService sourceService;
    
    @Mock
    private InformationSourceManagerImpl sourceManager;
    
    @Mock
    private RestAPIHelper helper;
    
    @Before
    public void setUp() throws ResourceNotFoundException {
        controller = new InformationSourceController();
        MockitoAnnotations.initMocks(this);
        
        initLogin();
        initContext();
        
        controller.setInformationSourceService(sourceService);
        controller.setInformationSourceManager(sourceManager);
        controller.setRestAPIHelper(helper);

        
        when(helper.returnOrException(Matchers.eq(sourceA))).thenReturn(sourceA);
        when(helper.returnOrException(Matchers.eq(sourceB))).thenReturn(sourceB);
        
        when(sourceService.get(Matchers.eq(sourceA.getId()))).thenReturn(sourceA);
        when(sourceService.get(Matchers.eq(sourceB.getId()))).thenReturn(sourceB);
    }
    
    /*
    Initiates the context in which the tests are run in.
    */
    private void initContext(){
        sourceA = new InformationSource();
        sourceA.setId("sourceA");
        sourceA.setSensors(new HashSet<>());
        
        sourceB = new InformationSource();
        sourceB.setId("sourceB");
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

        sourceService.save(sourceA);
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
    
    @After
    public void tearDown() {
    }

    @Test
    public void testGetInformationSource() throws InvalidParametersException, ResourceNotFoundException  {
        InformationSource foundSource = controller.getInformationSource("sourceA", null);
        assertEquals(foundSource, sourceA);
    }

    @Test
    public void testAddInformationSource() throws InvalidParametersException, ResourceNotFoundException, InvalidObjectException  {
        controller.addInformationSource(sourceB, null);
        InformationSource foundSource = controller.getInformationSource("sourceB", null);
        assertEquals(foundSource, sourceB);
    }

    @Test
    public void testEditInformationSource() throws InvalidParametersException, ResourceNotFoundException, InvalidObjectException  {
        sourceA.setReadFrequency(5555555);
        controller.editInformationSource(sourceA, null);
        
        Mockito.verify(sourceManager, Mockito.times(1)).updateSource(sourceA);
    }

    @Test
    public void testDeleteInformationSource() throws InvalidParametersException, ResourceNotFoundException  {
        controller.deleteInformationSource("sourceA", null);
        Mockito.verify(sourceManager, Mockito.times(1)).removeSource("sourceA");
    }

    @Test
    public void testListInformationSourcesList() throws InvalidParametersException  {
        assertFalse(controller.listInformationSourcesList(null)==null);
    }

    @Test
    public void testListInformationSourcesListAmount() throws InvalidParametersException  {
        assertFalse(controller.listInformationSourcesListAmount(1, null)==null);
    }

    @Test
    public void testListInformationSourcesListFromTo() throws InvalidParametersException  {
        assertFalse(controller.listInformationSourcesListFromTo(0, 1, null)==null);
    }
    
}
