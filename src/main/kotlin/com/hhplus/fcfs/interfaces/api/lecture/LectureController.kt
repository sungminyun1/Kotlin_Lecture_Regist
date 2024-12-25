package com.hhplus.fcfs.interfaces.api.lecture

import com.hhplus.fcfs.domain.lecture.LectureService
import com.hhplus.fcfs.interfaces.api.lecture.dto.LectureEnrollRequest
import com.hhplus.fcfs.interfaces.api.lecture.dto.LectureResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/lecture")
class LectureController(
    private val lectureService: LectureService
) {

    @PostMapping("/enrollment")
    fun enrollLecture(
        @RequestBody request: LectureEnrollRequest
    ): LectureResponse {
        val response = lectureService.enrollLecture(LectureEnrollRequest.toServiceRequest(request))
        return LectureResponse.of(response)
    }
}