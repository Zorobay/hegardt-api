package se.hegardt.api.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import se.hegardt.api.validators.LongTextConstraint

@Entity
@Table(name = 'locations')
class Location {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator='location_id_seq')
    @SequenceGenerator(name='location_id_seq', sequenceName = 'location_id_seq')
    @Id
    @Column(name = 'id')
    Long id

    @NotNull
    String country

    String region
    String city

    @LongTextConstraint
    String notes

    @Min(-90)
    @Max(90)
    Double latitude

    @Min(-180)
    @Max(180)
    Double longitude

//    static constraints = {
//        country nullable: false
//        region nullable: true
//        city nullable: true
//        notes nullable: true, maxSize: ConstraintIntLimits.LONG_TEXT.value
//        latitude nullable: true, max: 90.0d, min: -90.0d
//        longitude nullable: true, max: 180.0d, min: -180.0d
//        fetchStatus nullable: false
//    }

//    void setCoordinates(LatLong latLong) {
//        setLatitude(latLong.latitude)
//        setLongitude(latLong.longitude)
//        setFetchStatus(LocationCoordinateFetchStatus.FETCHED)
//    }

}