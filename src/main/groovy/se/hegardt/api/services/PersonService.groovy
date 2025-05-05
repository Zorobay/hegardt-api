package se.hegardt.api.services

import groovy.transform.CompileStatic
import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import se.hegardt.api.entities.Person
import se.hegardt.api.repositories.PersonRepository

@CompileStatic
@Service
@Transactional
class PersonService {

    @Autowired
    private PersonRepository personRepository

    List<Person> getAllPersons() {
        return personRepository.findAll()
    }

    Person createPerson(Person person) {
        return personRepository.save(person)
    }
}
