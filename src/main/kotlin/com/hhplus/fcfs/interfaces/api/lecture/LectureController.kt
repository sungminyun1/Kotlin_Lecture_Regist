package com.hhplus.fcfs.interfaces.api.lecture

import com.hhplus.fcfs.interfaces.api.lecture.dto.LectureEnrollRequest
import com.hhplus.fcfs.interfaces.api.lecture.dto.LectureEnrollResponse
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/lecture")
class LectureController {

    @PostMapping("/enrollment")
    fun enrollLecture(
        @RequestBody request: LectureEnrollRequest
    ): LectureEnrollResponse {
        return LectureEnrollResponse(1,1,"","")
    }
}