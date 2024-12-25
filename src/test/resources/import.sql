insert into member (id, name, created_at ) values (1, 'Teacher', NOW());
insert into member (id, name, created_at ) values (2, 'Student', NOW());


insert into lecture (id, capacity, datetime, name, teacher_id, created_at) values (1, 30, '2024-12-25 00:00:00', 'Test Lecture', 1, NOW())