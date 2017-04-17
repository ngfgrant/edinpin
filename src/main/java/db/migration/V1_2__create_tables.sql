CREATE TABLE `edinpin`.`address` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `address` VARCHAR(255) NULL COMMENT '',
  `city` VARCHAR(255) NULL COMMENT '',
  `postcode` VARCHAR(255) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '');

CREATE TABLE `edinpin`.`resource` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `name` VARCHAR(255) NULL COMMENT '',
  `type` VARCHAR(255) NULL COMMENT '',
  `addressId` INT NULL COMMENT '',
  `openingId` INT NULL COMMENT '',
  `information` BLOB NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '');

CREATE TABLE `edinpin`.`opening` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '',
  `openingTime` VARCHAR(45) NULL COMMENT '',
  `closingTime` VARCHAR(45) NULL COMMENT '',
  PRIMARY KEY (`id`)  COMMENT '');

