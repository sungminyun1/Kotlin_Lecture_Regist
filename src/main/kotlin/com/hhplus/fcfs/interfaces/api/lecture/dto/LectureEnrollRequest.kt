package com.hhplus.fcfs.interfaces.api.lecture.dto

import com.hhplus.fcfs.domain.lecture.dto.LectureEnrollSerReq

data class LectureEnrollRequest(
    val lectureId: Long,
    val userId: Long
){
    companion object{
        fun toServiceRequest(req: LectureEnrollRequest): LectureEnrollSerReq{
            return LectureEnrollSerReq(
                lectureId = req.lectureId,
                userId = req.userId
            )
        }
    }
}
