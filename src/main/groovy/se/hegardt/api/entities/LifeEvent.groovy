package se.hegardt.api.entities

import groovy.transform.CompileStatic
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToOne
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import se.hegardt.api.validators.LongTextConstraint

@CompileStatic
@Entity
@Table(name = 'life_events')
class LifeEvent {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = 'life_event_id_seq')
    @SequenceGenerator(name = 'life_event_id_seq', sequenceName = 'life_event_id_seq', initialValue = 1, allocationSize = 1)
    @Id
    @Column(name = 'id')
    Long id

    @OneToOne(cascade = CascadeType.ALL)
    PartialDate date

    @OneToOne(cascade = CascadeType.ALL)
    Location location

    @LongTextConstraint
    String notes
}
