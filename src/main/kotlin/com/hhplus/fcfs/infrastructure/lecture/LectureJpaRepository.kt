package com.hhplus.fcfs.infrastructure.lecture

import com.hhplus.fcfs.domain.lecture.Lecture
import jakarta.persistence.LockModeType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query

interface LectureJpaRepository: JpaRepository<Lecture, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select l from Lecture l join fetch l.teacher where l.id = :id
    """)
    fun findByIdForUpdate(id: Long): Lecture?
}