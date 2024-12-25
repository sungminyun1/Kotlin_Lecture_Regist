package com.hhplus.fcfs.domain.lecture

interface LectureRepository {
    fun findLectureByIdForUpdate(lectureId: Long): Lecture?
}
