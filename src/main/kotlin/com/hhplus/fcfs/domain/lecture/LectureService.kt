package com.hhplus.fcfs.domain.lecture

import com.hhplus.fcfs.domain.lecture.dto.LectureEnrollSerReq
import com.hhplus.fcfs.domain.lecture.dto.LectureEnrollSerRes
import com.hhplus.fcfs.domain.member.MemberRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class LectureService(
    private val lectureRepository: LectureRepository,
    private val memberRepository: MemberRepository
) {
    @Transactional
    fun enrollLecture(
        request: LectureEnrollSerReq
    ): LectureEnrollSerRes {
        val member = memberRepository.findById(request.userId)
            ?: throw IllegalArgumentException("Cannot find user with id ${request.userId}")

        val targetLecture = lectureRepository.findLectureByIdForUpdate(request.lectureId)
            ?: throw IllegalArgumentException("Cannot find lecture with id ${request.lectureId}")

        targetLecture.enroll(member)

        return LectureEnrollSerRes.from(member, targetLecture)
    }
}