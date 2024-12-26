package com.hhplus.fcfs.domain.memberlecture

import com.hhplus.fcfs.domain.baseEntity.BaseEntity
import com.hhplus.fcfs.domain.lecture.Lecture
import com.hhplus.fcfs.domain.member.Member
import jakarta.persistence.*

@Entity
class MemberLecture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val lecture: Lecture,

    @ManyToOne(fetch = FetchType.LAZY)
    val member: Member,

    ): BaseEntity() {
}