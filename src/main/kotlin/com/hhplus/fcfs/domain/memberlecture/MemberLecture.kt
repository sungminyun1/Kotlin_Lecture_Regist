package com.hhplus.fcfs.domain.memberlecture

import com.hhplus.fcfs.domain.baseEntity.BaseEntity
import com.hhplus.fcfs.domain.lecture.Lecture
import com.hhplus.fcfs.domain.member.Member
import jakarta.persistence.*

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(
            name = "uk_member_lecture_lecture_id_member_id",
            columnNames = ["lecture_id", "member_id"]
        )
    ]

)
class MemberLecture(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", nullable = false)
    val lecture: Lecture,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    val member: Member,

    ): BaseEntity() {
}