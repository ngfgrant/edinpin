ALTER TABLE `edinpin`.`resource`
ADD COLUMN `geocodingResult` BLOB NULL DEFAULT NULL COMMENT '' AFTER `description`;
ALTER TABLE `edinpin`.`resource`
CHANGE COLUMN `geocodingResult` `lat` VARCHAR(45) NULL DEFAULT NULL COMMENT '' ,
ADD COLUMN `lng` VARCHAR(45) NULL COMMENT '' AFTER `lat`;
