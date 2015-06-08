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
import java.util.ArrayList;
import org.owasp.validator.html.AntiSamy;
import org.owasp.validator.html.CleanResults;
import org.owasp.validator.html.PolicyException;
import org.owasp.validator.html.ScanException;
import org.springframework.stereotype.Component;

@Component
public class SanitizationHelper {

    private static final File slashdot = new File("src/main/resources/antisamy-slashdot-1.4.4.xml");
    private static final File ebay = new File("src/main/resources/antisamy-ebay-1.4.4.xml");

    /**
     * Sanitizes the input using default (Slashdot) policy, only following HTML
     * tags and no CSS: b, i, a, and blockquote.
     *
     * @param input Input String
     * @param logging If errors are logged to the DB or not.
     * @return Sanitized String
     * @throws ScanException
     * @throws PolicyException
     */
    public static String sanitize(String input, boolean logging) throws ScanException, PolicyException {
        return sanitize(input, slashdot, logging);
    }

    /**
     * Sanitizes the input using Slashdot policy, only following HTML tags and
     * no CSS: b, i, a, and blockquote.
     *
     * @param input Input String
     * @param logging If errors are logged to the DB or not.
     * @return Sanitized String
     * @throws ScanException
     * @throws PolicyException
     */
    public static String sanitizeSlashdot(String input, boolean logging) throws ScanException, PolicyException {
        return sanitize(input, slashdot, logging);
    }

    /**
     * Sanitizes the input using Slashdot policy, allows colors and semi-rich
     * variations in HTML.
     *
     * @param input Input String
     * @param logging If errors are logged to the DB or not.
     * @return Sanitized String
     * @throws ScanException
     * @throws PolicyException
     */
    public static String sanitizeEbay(String input, boolean logging) throws ScanException, PolicyException {
        return sanitize(input, ebay, logging);
    }

    /*
     * Sanitizes a String using given policy.
     */
    private static String sanitize(String input, File policy, boolean logging) throws PolicyException, ScanException {
        AntiSamy as = new AntiSamy();
        CleanResults cr = as.scan(input, policy);

        if (logging) {
            logErrorMessages(cr.getErrorMessages());
        }
        
        if(cr.getCleanHTML().isEmpty()){
            return "NaN";
        }

        return cr.getCleanHTML();
    }

    /*
     * Logs error messages found during sanitizion.
     */
    private static void logErrorMessages(ArrayList messages) {
        for (Object errorMessage : messages) {
            ErrorLogger.log(ErrorType.PARSE_ERROR, ErrorSeverity.LOW, "Sanitizion error occured: " + errorMessage);
        }
    }

}
