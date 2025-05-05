package se.hegardt.api.enums

import com.fasterxml.jackson.annotation.JsonCreator
import groovy.transform.CompileStatic

@CompileStatic
enum Sex {

    MAN("Man"),
    WOMAN("Woman"),
    UNKNOWN("Unknown")

    String pretty

    @JsonCreator
    static Sex fromValue(String val) {
        if(val) {
            return valueOf(val)
        }
        return null
    }

    Sex(String pretty) {
        this.pretty = pretty
    }

}