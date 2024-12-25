package com.hhplus.fcfs.domain.member

interface MemberRepository {
    fun findById(id: Long): Member?
}