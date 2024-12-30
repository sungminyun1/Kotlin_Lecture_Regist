package com.hhplus.fcfs.interfaces.api.lecture

import com.hhplus.fcfs.domain.lecture.LectureService
import com.hhplus.fcfs.interfaces.api.lecture.dto.LectureEnrollRequest
import com.hhplus.fcfs.interfaces.api.lecture.dto.LectureResponse
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.Date

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

    @GetMapping("/{date}")
    fun getLectures(
        @PathVariable("date") date: LocalDateTime
    ): List<LectureResponse> {
        val responses = lectureService.getLecturesByDate(date)
        return responses.map { LectureResponse.of(it) }
    }
}