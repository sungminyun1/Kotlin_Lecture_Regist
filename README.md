# Kotlin_Lecture_Regist

## ERD
![image](https://github.com/user-attachments/assets/8d3b6fdf-1e2f-47a7-8e9c-4bd36336132d)

### Lecture
강의 정보를 저장한다. capacity 라고 수강 가능한 인원이 저장된다.
동시성 제어를 위해 수강 신청을할때 이 테이블의 row를 select for update 로 조회해서 lock을 건다

### Member
사용자 정보를 저장하는 테이블이다

### MemberLecture 
사용자가 강의를 신청할때 N:M 매핑을 해주는 매핑테이블이다 
