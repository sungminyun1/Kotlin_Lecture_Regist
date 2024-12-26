package com.hhplus.fcfs.domain.member

import com.hhplus.fcfs.TestContainerSupporter
import com.hhplus.fcfs.domain.lecture.LectureRepository
import com.hhplus.fcfs.domain.memberlecture.MemberLecture
import com.hhplus.fcfs.domain.memberlecture.MemberLectureRepository
import com.hhplus.fcfs.infrastructure.memberlecture.MemberLectureJpaRepository
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDateTime

class MemberServiceTest: TestContainerSupporter, FunSpec(){
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    private lateinit var memberService: MemberService

    @Autowired
    private lateinit var memberLectureRepository: MemberLectureRepository

    @Autowired
    private lateinit var memberLectureJpaRepository: MemberLectureJpaRepository

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var lectureRepository: LectureRepository

    init {

        test("유저 id로 자신이 신청한 과목들을 조회한다"){
            //given
            val student = memberRepository.findById(2)
            val lectures = lectureRepository.findAllByDateTimeBetween(
                LocalDateTime.of(2024,12,25,0,0),
                LocalDateTime.of(2024,12,26,0,0)
            )

            memberLectureJpaRepository.save(MemberLecture(
                member = student!!,
                lecture = lectures[0],
            ))
            memberLectureJpaRepository.save(MemberLecture(
                member = student,
                lecture = lectures[1],
            ))

            //when
            val result = memberService.getUserEnrolledLecture(2)

            //then
            result.size shouldBe 2
        }

        test("유저 아이디의 신청한 과목이 없다면 빈 리스트를 반환한다"){
            //given
            val student = memberRepository.findById(2)

            //when
            val result = memberService.getUserEnrolledLecture(2)

            //then
            result.size shouldBe 0
        }

    }
}