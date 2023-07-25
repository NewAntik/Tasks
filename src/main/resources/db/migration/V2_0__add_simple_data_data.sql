INSERT INTO courses (name, description)
VALUES ('Math', 'Math Description'),
       ('Biology', 'Biology Description'),
       ('Geography', 'Geography Description'),
       ('Music', 'Music Description'),
       ('History', 'History Description'),
       ('Physics', 'Physics Description'),
       ('Medicine', 'Medicine Description'),
       ('Tehnology', 'Tehnology Description');

INSERT INTO rooms (name)
VALUES ('Lecture hall #1'),
       ('Lecture hall #5'),
       ('Lecture hall #6'),
       ('Lecture hall #9'),
       ('Lecture hall #13'),
       ('Lecture hall #17');

INSERT INTO groups (name)
VALUES ('AA-01'),
       ('AA-02'),
       ('AA-03'),
       ('AA-04'),
       ('AA-05'),
       ('AA-05');
       
INSERT INTO users (login, password, role_type, role, group_ref, first_name, last_name)
VALUES ('admin', '$2a$10$M2Tyh1FN6UI7/JCCm5hpTukZ9M2cIYeLTQPWoCwW5NRS.boZH5jPa', 'admin', 'ADMIN', null, 'Main', 'Main'),
       ('staff', '$2a$10$M2Tyh1FN6UI7/JCCm5hpTukZ9M2cIYeLTQPWoCwW5NRS.boZH5jPa', 'User', 'STAFF', null, 'Henry', 'Karlos'), 
       ('student1', '$2a$10$M2Tyh1FN6UI7/JCCm5hpTukZ9M2cIYeLTQPWoCwW5NRS.boZH5jPa', 'student', 'STUDENT', 1, 'Jon', 'Cover'),
       ('teacher1', '$2a$10$M2Tyh1FN6UI7/JCCm5hpTukZ9M2cIYeLTQPWoCwW5NRS.boZH5jPa', 'teacher', 'TEACHER', null, 'Ann', 'Pinkerton'),
       ('student2', '$2a$10$M2Tyh1FN6UI7/JCCm5hpTukZ9M2cIYeLTQPWoCwW5NRS.boZH5jPa', 'student', 'STUDENT', 1, 'Scott', 'Swann'),
       ('teacher3', '$2a$10$M2Tyh1FN6UI7/JCCm5hpTukZ9M2cIYeLTQPWoCwW5NRS.boZH5jPa', 'teacher', 'TEACHER', null, 'Yudong', 'Tang'),
       ('teacher7', '$2a$10$M2Tyh1FN6UI7/JCCm5hpTukZ9M2cIYeLTQPWoCwW5NRS.boZH5jPa', 'teacher', 'TEACHER', null, 'Makama', 'Roy');
       
INSERT INTO timetables (lesson_num, date_ref, room_ref, teacher_ref, course_ref, group_ref)
VALUES (6, '1922-02-02', 1, 4, 1, 1);
       