package se.hegardt.api.services

import groovy.transform.CompileStatic
import jakarta.transaction.Transactional
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException
import se.hegardt.api.entities.Location
import se.hegardt.api.enums.LocationCoordinateFetchStatus
import se.hegardt.api.models.LatLong
import se.hegardt.api.repositories.LocationRepository

@CompileStatic
@Transactional
@Service
class LocationService {

    private static final Logger logger = LogManager.getLogger(this)

    @Autowired
    RadarAPIService radarAPIService
    @Autowired
    CountryService countryService
    @Autowired
    private LocationRepository locationRepository

    void processAwaitingElements() {
        List<Location> locations = locationRepository.findAllByFetchStatus(LocationCoordinateFetchStatus.AWAITING, Pageable.ofSize(5))

        locations.each { Location location ->
            try {
                LatLong latLong = findCoordinates(location)
                if (latLong) {
                    location.setCoordinates(latLong)
                    logger.info("Updated coordinates for location id: ${location.id}")
                } else {
                    location.setFetchStatus(LocationCoordinateFetchStatus.NO_COORDINATES_FOUND)
                    logger.warn("No coordinates found for location id: ${location.id}")
                }
            } catch (HttpClientErrorException e) {
                logger.error("Error fetching coordinates from Radar API for location id: ${location.id}", e)

                if (!(e.statusCode.value() in [HttpStatus.UNAUTHORIZED, HttpStatus.FORBIDDEN]*.value())) {
                    location.setFetchStatus(LocationCoordinateFetchStatus.ERROR)
                }
            }

            locationRepository.save(location)
        }
    }

    private LatLong findCoordinates(Location loc) {
        String countryCode = countryService.getCountryCode(loc.country)
        List<LatLong> coordinates = radarAPIService.geocodeToCoordinates(countryCode, loc.region, loc.city)
        return coordinates ? coordinates.first() : null
    }
}
