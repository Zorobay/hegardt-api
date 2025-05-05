package se.hegardt.api.enums

import groovy.transform.CompileStatic

@CompileStatic
enum ConstraintIntLimits {

    // Text limits
    SHORT_TEXT(255),
    MEDIUM_TEXT(10000),
    LONG_TEXT(100000),

    // List limits
    LIST_LIMIT(100)

    ConstraintIntLimits(int limit) {
        value = limit
    }

    int value
}