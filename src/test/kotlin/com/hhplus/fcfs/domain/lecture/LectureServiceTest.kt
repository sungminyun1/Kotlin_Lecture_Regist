package com.hhplus.fcfs.domain.lecture

import com.hhplus.fcfs.TestContainerSupporter
import com.hhplus.fcfs.domain.lecture.dto.LectureEnrollSerReq
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import java.time.LocalDateTime
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors


class LectureServiceTest: FunSpec(), TestContainerSupporter {
    override fun extensions() = listOf(SpringExtension)

    @Autowired
    lateinit var lectureService: LectureService

    init {
        test("유저 아이디와 강의 아이디로 수강 신청을 한다"){
            val request = LectureEnrollSerReq(2,1)

            val result = lectureService.enrollLecture(request)

            result.lectureId shouldBe 1
            result.lectureName shouldBe "Test Lecture"
            result.teacherName shouldBe "Teacher"
        }

        test("유저 아이디와 강의 아이디로 수강 신청을 한다2"){
            val request = LectureEnrollSerReq(2,1)

            val result = lectureService.enrollLecture(request)

            result.lectureId shouldBe 1
            result.lectureName shouldBe "Test Lecture"
            result.teacherName shouldBe "Teacher"
        }

        test("하나의 강의는 선착순 30명만 신청할 수 있다. 40명 신청시 10번 실패"){
            val threadCount = 40
            var failCount = 0
            val startLatch = CountDownLatch(1);
            val doneLatch = CountDownLatch(threadCount)

            val executor = Executors.newFixedThreadPool(threadCount)

            repeat(threadCount) { i ->
                executor.submit {
                    try {
                        val request = LectureEnrollSerReq(i + 2L,1)

                        val result = lectureService.enrollLecture(request)

                    }catch(e: IllegalStateException){
                        failCount++
                    } finally {
                        doneLatch.countDown()
                    }
                }
            }

            startLatch.countDown()

            doneLatch.await()

            failCount shouldBe 10
        }

        test("강의 목록을 일자별로 조회할 수 있다"){
            val reqDate = LocalDateTime.of(2024, 12, 25, 0, 0, 0)

            val responses = lectureService.getLecturesByDate(reqDate)

            responses.size shouldBe 3
            responses.get(2).capacity shouldBe 30
            responses.get(1).capacity shouldBe 0
        }


        test("유저는 같은 강의에 한번만 수강 신청할 수 있다."){
            val request = LectureEnrollSerReq(3,3)
            lectureService.enrollLecture(request)

            val exception = shouldThrow<IllegalStateException> { lectureService.enrollLecture(request) }

            exception.message shouldBe "User 3 already enrolled lecture 3"
        }

        test("유저는 같은 강의에는 한번만 수강 신청할 수 있다. 동시에 5번 신청하면 한번만 성공"){
            val threadCount = 5
            var failCount = 0
            val startLatch = CountDownLatch(1)
            val doneLatch = CountDownLatch(threadCount)

            val executor = Executors.newFixedThreadPool(threadCount)

            repeat(threadCount) { i ->
                executor.submit {
                    try {
                        val request = LectureEnrollSerReq(4,3)

                        val result = lectureService.enrollLecture(request)

                    }catch(e: DataIntegrityViolationException){
                        failCount++
                    } finally {
                        doneLatch.countDown()
                    }
                }
            }

            startLatch.countDown()

            doneLatch.await()

            failCount shouldBe 4
        }
    }
}