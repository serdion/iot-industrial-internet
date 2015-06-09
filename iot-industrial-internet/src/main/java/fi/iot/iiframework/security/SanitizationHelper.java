/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.security;

import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import java.io.File;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.stereotype.Component;

@Component
public class SanitizationHelper {

    private static final File slashdot = new File("src/main/resources/antisamy-slashdot-1.4.4.xml");
    private static final File ebay = new File("src/main/resources/antisamy-ebay-1.4.4.xml");
    private static final File nothing = new File("src/main/resources/antisamy-nothing.xml");

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
        return sanitize(input, slashdot);
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
        return sanitize(input, ebay);
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
        return sanitize(input, nothing);
    }

    /*
     * Sanitizes a String using given policy.
     */
    private static String sanitize(String input, File policy) {
        try {
            AntiSamy as = new AntiSamy();
            CleanResults cr = as.scan(input, policy);

            if (cr.getCleanHTML().isEmpty()) {
                return "NaN";
            }

            return cr.getCleanHTML();

        } catch (ScanException ex) {
            ErrorLogger.log(ErrorType.NOT_ACCEPTED, ErrorSeverity.LOW, "Scan exception during sanitizion process: " + ex.toString());
        } catch (PolicyException ex) {
            ErrorLogger.log(ErrorType.NOT_ACCEPTED, ErrorSeverity.LOW, "Policy exception during sanitizion process: " + ex.toString());
        }

        return "NaN";
    }

}
