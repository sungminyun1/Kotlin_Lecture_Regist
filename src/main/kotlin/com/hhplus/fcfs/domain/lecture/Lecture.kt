package com.hhplus.fcfs.domain.lecture

import com.hhplus.fcfs.domain.baseEntity.BaseEntity
import com.hhplus.fcfs.domain.member.Member
import com.hhplus.fcfs.domain.memberlecture.MemberLecture
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class Lecture(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    val name: String,

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    val teacher: Member?,

    val datetime: LocalDateTime,

    var capacity: Int,

    @OneToMany(mappedBy = "lecture", cascade = [CascadeType.ALL], orphanRemoval = true)
    val members: MutableList<MemberLecture> = mutableListOf(),
) :BaseEntity() {

    companion object {
        val MAX_CAPACITY = 30
    }


    fun enroll(member: Member?){
        check(capacity > 0) { "수강신청 인원이 초과되었습니다" }
        requireNotNull(member){"수강 신청하려는 멤버를 찾을 수 없습니다"}

        members.add(MemberLecture(
            lecture = this,
            member = member
        ))

        capacity--;
    }
}