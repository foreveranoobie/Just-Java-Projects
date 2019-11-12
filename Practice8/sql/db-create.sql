CREATE DATABASE P8DB;
CREATE TABLE `P8DB`.`users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`));
  ALTER TABLE `P8DB`.`users` 
ADD COLUMN `login` VARCHAR(10) NULL AFTER `id`,
ADD UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE;
;
CREATE TABLE `P8DB`.`teams` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(10) NULL,
  PRIMARY KEY (`id`));
CREATE TABLE `P8DB`.`users_teams` (
  `user_id` INT NULL,
  `team_id` INT NULL,
  INDEX `user_idx` (`user_id` ASC) VISIBLE,
  INDEX `team_idx` (`team_id` ASC) VISIBLE,
  CONSTRAINT `user`
    FOREIGN KEY (`user_id`)
    REFERENCES `P8DB`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `team`
    FOREIGN KEY (`team_id`)
    REFERENCES `P8DB`.`teams` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
	UNIQUE (user_id, team_id));
INSERT INTO `P8DB`.`users` (login, id) VALUES ("ivanov", 1);
INSERT INTO `P8DB`.`teams` (name, id) VALUES ("teamA", 1);