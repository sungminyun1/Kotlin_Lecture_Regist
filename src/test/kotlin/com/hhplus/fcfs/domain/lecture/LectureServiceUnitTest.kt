package com.hhplus.fcfs.domain.lecture

import com.hhplus.fcfs.domain.lecture.dto.LectureEnrollSerReq
import com.hhplus.fcfs.domain.member.Member
import com.hhplus.fcfs.domain.member.MemberRepository
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.mockito.internal.matchers.Any
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.bean.override.mockito.MockitoBean
import java.time.LocalDateTime
import java.util.Date

@SpringBootTest
class LectureServiceUnitTest: FunSpec(){

    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var lectureService: LectureService

    @MockitoBean
    lateinit var memberRepository: MemberRepository

    @MockitoBean
    lateinit var lectureRepository: LectureRepository

    lateinit var dummyStudent: Member
    lateinit var dummyTeacher: Member
    lateinit var dummyLecture: Lecture
    lateinit var dummyLecture2: Lecture

    init {
        beforeEach {
            dummyTeacher = Member(
                id = 1,
                name = "Teacher"
            )

            dummyStudent = Member(
                id = 2,
                name = "Student"
            )

            dummyLecture = Lecture(
                id = 1,
                teacher = dummyTeacher,
                name = "Test Lecture",
                capacity = Lecture.MAX_CAPACITY,
                datetime = LocalDateTime.of(2024,12,5,13,0)
            )

            dummyLecture2 = Lecture(
                id = 1,
                teacher = dummyTeacher,
                name = "Test Lecture2",
                capacity = Lecture.MAX_CAPACITY,
                datetime = LocalDateTime.of(2024,12,5,17,0)
            )
        }

        test("사용자 id와 강의 id로 수강 신청을 한다"){
            //given
            val request = LectureEnrollSerReq(2,1)
            given(memberRepository.findById(2)).willReturn(dummyStudent)
            given(lectureRepository.findLectureByIdForUpdate(1)).willReturn(dummyLecture)

            val result = lectureService.enrollLecture(request)

            result.lectureId shouldBe 1
            result.lectureName shouldBe "Test Lecture"
            result.teacherName shouldBe "Teacher"
            dummyLecture.members.size shouldBe 1
            dummyLecture.capacity shouldBe Lecture.MAX_CAPACITY - 1
        }

        test("수강 신청인원이 초과되면 에러가 발생한다"){
            val request = LectureEnrollSerReq(2,1)
            given(memberRepository.findById(2)).willReturn(dummyStudent)
            given(lectureRepository.findLectureByIdForUpdate(1)).willReturn(dummyLecture)
            dummyLecture.capacity = 0

            val exception = shouldThrow<IllegalStateException> { lectureService.enrollLecture(request) }
            exception.message shouldBe "수강신청 인원이 초과되었습니다"
        }

        test("강의 목록을 일자별로 조회할 수 있다"){
            val reqDate = LocalDateTime.of(2024, 12, 25, 0, 0, 0)
            given(lectureRepository.findAllByDateTimeBetween(any(), any())).willReturn(listOf(dummyLecture,dummyLecture2))

            val responses = lectureService.getLecturesByDate(reqDate)

            responses.size shouldBe 2
        }

    }

}

