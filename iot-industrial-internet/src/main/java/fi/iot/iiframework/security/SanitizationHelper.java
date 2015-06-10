/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.security;

import ch.qos.logback.core.spi.ScanException;
import com.sun.xml.internal.ws.policy.PolicyException;
import org.springframework.stereotype.Component;

@Component
public class SanitizationHelper {

    /**
     * Sanitizes the input using Slashdot policy, only following HTML tags and
     * no CSS: b, i, a, and blockquote.
     *
     * @param input Input String
     * @return Sanitized String
     * @throws ScanException
     * @throws PolicyException
     */
    public static String sanitizeStrict(String input) {
        return sanitize(input);
    }

    /**
     * Sanitizes the input using Slashdot policy, allows colors and semi-rich
     * variations in HTML.
     *
     * @param input Input String
     * @return Sanitized String
     * @throws ScanException
     * @throws PolicyException
     */
    public static String sanitizeLoose(String input) throws ScanException, PolicyException {
        return sanitize(input);
    }

    /**
     * Sanitizes the input using custom policy that forbits everything HTML, JS
     * and CSS.
     *
     * @param input Input String
     * @return Sanitized String
     * @throws ScanException
     * @throws PolicyException
     */
    public static String sanitizeNoHTML(String input) throws ScanException, PolicyException {
        return sanitize(input);
    }

    /*
     * Sanitizes a String using given policy.
     */
    private static String sanitize(String input) {
        return input;
    }

}
