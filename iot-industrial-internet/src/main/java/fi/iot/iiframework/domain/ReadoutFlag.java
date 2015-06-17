/*
 * IoT - Industrial Internet Framework
 * Apache License Version 2.0, January 2004
 * Released as a part of Helsinki University
 * Software Engineering Lab in summer 2015
 */
package fi.iot.iiframework.domain;

import fi.iot.iiframework.mutator.ValueCondition;

public enum ReadoutFlag {

    ERRONOUS,
    POSSIBLE_ERRONOUS,
    TOO_HIGH_VALUE,
    TOO_LOW_VALUE,
    EMPTY;

    /**
     * Returns a Flag representation from a ValueCondition
     *
     * @param condition Condition to evaluate
     * @return Flag that corresponds the ValueCondition given
     */
    public static ReadoutFlag getFlagFromCondition(ValueCondition condition) {
        if (condition == ValueCondition.HIGHER_THAN) {
            return TOO_HIGH_VALUE;
        }

        if (condition == ValueCondition.LOWER_THAN) {
            return TOO_LOW_VALUE;
        }

        return EMPTY;
    }
}
