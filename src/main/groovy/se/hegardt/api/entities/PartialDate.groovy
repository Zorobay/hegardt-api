package se.hegardt.api.entities

import groovy.transform.CompileStatic
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.SequenceGenerator
import jakarta.persistence.Table
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

import java.time.DateTimeException
import java.time.LocalDate

@CompileStatic
@Entity
@Table(name = 'partial_dates')
class PartialDate {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator='partial_date_id_seq')
    @SequenceGenerator(name='partial_date_id_seq', sequenceName = 'partial_date_id_seq')
    @Id
    @Column(name = 'id')
    Long id

    @NotNull
    LocalDate date

    @NotNull
    @Positive
    Integer year

    @Positive
    @Max(12)
    Integer month

    @Positive
    @Max(31)
    Integer day

    def beforeValidate() {
        updateLocalDateField()
    }

    void updateLocalDateField() {
        date = calculateLocalDate()
    }

    private LocalDate calculateLocalDate() {
        try {
            return LocalDate.of(year, month ?: 1, day ?: 1)
        } catch (DateTimeException ignored) {
            return null
        }
    }
}
