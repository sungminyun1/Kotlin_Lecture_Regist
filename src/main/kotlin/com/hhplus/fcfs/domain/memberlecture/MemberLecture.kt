package com.hhplus.fcfs.domain.memberlecture

import com.hhplus.fcfs.domain.baseEntity.BaseEntity
import com.hhplus.fcfs.domain.lecture.Lecture
import com.hhplus.fcfs.domain.member.Member
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne

@Entity
class MemberLecture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne
    val lecture: Lecture,

    @ManyToOne
    val member: Member,

    ): BaseEntity() {
}