package se.hegardt.api.repositories

import org.springframework.data.jpa.repository.JpaRepository
import se.hegardt.api.entities.Person

interface PersonRepository extends JpaRepository<Person, Long>{
    // Define custom query methods if needed

}