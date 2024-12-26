package com.hhplus.fcfs.domain.lecture

import com.hhplus.fcfs.domain.lecture.dto.LectureEnrollSerReq
import com.hhplus.fcfs.domain.lecture.dto.LectureSerRes
import com.hhplus.fcfs.domain.member.Member
import com.hhplus.fcfs.domain.member.MemberRepository
import com.hhplus.fcfs.domain.memberlecture.MemberLectureRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class LectureService(
    private val lectureRepository: LectureRepository,
    private val memberRepository: MemberRepository,
    private val memberLectureRepository: MemberLectureRepository,
) {
    @Transactional
    fun enrollLecture(
        request: LectureEnrollSerReq
    ): LectureSerRes {
        val member = memberRepository.findById(request.userId)
            ?: throw IllegalArgumentException("Cannot find user with id ${request.userId}")

        val targetLecture = lectureRepository.findLectureByIdForUpdate(request.lectureId)
            ?: throw IllegalArgumentException("Cannot find lecture with id ${request.lectureId}")

        if(checkAlreadyEnrolled(member, targetLecture)) {
            throw IllegalStateException("User ${member.id} already enrolled lecture ${targetLecture.id}")
        }

        targetLecture.enroll(member)

        return LectureSerRes.from(targetLecture)
    }

    private fun checkAlreadyEnrolled(member:Member, lecture:Lecture):Boolean {
        val res = memberLectureRepository.findByMemberAndLecture(member, lecture)
            ?:return false
        return true
    }

    @Transactional
    fun getLecturesByDate(
        targetDate: LocalDateTime
    ): List<LectureSerRes> {
        val startOfDay = targetDate.toLocalDate().atStartOfDay()

        val endOfDay = startOfDay.plusDays(1)

        val responses = lectureRepository.findAllByDateTimeBetween(startOfDay, endOfDay)

        return responses.map { LectureSerRes.from(it) }
    }
}