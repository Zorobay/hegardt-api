package se.hegardt.api.controllers

import groovy.transform.CompileStatic
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import se.hegardt.api.entities.Person
import se.hegardt.api.services.PersonService

@CompileStatic
@RestController
@RequestMapping('/api/persons')
class PersonController {

    @Autowired
    PersonService personService

    @GetMapping
    List<Person> getAllPersons() {
        return personService.getAllPersons()
    }

    @PostMapping
    Person createPerson(@RequestBody @Valid Person person) {
        return personService.createPerson(person)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException)
    Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = [:]
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField()
            String errorMessage = error.getDefaultMessage()
            errors.put(fieldName, errorMessage)
        })
        return errors
    }

}
