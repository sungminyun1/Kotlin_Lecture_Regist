package com.hhplus.fcfs

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.springframework.transaction.annotation.Transactional
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Transactional
@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
interface TestContainerSupporter {
    companion object {
        // MySQL 컨테이너를 static처럼 관리
        @Container
        val mysqlContainer = MySQLContainer<Nothing>("mysql:8.0").apply {
            withDatabaseName("testdb")
            withUsername("test_user")
            withPassword("test_password")
//            withInitScript("init.sql") // 스크립트 자동 실행
        }.also {
            it.start()
        }

        // Spring Boot의 DynamicPropertySource를 이용해
        // 컨테이너에서 할당된 url/port 등을 스프링 환경 설정에 주입
        @JvmStatic
        @DynamicPropertySource
        fun registerDataSourceProps(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url") { mysqlContainer.jdbcUrl }
            registry.add("spring.datasource.username") { mysqlContainer.username }
            registry.add("spring.datasource.password") { mysqlContainer.password }
        }
    }
}