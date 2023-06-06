INSERT INTO courses (id, name, description)
VALUES (100, 'Math', 'Math Description'),
       (101, 'Biology', 'Biology Description'),
       (102, 'Geography', 'Geography Description'),
       (103, 'Music', 'Music Description'),
       (104, 'History', 'History Description'),
       (105, 'Physics', 'Physics Description'),
       (106, 'Medicine', 'Medicine Description');

INSERT INTO groups (id, name)
VALUES (1, 'AA-01'),
       (2, 'AA-02'),
       (3, 'AA-03'),
       (4, 'AA-04'),
       (5, 'AA-05');

INSERT INTO users (id, login, password, role_type, role, group_ref, first_name, last_name)
VALUES (1, 'staff', '1234', 'user', 'STAFF', null, 'Cleaning', 'Lady'),
       (2, 'student1', '1234', 'student', 'STUDENT', 1, 'Cleaning', 'Lady'),
       (3, 'teacher1', '1234', 'teacher', 'TEACHER', null, 'Cleaning', 'Lady'),
       (4, 'student2', '1234', 'student', 'STUDENT', 2, 'Cleaning', 'Lady'),
       (5, 'teacher3', '1234', 'teacher', 'TEACHER', null, 'Cleaning', 'Lady');




INSERT INTO courses_groups (group_ref, course_ref)
VALUES (1, 100),
       (1, 101),
       (2, 102),
       (2, 103),
       (3, 104);

-- INSERT INTO timetables (id, room_ref, course_ref, group_ref, teacher_ref, date_time)
-- VALUES (1, 1, 100, 1, 1, '2023-06-22 19:10:25-07'),
--        (2, 2, 101, 2, 2, '2023-06-22 19:10:25-07'),
--        (3, 3, 102, 3, 4, '2023-06-22 19:10:25-07'),
--        (4, 4, 103, 4, 4, '2023-06-22 19:10:25-07'),
--        (5, 5, 104, 5, 5,'2023-06-22 19:10:25-07');
--
--
-- INSERT INTO rooms (id, name)
-- VALUES (1, 'Lecture hall #1'),
--        (2, 'Lecture hall #5'),
--        (3, 'Lecture hall #6'),
--        (4, 'Lecture hall #9'),
--        (5, 'Lecture hall #13');
--
-- INSERT INTO teachers (id, first_name, last_name)
-- VALUES (1, 'Larry', 'Will'),
--        (2, 'Vermont', 'Roberts'),
--        (3, 'Emiliano', 'Bryant'),
--        (4, 'Vinson', 'Collins'),
--        (5, 'Roberto', 'Murphy');
--
-- INSERT INTO students (id, group_ref, first_name, last_name)
-- VALUES (1, 5, 'Henry', 'Grown'),
--        (2, 4, 'Alex', 'Barlowe'),
--        (3, 3, 'Jon', 'Caddel'),
--        (4, 2, 'Green', 'Hart'),
--        (5, 1, 'Ken', 'Block');
--
-- INSERT INTO groups (id, name)
-- VALUES (1, 'AA-01'),
--        (2, 'AA-02'),
--        (3, 'AA-03'),
--        (4, 'AA-04'),
--        (5, 'AA-05');
--
--
-- INSERT INTO teachers_courses (teacher_ref, course_ref)
-- VALUES (1, 100),
--        (1, 101),
--        (1, 103),
--        (2, 100),
--        (2, 102),
--        (3, 101),
--        (4, 103);
--
-- INSERT INTO courses_groups (group_ref, course_ref)
-- VALUES (1, 100),
--        (1, 101),
--        (2, 100),
--        (2, 102),
--        (3, 101);