INSERT INTO timetables (id, room_ref, course_ref, group_ref, teacher_ref, date_time)
VALUES (1, 1, 100, 1, 1, '2023-06-22 19:10:25-07'),
       (2, 2, 101, 2, 2, '2023-06-22 19:10:25-07'),
       (3, 3, 102, 3, 4, '2023-06-22 19:10:25-07'),
       (4, 4, 103, 4, 4, '2023-06-22 19:10:25-07'),
       (5, 5, 104, 5, 5,'2023-06-22 19:10:25-07');
       
INSERT INTO users (id, login, password)
VALUES (1, 'Uzumumw', '1234'),
       (2, 'Uzumumw', '4323'),
       (3, 'Uzumumw', '4432'),
       (4, 'Uzumumw', '4312'),
       (5, 'Uzumumw', '1564');

INSERT INTO rooms (id, name)
VALUES (1, 'Lecture hall #1'),
       (2, 'Lecture hall #5'),
       (3, 'Lecture hall #6'),
       (4, 'Lecture hall #9'),
       (5, 'Lecture hall #13');
       
INSERT INTO teachers (id, first_name, last_name)
VALUES (1, 'Larry', 'Will'),
       (2, 'Vermont', 'Roberts'),
       (3, 'Emiliano', 'Bryant'),
       (4, 'Vinson', 'Collins'),
       (5, 'Roberto', 'Murphy');

INSERT INTO students (id, first_name, last_name)
VALUES (1, 'Henry', 'Grown'),
       (2, 'Alex', 'Barlowe'),
       (3, 'Jon', 'Caddel'),
       (4, 'Green', 'Hart'),
       (5, 'Ken', 'Block');
       
INSERT INTO groups (id, name)
VALUES (1, 'AA-01'),
       (2, 'AA-02'),
       (3, 'AA-03'),
       (4, 'AA-04'),
       (5, 'AA-05');

INSERT INTO courses (id, name)
VALUES (100, 'Math'),
       (101, 'Biology'),
       (102, 'Geography'),
       (103, 'Music'),
       (104, 'History'),
       (105, 'Physics'),
       (106, 'Medicine');

INSERT INTO teachers_courses (teacher_ref, course_ref)
VALUES (1, 100),
       (1, 101),
       (1, 103),
       (2, 100),
       (2, 102),
       (3, 101),
       (4, 103);

INSERT INTO courses_groups (group_ref, course_ref)
VALUES (1, 100),
       (1, 101),
       (2, 100),
       (2, 102),
       (3, 101);