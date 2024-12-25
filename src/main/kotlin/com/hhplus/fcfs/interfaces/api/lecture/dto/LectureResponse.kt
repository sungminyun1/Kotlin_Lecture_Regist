package com.hhplus.fcfs.interfaces.api.lecture.dto

import com.hhplus.fcfs.domain.lecture.dto.LectureEnrollSerRes

data class LectureResponse(
    val userId: Long,
    val lectureId: Long,
    val lectureName: String,
    val teacherName: String
){
    companion object{
        fun of(response: LectureEnrollSerRes): LectureResponse {
            return LectureResponse(
                userId = response.userId,
                lectureId = response.lectureId,
                lectureName = response.lectureName,
                teacherName = response.teacherName
            )
        }
    }
}
