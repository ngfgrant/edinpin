ALTER TABLE `edinpin`.`resource`
DROP COLUMN `openingId`,
CHANGE COLUMN `addressId` `resourceTitle` VARCHAR(255) NULL DEFAULT NULL COMMENT '' AFTER `resourceType`,
CHANGE COLUMN `name` `resourceType` VARCHAR(255) NULL DEFAULT NULL COMMENT '' ,
CHANGE COLUMN `type` `companyName` VARCHAR(255) NULL DEFAULT NULL COMMENT '' ,
CHANGE COLUMN `information` `description` BLOB NULL DEFAULT NULL COMMENT '' ,
ADD COLUMN `managerName` VARCHAR(255) NULL COMMENT '' AFTER `companyName`,
ADD COLUMN `address` VARCHAR(255) NULL COMMENT '' AFTER `managerName`,
ADD COLUMN `postCode` VARCHAR(255) NULL COMMENT '' AFTER `address`,
ADD COLUMN `date` VARCHAR(255) NULL COMMENT '' AFTER `postCode`;