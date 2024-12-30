package com.hhplus.fcfs.interfaces.api.lecture.dto

import com.hhplus.fcfs.domain.lecture.dto.LectureSerRes

data class LectureResponse(
    val lectureId: Long,
    val lectureName: String,
    val teacherName: String,
    val capacity: Int,
){
    companion object{
        fun of(response: LectureSerRes): LectureResponse {
            return LectureResponse(
                lectureId = response.lectureId,
                lectureName = response.lectureName,
                teacherName = response.teacherName,
                capacity = response.capacity,
            )
        }
    }
}
