package se.hegardt.api.enums

import groovy.transform.CompileStatic

@CompileStatic
enum LocationCoordinateFetchStatus {
    AWAITING,
    FETCHED,
    NO_COORDINATES_FOUND,
    ERROR,
}
