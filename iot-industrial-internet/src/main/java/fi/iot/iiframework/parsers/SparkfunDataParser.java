/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.parsers;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import fi.iot.iiframework.domain.Readout;
import fi.iot.iiframework.domain.Sensor;
import fi.iot.iiframework.errors.ErrorLogger;
import fi.iot.iiframework.errors.ErrorSeverity;
import fi.iot.iiframework.errors.ErrorType;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.joda.time.DateTime;

public class SparkfunDataParser {

    public static List<Sensor> parse(String location) {
        try {
            URL url = new URL(location);
            return parse(url);
        } catch (MalformedURLException ex) {
            ErrorLogger.log(ErrorType.BAD_REQUEST, ErrorSeverity.MEDIUM, "Tried to parse XML to Object but URI given could not be formed correctly");
        } catch (IOException ex) {
            ErrorLogger.log(ErrorType.IO_ERROR, ErrorSeverity.MEDIUM, "Tried to parse XML to Object but failed due to an error: " + ex.toString());
        }
        return null;
    }

    public static List<Sensor> parse(URL url) throws IOException {
        JsonParser parser = new JsonParser();
        BufferedReader in
                = new BufferedReader(new InputStreamReader(url.openStream()));

        Map<String, Sensor> sensors = new HashMap<>();

        JsonElement document = parser.parse(in);
        readSensors(document, sensors);

        return Arrays.asList(sensors.values().toArray(new Sensor[0]));
    }

    private static void readSensors(JsonElement document, Map<String, Sensor> sensors) {
        document.getAsJsonArray().iterator().forEachRemaining(a -> {
            Set<Entry<String, JsonElement>> entrySet = a.getAsJsonObject().entrySet();
            long timestamp = findTimeStamp(entrySet);
            readReadoutsToSensors(entrySet, sensors, timestamp);
        });
    }

    private static void readReadoutsToSensors(Set<Entry<String, JsonElement>> entrySet, Map<String, Sensor> sensors, long timestamp) {
        entrySet.forEach(entry -> {
            if (entry.getKey().equals("timestamp")) {
                return;
            }
            if (!sensors.containsKey(entry.getKey())) {
                addFoundSensor(entry, sensors);
                if (!sensors.containsKey(entry.getKey())) {
                    return;
                }
            }
            sensors.get(entry.getKey())
                    .getReadouts()
                    .add(new Readout(timestamp, entry.getValue().getAsDouble(), sensors.get(entry.getKey())));
        });
    }

    private static void addFoundSensor(Entry<String, JsonElement> e, Map<String, Sensor> sensors) {
        try {
            e.getValue().getAsDouble();
        } catch (NumberFormatException ex) {
            return;
        }
        Sensor sensor = new Sensor();
        sensor.setName(e.getKey());
        sensor.setReadouts(new HashSet<>());
        sensors.put(e.getKey(), sensor);
    }

    private static long findTimeStamp(Set<Entry<String, JsonElement>> entrySet) {
        return new DateTime(entrySet.stream()
                .filter(e -> e.getKey().equals("timestamp"))
                .findFirst()
                .get()
                .getValue()
                .getAsString())
                .getMillis();
    }

}
