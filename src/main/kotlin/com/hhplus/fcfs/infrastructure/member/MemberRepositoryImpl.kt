package com.hhplus.fcfs.infrastructure.member

import com.hhplus.fcfs.domain.member.Member
import com.hhplus.fcfs.domain.member.MemberRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class MemberRepositoryImpl(
    val memberJpaRepository: MemberJpaRepository
): MemberRepository {
    override fun findById(id: Long): Member? {
        return memberJpaRepository.findByIdOrNull(id)
    }
}