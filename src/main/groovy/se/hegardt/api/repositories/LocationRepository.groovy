package se.hegardt.api.repositories

import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import se.hegardt.api.entities.Location
import se.hegardt.api.enums.LocationCoordinateFetchStatus

interface LocationRepository extends JpaRepository<Location, Long> {

    List<Location> findAllByFetchStatus(LocationCoordinateFetchStatus fetchStatus, Pageable page)
}