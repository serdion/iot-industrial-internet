/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.restapi;

import fi.iot.iiframework.application.TestConfig;
import fi.iot.iiframework.domain.InformationSource;
import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.restapi.exceptions.InvalidObjectException;
import fi.iot.iiframework.restapi.exceptions.InvalidParametersException;
import fi.iot.iiframework.restapi.exceptions.ResourceNotFoundException;
import fi.iot.iiframework.services.domain.InformationSourceObjectProvider;
import fi.iot.iiframework.services.domain.InformationSourceService;
import fi.iot.iiframework.services.domain.ReadoutService;
import fi.iot.iiframework.services.domain.SensorService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Ignore
@TransactionConfiguration(defaultRollback = true)
@Transactional
@SpringApplicationConfiguration(classes = {TestConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class InformationSourceControllerTest {
    
    @Autowired
    private InformationSourceController controller;
    
    private InformationSource sourceA;
    private InformationSource sourceB;

    private Sensor sensorA;
    private Sensor sensorB;

    private Readout readoutA;
    private Readout readoutB;
    private Readout readoutC;
    
    @Autowired
    private InformationSourceService sourceService;
    
    @Before
    public void setUp() {
        initLogin();
        initContext();
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
        
        InformationSource foundSource = controller.getInformationSource("sourceA", null);
        assertEquals(foundSource.getReadFrequency(), 5555555);
    }

    @Test
    public void testDeleteInformationSource() throws InvalidParametersException, ResourceNotFoundException  {
        controller.deleteInformationSource("sourceA", null);
        assertEquals(controller.getInformationSource("sourceA", null), null);
    }

    @Test
    public void testListInformationSourcesList() throws InvalidParametersException  {
        List<InformationSource> asList = controller.listInformationSourcesList(null);
        
        assertTrue(asList.contains(sourceA));
    }

    @Test
    public void testListInformationSourcesListAmount() throws InvalidParametersException  {
        sourceService.save(sourceB);
        List<InformationSource> asList = controller.listInformationSourcesListAmount(1, null);
        
        assertFalse(asList.contains(sourceB));
    }

    @Test
    public void testListInformationSourcesListFromTo() throws InvalidParametersException  {
        sourceService.save(sourceB);
        List<InformationSource> asList = controller.listInformationSourcesListFromTo(0, 1, null);
        
        assertFalse(asList.contains(sourceB));
    }
    
}
