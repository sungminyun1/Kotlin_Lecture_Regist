package com.hhplus.fcfs.domain.lecture.dto

import com.hhplus.fcfs.domain.lecture.Lecture
import com.hhplus.fcfs.domain.member.Member

data class LectureSerRes(
    val lectureId: Long,
    val lectureName: String,
    val teacherName: String,
    val capacity: Int
){
    companion object {
        fun from(lecture: Lecture): LectureSerRes {
            return LectureSerRes(
                lectureId = lecture.id!!,
                lectureName = lecture.name,
                teacherName = lecture.teacher!!.name,
                capacity = lecture.capacity
            )
        }
    }
}
