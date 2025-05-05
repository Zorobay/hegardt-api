package se.hegardt.api.entities.traits

import jakarta.persistence.Column
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

import java.time.LocalDateTime

trait TimestampAware {

    @CreationTimestamp
    @Column(name = 'created_date')
    LocalDateTime createdDate

    @UpdateTimestamp
    @Column(name = 'updated_date')
    LocalDateTime updateDate
}