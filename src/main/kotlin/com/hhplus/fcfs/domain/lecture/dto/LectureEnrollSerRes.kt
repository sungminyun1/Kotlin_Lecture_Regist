package com.hhplus.fcfs.domain.lecture.dto

import com.hhplus.fcfs.domain.lecture.Lecture
import com.hhplus.fcfs.domain.member.Member

data class LectureEnrollSerRes(
    val userId: Long,
    val lectureId: Long,
    val lectureName: String,
    val teacherName: String
){
    companion object {
        fun from(user: Member, lecture: Lecture): LectureEnrollSerRes {
            return LectureEnrollSerRes(
                userId = user.id!!,
                lectureId = lecture.id!!,
                lectureName = lecture.name,
                teacherName = lecture.teacher!!.name
            )
        }
    }
}
