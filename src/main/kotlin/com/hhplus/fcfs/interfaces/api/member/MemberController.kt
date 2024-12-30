package com.hhplus.fcfs.interfaces.api.member

import com.hhplus.fcfs.domain.member.MemberService
import com.hhplus.fcfs.interfaces.api.lecture.dto.LectureResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/member")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping("/{userId}")
    fun getUserEnrolledLecgtures(
        @PathVariable userId: Long,
    ): List<LectureResponse>{
        val responses = memberService.getUserEnrolledLecture(userId)
        return responses.map { LectureResponse.of(it) }
    }
}