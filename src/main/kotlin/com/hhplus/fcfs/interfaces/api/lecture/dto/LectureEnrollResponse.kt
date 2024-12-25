package com.hhplus.fcfs.interfaces.api.lecture.dto

data class LectureEnrollResponse(
    val userId: Long,
    val lectureId: Long,
    val lectureName: String,
    val teacherName: String
)
