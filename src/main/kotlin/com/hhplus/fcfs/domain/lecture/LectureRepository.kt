package com.hhplus.fcfs.domain.lecture

import java.time.LocalDateTime

interface LectureRepository {
    fun findLectureByIdForUpdate(lectureId: Long): Lecture?

    fun findAllByDateTimeBetween(fromDate: LocalDateTime, toDate: LocalDateTime): List<Lecture>
}
