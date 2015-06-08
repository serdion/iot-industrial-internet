/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.security;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;

public class SanitizationHelperTest {

    private static final String alwaysAcceptedInputs[] = {
        "Default String with no HTML or CSS",
        "String with . / % and even ()",
        "             Alot of whitespaces                "
    };

    private static final String sometimesAcceptedInputs[] = {
        "<b>Bold text</b>",
        "<i>Italic text</i>",
        "<a>link text</a>"
    };

    private static final String testNotAcceptedInputs[] = {
        "<SCRIPT type=\"text/javascript\">Default Script-tag String</SCRIPT>",
        "<body onload=alert('Noob attack')>",
        "<b onmouseover=alert('Wufff!')>More complex JS Attack</b>",
        "<IMG SRC=j&#X41vascript:alert('Attack using encoded URI schemes')>",};

    public SanitizationHelperTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testSanitizeDefaultWithAcceptedInputs() throws Exception {
        for (int i = 0; i < alwaysAcceptedInputs.length; i++) {
            assertEquals(alwaysAcceptedInputs[i], SanitizationHelper.sanitize(alwaysAcceptedInputs[i], false));
        }

        for (int i = 0; i < sometimesAcceptedInputs.length; i++) {
            assertEquals(sometimesAcceptedInputs[i], SanitizationHelper.sanitize(sometimesAcceptedInputs[i], false));
        }
    }

    @Test
    public void testSanitizeDefaultWithNotAcceptedInputs() throws ScanException, PolicyException {
        for (int i = 0; i < testNotAcceptedInputs.length; i++) {
            SanitizationHelper.sanitize(testNotAcceptedInputs[i], false);
            assertNotEquals(testNotAcceptedInputs[i], SanitizationHelper.sanitize(testNotAcceptedInputs[i], false));
        }
    }
    
    @Test
    public void testSanitizeSlashdotWithAcceptedInputs() throws ScanException, PolicyException {
        for (int i = 0; i < alwaysAcceptedInputs.length; i++) {
            assertEquals(alwaysAcceptedInputs[i], SanitizationHelper.sanitizeSlashdot(alwaysAcceptedInputs[i], false));
        }

        for (int i = 0; i < sometimesAcceptedInputs.length; i++) {
            assertEquals(sometimesAcceptedInputs[i], SanitizationHelper.sanitizeSlashdot(sometimesAcceptedInputs[i], false));
        }
    }
    
    @Test
    public void testSanitizeSlashdotWithNotAcceptedInputs() throws ScanException, PolicyException {
        for (int i = 0; i < testNotAcceptedInputs.length; i++) {
            SanitizationHelper.sanitizeSlashdot(testNotAcceptedInputs[i], false);
            assertNotEquals(testNotAcceptedInputs[i], SanitizationHelper.sanitize(testNotAcceptedInputs[i], false));
        }
    }
    
    @Test
    public void testSanitizeEbayWithAcceptedInputs() throws ScanException, PolicyException {
        for (int i = 0; i < alwaysAcceptedInputs.length; i++) {
            assertEquals(alwaysAcceptedInputs[i], SanitizationHelper.sanitizeEbay(alwaysAcceptedInputs[i], false));
        }

        for (int i = 0; i < sometimesAcceptedInputs.length; i++) {
            assertEquals(sometimesAcceptedInputs[i], SanitizationHelper.sanitizeEbay(sometimesAcceptedInputs[i], false));
        }
    }
    
    @Test
    public void testSanitizeEbayWithNotAcceptedInputs() throws ScanException, PolicyException {
        for (int i = 0; i < testNotAcceptedInputs.length; i++) {
            SanitizationHelper.sanitizeEbay(testNotAcceptedInputs[i], false);
            assertNotEquals(testNotAcceptedInputs[i], SanitizationHelper.sanitize(testNotAcceptedInputs[i], false));
        }
    }

}
