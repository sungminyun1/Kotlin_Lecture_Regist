package com.hhplus.fcfs.infrastructure.member

import com.hhplus.fcfs.domain.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository: JpaRepository<Member, Long> {

}