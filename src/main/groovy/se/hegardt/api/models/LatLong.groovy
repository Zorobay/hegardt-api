package se.hegardt.api.models

import groovy.transform.CompileStatic

@CompileStatic
class LatLong {

    Double latitude
    Double longitude

    LatLong(Double latitude, Double longitude) {
        this.latitude = latitude
        this.longitude = longitude
    }
}
