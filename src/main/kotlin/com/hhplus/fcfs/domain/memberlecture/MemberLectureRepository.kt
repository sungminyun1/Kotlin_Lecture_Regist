package com.hhplus.fcfs.domain.memberlecture

import com.hhplus.fcfs.domain.lecture.Lecture
import com.hhplus.fcfs.domain.member.Member


interface MemberLectureRepository {
    fun findAllByMember(member: Member): List<Lecture>
}