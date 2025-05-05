package se.hegardt.api.entities

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import se.hegardt.api.enums.LocationCoordinateFetchStatus
import se.hegardt.api.models.LatLong
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

    @Enumerated(EnumType.STRING)
    @NotNull
    LocationCoordinateFetchStatus fetchStatus = LocationCoordinateFetchStatus.AWAITING

    void setCoordinates(LatLong latLong) {
        setLatitude(latLong.latitude)
        setLongitude(latLong.longitude)
        setFetchStatus(LocationCoordinateFetchStatus.FETCHED)
    }

}
