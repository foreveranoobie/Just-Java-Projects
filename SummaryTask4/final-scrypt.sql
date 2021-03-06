DROP DATABASE IF EXISTS finaltask;
CREATE SCHEMA `finaltask`;
SET time_zone='+02:00';
-- Creating roles
CREATE TABLE `finaltask`.`roles` (
  `id` INT UNSIGNED NOT NULL,
  `role_name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `role_name_UNIQUE` (`role_name` ASC) VISIBLE);
INSERT INTO `finaltask`.`roles` (`id`, `role_name`) VALUES ('1', 'admin');
INSERT INTO `finaltask`.`roles` (`id`, `role_name`) VALUES ('2', 'teacher');
INSERT INTO `finaltask`.`roles` (`id`, `role_name`) VALUES ('3', 'student');
-- Table for registered student on courses
CREATE TABLE `finaltask`.`student_courses` (
  `student_id` INT NULL,
  `course_id` INT UNSIGNED NULL,
   UNIQUE(student_id, course_id));
-- Table of requests to register teachers by admin
CREATE TABLE `finaltask`.`register_request` (
  `username` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
   UNIQUE(username, password));
-- Table of users list
CREATE TABLE `finaltask`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `role` INT NOT NULL,
   PRIMARY KEY (`id`),
   UNIQUE(id, login, password));
-- Table for courses description
CREATE TABLE `finaltask`.`courses` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `subject` VARCHAR(45) NOT NULL,
  `start` DATETIME NOT NULL,
  `end` DATETIME NOT NULL,
  `teacher_id` INT NOT NULL,
  UNIQUE(subject, teacher_id),
  PRIMARY KEY (`id`));
-- FK for cascade deletion in student_courses
ALTER TABLE `finaltask`.`student_courses` 
ADD CONSTRAINT `course_del`
  FOREIGN KEY (`course_id`)
  REFERENCES `finaltask`.`courses` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
-- FK for cascade deletion in courses
ALTER TABLE `finaltask`.`courses` 
ADD INDEX `teacher_idx` (`teacher_id` ASC) VISIBLE;
;
ALTER TABLE `finaltask`.`courses` 
ADD CONSTRAINT `teacher`
  FOREIGN KEY (`teacher_id`)
  REFERENCES `finaltask`.`users` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  CREATE TRIGGER `finaltask`.`student_courses_BEFORE_INSERT` BEFORE INSERT ON finaltask.student_courses FOR EACH ROW BEGIN
IF NOT EXISTS(SELECT * FROM finaltask.users WHERE users.id = new.student_id
AND users.role = 3)
THEN SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Student does not exist'            
END IF
delimiter ;
END
delimiter ;
-- Creating the journal
CREATE TABLE `finaltask`.`journal` (
  `course_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  `mark` INT UNSIGNED NOT NULL);
-- Table of blocked students
CREATE TABLE `finaltask`.`blocked` (
  `id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `student`
    FOREIGN KEY (`id`)
    REFERENCES `finaltask`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
-- Create trigger to avoid filling the journal with blocked students

insert into finaltask.users (id, login, password, role) VALUES 
(1, 'admin', 'admin',1), (2, 'teacher', 'teacher',2), (3, 'student', 'student', 3), (4, 'teacher22', '123', 2), (5, 'student2000', '123', 3), (6, 'student255', '123', 3), 
(7, 'student33', '12345', 3), (8, 'teacher3', 'qwerty', 2), (9, 'student256', 'asd', 3);

insert into finaltask.courses (id, subject, start, end, teacher_id) VALUES
(1, 'Programming', '2018-10-17', '2019-04-25', 2), (2, 'Python', '2017-02-10', '2017-10-10', 8), (3, 'Algorithms', '2020-01-15', '2020-05-28', 4),
(4, 'Java', '2019-10-07', '2020-03-14', 2), (5, 'Electronics', '2019-04-11', '2019-11-12', 8), (6, 'Дискретная Математика', '2019-02-11', '2019-11-10', 4),
(7, 'Cybersecurity', '2019-12-20', '2020-04-20', 2); (8, 'Blockchain', '2019-03-15', '2019-11-12', 4)

insert into finaltask.blocked (id) values (5);

insert into finaltask.student_courses (student_id, course_id) VALUES (9, 5), (7, 6), (3,4), (5, 3), (9, 3), (5, 6), (7,4), (9, 7), (3, 8),
(5, 8). (9, 8);

insert into finaltask.journal (course_id, student_id, mark) VALUES (1, 3, 5), (2, 5, 4), (1, 5, 5), (2, 3, 3), (2, 6, 4), (1, 6, 4), (1, 9, 5);
