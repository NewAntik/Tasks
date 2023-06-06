CREATE SEQUENCE IF NOT EXISTS groups_id_seq;
CREATE SEQUENCE IF NOT EXISTS students_id_seq;
CREATE SEQUENCE IF NOT EXISTS courses_id_seq;
CREATE SEQUENCE IF NOT EXISTS timetables_id_seq;
CREATE SEQUENCE IF NOT EXISTS rooms_id_seq;
CREATE SEQUENCE IF NOT EXISTS teachers_id_seq;
CREATE SEQUENCE IF NOT EXISTS users_id_seq;

CREATE TABLE users (
    id BIGINT NOT NULL DEFAULT nextval('users_id_seq'),
    login VARCHAR (10),
    password VARCHAR (4),
    PRIMARY KEY (id)
);

ALTER SEQUENCE users_id_seq
    OWNED BY users.id;

CREATE TABLE students (
    id BIGINT NOT NULL DEFAULT nextval('students_id_seq') REFERENCES users (id),
    first_name VARCHAR (50),
    last_name VARCHAR (50),
    PRIMARY KEY (id)
);

ALTER SEQUENCE students_id_seq
    OWNED BY students.id;

CREATE TABLE teachers (
    id BIGINT NOT NULL DEFAULT nextval('teachers_id_seq') REFERENCES users (id),
    first_name VARCHAR (50),
    last_name VARCHAR (50),
    PRIMARY KEY (id)
);

ALTER SEQUENCE teachers_id_seq
    OWNED BY teachers.id;

CREATE TABLE rooms (
    id BIGINT NOT NULL DEFAULT nextval('rooms_id_seq'),
    name VARCHAR (20),
    PRIMARY KEY(id)
);

ALTER SEQUENCE rooms_id_seq
    OWNED BY rooms.id;

CREATE TABLE timetables (
    id BIGINT NOT NULL DEFAULT nextval('timetables_id_seq'),
    date_time TIMESTAMP WITH TIME ZONE NOT NULL,
    room_ref BIGINT NOT NULL,
    teacher_ref BIGINT NOT NULL,
    course_ref BIGINT NOT NULL,
    group_ref BIGINT NOT NULL ,
    PRIMARY KEY(id),
    UNIQUE (room_ref, teacher_ref, course_ref, group_ref)
);

ALTER SEQUENCE timetables_id_seq
    OWNED BY timetables.id;

CREATE TABLE courses (
    id BIGINT NOT NULL DEFAULT nextval('courses_id_seq'),
    name VARCHAR (50),
    description VARCHAR (300),
    PRIMARY KEY(id)
);

ALTER SEQUENCE courses_id_seq
    OWNED BY courses.id;

CREATE TABLE groups (
    id BIGINT NOT NULL DEFAULT nextval('groups_id_seq'),
    name VARCHAR (50),
    PRIMARY KEY(id)
);

ALTER SEQUENCE groups_id_seq
    OWNED BY groups.id;

CREATE TABLE teachers_courses (
    teacher_ref BIGINT REFERENCES teachers (id) ON DELETE CASCADE ON UPDATE CASCADE,
    course_ref BIGINT REFERENCES courses (id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (teacher_ref, course_ref)
);

CREATE TABLE courses_groups (
    group_ref BIGINT REFERENCES groups (id) ON DELETE CASCADE ON UPDATE CASCADE,
    course_ref BIGINT REFERENCES courses (id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (group_ref, course_ref)
);