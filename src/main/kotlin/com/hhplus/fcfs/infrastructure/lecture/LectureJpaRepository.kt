package com.hhplus.fcfs.infrastructure.lecture

import com.hhplus.fcfs.domain.lecture.Lecture
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

interface LectureJpaRepository: JpaRepository<Lecture, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select l from Lecture l join fetch l.teacher where l.id = :id
    """)
    fun findByIdForUpdate(id: Long): Lecture?

    @Query("""
        SELECT l FROM Lecture l
        WHERE l.datetime >= :startOfDay
          AND l.datetime < :endOfDay
    """)
    fun findAllByDateTimeBetween(
        @Param("startOfDay") startOfDay: LocalDateTime,
        @Param("endOfDay") endOfDay: LocalDateTime
    ): List<Lecture>
}