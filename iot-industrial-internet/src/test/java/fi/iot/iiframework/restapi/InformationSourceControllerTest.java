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
import fi.iot.iiframework.restapi.exceptions.TooManyRequestsException;
import fi.iot.iiframework.services.domain.InformationSourceObjectProvider;
import fi.iot.iiframework.services.domain.InformationSourceService;
import fi.iot.iiframework.source.InformationSourceManager;
import java.util.Date;
import java.util.HashSet;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestConfig.class})
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
    private InformationSourceManager sourceManager;

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
    private void initContext() {
        sourceA = new InformationSource();
        sourceA.setId(1l);

        sourceB = new InformationSource();
        sourceB.setId(2l);

        sensorA = InformationSourceObjectProvider.provideSensor();
        sensorB = InformationSourceObjectProvider.provideSensor();

        readoutA = InformationSourceObjectProvider.provideReadout();
        readoutB = InformationSourceObjectProvider.provideReadout();
        readoutC = InformationSourceObjectProvider.provideReadout();

        readoutA.setValue(21.0);
        readoutB.setValue(22.0);
        readoutC.setValue(23.0);

        sensorA.addReadout(readoutA);
        sensorA.addReadout(readoutB);
        sensorB.addReadout(readoutC);

        sourceA.addSensor(sensorA);
        sourceB.addSensor(sensorB);

        sourceService.save(sourceA);
        // Don't save this sourceService.save(sourceB);
    }

    /*
     Login as a moderator, broken if the in-memory logins are changed!
     */
    private void initLogin() {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken("moderator", "moderator")
        );
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testGetInformationSource() throws InvalidParametersException, ResourceNotFoundException {
        InformationSource foundSource = controller.getInformationSource(1, null);
        assertEquals(foundSource, sourceA);
    }

    @Test
    public void testAddInformationSource() throws InvalidParametersException, ResourceNotFoundException, InvalidObjectException {
        controller.addInformationSource(sourceB, null);
        InformationSource foundSource = controller.getInformationSource(2, null);
        assertEquals(foundSource, sourceB);
    }

//    @Test
//    public void testEditInformationSource() throws InvalidParametersException, ResourceNotFoundException, InvalidObjectException  {
//        sourceA.setReadFrequency(5555555);
//        controller.editInformationSource(sourceA, null);
//        
//        Mockito.verify(sourceManager, Mockito.times(1)).updateSource(sourceA);
//    }
    @Test
    public void testDeleteInformationSource() throws InvalidParametersException, ResourceNotFoundException {
        controller.deleteInformationSource(1, null);
        Mockito.verify(sourceManager, Mockito.times(1)).removeSource(1);
    }

    @Test
    public void testListInformationSourcesList() throws InvalidParametersException {
        assertFalse(controller.listInformationSourcesList(null) == null);
    }

    @Test
    public void testListInformationSourcesListAmount() throws InvalidParametersException {
        assertFalse(controller.listInformationSourcesListAmount(1, null) == null);
    }

    @Test
    public void testListInformationSourcesListFromTo() throws InvalidParametersException {
        assertFalse(controller.listInformationSourcesListFromTo(0, 1, null) == null);
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void spammingReadInformationSourceThrowsTooManyRequestsException() throws InvalidParametersException, TooManyRequestsException {
        ResponseEntity<SuccessObject> response = controller.readInformationSource(sourceB.getId());
        System.out.println(response);
        exception.expect(TooManyRequestsException.class);
        response = controller.readInformationSource(sourceB.getId());
    }

}
