package com.hhplus.fcfs.infrastructure.memberlecture

import com.hhplus.fcfs.domain.member.Member
import com.hhplus.fcfs.domain.memberlecture.MemberLecture
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query


interface MemberLectureJpaRepository: JpaRepository<MemberLecture, Long> {

    @Query("""
        select ml from MemberLecture ml join fetch ml.lecture where ml.member = :user
    """)
    fun findAllByMember(user: Member): List<MemberLecture>
}