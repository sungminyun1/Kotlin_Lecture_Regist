package com.hhplus.fcfs.infrastructure.lecture

import com.hhplus.fcfs.domain.lecture.Lecture
import com.hhplus.fcfs.domain.lecture.LectureRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class LectureRepositoryImpl(
    val lectureJpaRepository: LectureJpaRepository
): LectureRepository {
    override fun findLectureByIdForUpdate(lectureId: Long): Lecture? {
        return lectureJpaRepository.findByIdForUpdate(lectureId)
    }

    override fun findAllByDateTimeBetween(fromDate: LocalDateTime, toDate: LocalDateTime): List<Lecture> {
        return lectureJpaRepository.findAllByDateTimeBetween(fromDate, toDate)
    }
}