package se.hegardt.api.repositories

import org.springframework.data.jpa.repository.JpaRepository
import se.hegardt.api.entities.PartialDate

interface PartialDateRepository extends JpaRepository<PartialDate, Long> {
    // Define custom query methods if needed

}