package fi.iot.iiframework.source;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Creates and manages InformationSource classes
 */
public class InformationSourceManager {
    
    private static List<InformationSource> sources = new ArrayList<>();
    
    public static void createSource(InformationSourceConfiguration config) {
        InformationSource source = new InformationSource(config);
        sources.add(source);
    }
    
}