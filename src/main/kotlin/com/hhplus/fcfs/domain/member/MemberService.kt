package com.hhplus.fcfs.domain.member

import com.hhplus.fcfs.domain.lecture.dto.LectureSerRes
import com.hhplus.fcfs.domain.memberlecture.MemberLectureRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val memberLectureRepository: MemberLectureRepository,
) {

    @Transactional(readOnly = true)
    fun getUserEnrolledLecture(
        userId: Long
    ): List<LectureSerRes>{
        val member = memberRepository.findById(userId)
            ?: throw IllegalArgumentException("User does not exist. userId $userId", )

        return memberLectureRepository.findAllByMember(member)
            .map { LectureSerRes.from(it) }
    }
}