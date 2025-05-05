package se.hegardt.api.entities

import groovy.transform.CompileStatic
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import se.hegardt.api.entities.traits.TimestampAware
import se.hegardt.api.enums.Sex

@CompileStatic
@Entity
@Table(name = 'persons')
class Person implements TimestampAware {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator='person_id_seq')
    @SequenceGenerator(name='person_id_seq', sequenceName = 'person_id_seq')
    @Id
    @Column(name = 'id')
    Long id

    String firstName
    String lastName
    List<String> middleNames

    @Enumerated(EnumType.STRING)
    Sex sex = Sex.UNKNOWN

    @OneToOne(cascade = CascadeType.ALL)
    LifeEvent birth
    @OneToOne(cascade = CascadeType.ALL)
    LifeEvent death
    @OneToOne(cascade = CascadeType.ALL)
    LifeEvent burial

    List<String> occupations = []
    String notes

    List<String> references = []

    @OneToOne
    Person father

    @OneToOne
    Person mother

    @OneToMany()
    List<Person> children = []
}
