/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.parsers;

import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.source.InformationSourceType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

/**
 *
 */
@Component
public final class ParserContainer {
    
    private static Map<InformationSourceType, Parser> parsers = new HashMap<>();    
    
    @PostConstruct
    public void ParserContainer() {
        parsers.put(InformationSourceType.JSON, new SparkfunDataParser());
        parsers.put(InformationSourceType.XML, new XMLParser());
    }

    public static Map<InformationSourceType, Parser> getParsers() {
        return parsers;
    }
}
