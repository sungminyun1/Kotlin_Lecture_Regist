package com.hhplus.fcfs.domain.lecture

import com.hhplus.fcfs.domain.baseEntity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class Lecture(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    val teacherId: Long,

    val datetime: LocalDateTime,

    val capacity: Int,
) :BaseEntity() {
}