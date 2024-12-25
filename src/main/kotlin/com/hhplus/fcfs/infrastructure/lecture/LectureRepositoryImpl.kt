package com.hhplus.fcfs.infrastructure.lecture

import com.hhplus.fcfs.domain.lecture.Lecture
import com.hhplus.fcfs.domain.lecture.LectureRepository
import org.springframework.stereotype.Repository

@Repository
class LectureRepositoryImpl(
    val lectureJpaRepository: LectureJpaRepository
): LectureRepository {
    override fun findLectureByIdForUpdate(lectureId: Long): Lecture? {
        return lectureJpaRepository.findByIdForUpdate(lectureId)
    }
}