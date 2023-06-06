CREATE SEQUENCE IF NOT EXISTS groups_id_seq;
CREATE SEQUENCE IF NOT EXISTS students_id_seq;
CREATE SEQUENCE IF NOT EXISTS courses_id_seq;
CREATE SEQUENCE IF NOT EXISTS timetables_id_seq;
CREATE SEQUENCE IF NOT EXISTS rooms_id_seq;
CREATE SEQUENCE IF NOT EXISTS teachers_id_seq;
CREATE SEQUENCE IF NOT EXISTS users_id_seq;

CREATE TABLE groups
(
    id   BIGINT NOT NULL DEFAULT nextval('groups_id_seq'),
    name VARCHAR(50),
    PRIMARY KEY (id)
);

CREATE TABLE users
(
    id         BIGINT      NOT NULL DEFAULT nextval('users_id_seq'),
    login      VARCHAR(10),
    password   VARCHAR(4),
    role_type  TEXT        NOT NULL,
    role       TEXT        NOT NULL,
    group_ref  BIGINT references groups (id),
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE rooms
(
    id   BIGINT NOT NULL DEFAULT nextval('rooms_id_seq'),
    name VARCHAR(20),
    PRIMARY KEY (id)
);

CREATE TABLE courses
(
    id          BIGINT NOT NULL DEFAULT nextval('courses_id_seq'),
    name        VARCHAR(50),
    description VARCHAR(300),
    PRIMARY KEY (id)
);

CREATE TABLE teachers_courses
(
    teacher_ref BIGINT REFERENCES users (id) ON DELETE CASCADE ON UPDATE CASCADE,
    course_ref  BIGINT REFERENCES courses (id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (teacher_ref, course_ref)
);

CREATE TABLE courses_groups
(
    group_ref  BIGINT REFERENCES groups (id) ON DELETE CASCADE ON UPDATE CASCADE,
    course_ref BIGINT REFERENCES courses (id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (group_ref, course_ref)
);

CREATE TABLE timetables
(
    id          BIGINT                   NOT NULL DEFAULT nextval('timetables_id_seq'),
    lesson_num  BIGINT                   not null,
    date        TIMESTAMP WITH TIME ZONE NOT NULL,
    room_ref    BIGINT                   NOT NULL references rooms (id) on DELETE cascade,
    teacher_ref BIGINT                   NOT NULL references users (id) on DELETE cascade,
    course_ref  BIGINT                   NOT NULL references courses (id) on DELETE cascade,
    group_ref   BIGINT                   NOT NULL references groups (id) on DELETE cascade,
    PRIMARY KEY (id),
    UNIQUE (date, lesson_num, room_ref),
    UNIQUE (date, lesson_num, teacher_ref),
    UNIQUE (date, lesson_num, course_ref),
    UNIQUE (date, lesson_num, group_ref)
);

