package com.hhplus.fcfs.infrastructure.memberlecture

import com.hhplus.fcfs.domain.lecture.Lecture
import com.hhplus.fcfs.domain.member.Member
import com.hhplus.fcfs.domain.memberlecture.MemberLecture
import com.hhplus.fcfs.domain.memberlecture.MemberLectureRepository
import org.springframework.stereotype.Repository

@Repository
class MemberLectureRepositoryImpl(
    private val memberLectureJpaRepository: MemberLectureJpaRepository
): MemberLectureRepository {
    override fun findAllByMember(member: Member): List<Lecture> {
        return memberLectureJpaRepository.findAllByMember(member)
            .map { it.lecture }
    }
}