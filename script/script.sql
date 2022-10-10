-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema intcomex
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema intcomex
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `intcomex` ;
USE `intcomex` ;

-- -----------------------------------------------------
-- Table `intcomex`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intcomex`.`category` (
  `categoryId` INT NOT NULL AUTO_INCREMENT,
  `categoryName` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `description` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `picture` BLOB NULL DEFAULT NULL,
  PRIMARY KEY (`categoryId`),
  UNIQUE INDEX `UX_CATEGORY_NAME` (`categoryName` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6;


-- -----------------------------------------------------
-- Table `intcomex`.`customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intcomex`.`customer` (
  `customerId` INT NOT NULL AUTO_INCREMENT,
  `companyName` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `contactName` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `contactTitle` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `address` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `city` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `region` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `postalCode` INT NULL DEFAULT NULL,
  `country` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `phone` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `fax` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`customerId`),
  INDEX `IX_CUSTOMER_CITY` (`city` ASC) INVISIBLE,
  INDEX `IX_CUSTOMER_COMPANY` (`companyName` ASC) INVISIBLE,
  INDEX `IX_CUSTOMER_POSTALCODE` (`postalCode` ASC) INVISIBLE,
  INDEX `IX_CUSTOMER_REGION` (`region` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intcomex`.`employee`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intcomex`.`employee` (
  `employeeId` INT NOT NULL AUTO_INCREMENT,
  `lastName` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `firstName` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `title` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `titleOfCourtesy` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `birthDate` DATE NULL DEFAULT NULL,
  `hireDate` DATE NULL DEFAULT NULL,
  `address` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `city` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `region` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `postalCode` INT NULL DEFAULT NULL,
  `country` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `extension` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `photo` BINARY(1) NULL DEFAULT NULL,
  `reportsTo` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`employeeId`),
  INDEX `IX_EMPLOYEE_LASTNAME` (`lastName` ASC) VISIBLE,
  INDEX `IX_EMPLOYEE_POSTALCODE` (`postalCode` ASC) INVISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intcomex`.`shipper`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intcomex`.`shipper` (
  `shipperId` INT NOT NULL AUTO_INCREMENT,
  `companyName` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `phone` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`shipperId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `intcomex`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intcomex`.`order` (
  `orderId` BIGINT NOT NULL AUTO_INCREMENT,
  `customerId` INT NULL DEFAULT NULL,
  `employeeId` INT NULL DEFAULT NULL,
  `orderDate` DATE NULL DEFAULT NULL,
  `requiredDate` DATE NULL DEFAULT NULL,
  `shippedDate` DATE NULL DEFAULT NULL,
  `shipVia` INT NULL DEFAULT NULL,
  `freight` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `shipName` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `shipAddress` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `shipCity` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `shipRegion` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `shipPostalcode` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `shipCountry` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  INDEX `FK_CUSTOMER_ID` (`customerId` ASC) VISIBLE,
  INDEX `IX_ORDER_CUSTOMER` (`customerId` ASC) INVISIBLE,
  INDEX `FK_EMPLOYEE_ID` (`employeeId` ASC) VISIBLE,
  INDEX `IX_ORDER_EMPLOYEE` (`employeeId` ASC) INVISIBLE,
  INDEX `IX_ORDER_DATE` (`orderDate` ASC) INVISIBLE,
  INDEX `IX_ORDER_SHIPPED` (`shippedDate` ASC) INVISIBLE,
  INDEX `FK_SHIPPER_ID` (`shipVia` ASC) INVISIBLE,
  INDEX `IX_ORDER_SHIPPOSTALCODE` (`shipPostalcode` ASC) VISIBLE,
  CONSTRAINT `FK_CUSTOMER_ID`
    FOREIGN KEY (`customerId`)
    REFERENCES `intcomex`.`customer` (`customerId`),
  CONSTRAINT `FK_EMPLOYEE`
    FOREIGN KEY (`employeeId`)
    REFERENCES `intcomex`.`employee` (`employeeId`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `FK_SHIPPER`
    FOREIGN KEY (`shipVia`)
    REFERENCES `intcomex`.`shipper` (`shipperId`))
ENGINE = InnoDB
AUTO_INCREMENT = 10000000;


-- -----------------------------------------------------
-- Table `intcomex`.`supplier`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intcomex`.`supplier` (
  `supplierId` INT NOT NULL AUTO_INCREMENT,
  `companyName` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `contactName` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `contactTitle` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `address` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `city` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `region` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `postalCode` INT NULL DEFAULT NULL,
  `country` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `phone` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `fax` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `homePage` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  PRIMARY KEY (`supplierId`),
  INDEX `IX_SUPPLIER_COMPANY` (`companyName` ASC) INVISIBLE,
  INDEX `IX_SUPPLIER_POSTALCODE` (`postalCode` ASC) INVISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 2;


-- -----------------------------------------------------
-- Table `intcomex`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intcomex`.`product` (
  `productId` INT NOT NULL AUTO_INCREMENT,
  `productName` VARCHAR(45) COLLATE 'utf8mb3_spanish2_ci' NULL DEFAULT NULL,
  `supplierId` INT NULL DEFAULT NULL,
  `categoryId` INT NULL DEFAULT NULL,
  `quantityPerUnit` DECIMAL(5,2) NULL DEFAULT NULL,
  `unitsInStock` DECIMAL(5,2) NULL DEFAULT NULL,
  `unitPrice` DECIMAL(5,2) NULL DEFAULT NULL,
  `unitsOnOrder` DECIMAL(5,2) NULL DEFAULT NULL,
  `reorderLevel` INT NULL DEFAULT NULL,
  `discontinued` INT NULL DEFAULT NULL,
  PRIMARY KEY (`productId`),
  INDEX `IX_PRODUCT_NAME` (`productName` ASC) VISIBLE,
  INDEX `IX_CATEGORY_ID` (`categoryId` ASC) INVISIBLE,
  INDEX `IX_SUPPLIER_ID` (`supplierId` ASC) VISIBLE,
  CONSTRAINT `FK_CATEGORY_ID`
    FOREIGN KEY (`categoryId`)
    REFERENCES `intcomex`.`category` (`categoryId`),
  CONSTRAINT `FK_SUPPLIER_ID`
    FOREIGN KEY (`supplierId`)
    REFERENCES `intcomex`.`supplier` (`supplierId`))
ENGINE = InnoDB
AUTO_INCREMENT = 21015;


-- -----------------------------------------------------
-- Table `intcomex`.`orderdetail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `intcomex`.`orderdetail` (
  `orderId` BIGINT NOT NULL,
  `productId` INT NOT NULL,
  `unitPrice` DECIMAL(5,2) NULL DEFAULT NULL,
  `quantity` DECIMAL(5,3) NULL DEFAULT NULL,
  `discount` DECIMAL(5,2) NULL DEFAULT NULL,
  PRIMARY KEY (`orderId`, `productId`),
  INDEX `FK_ORDER_ID` (`orderId` ASC) INVISIBLE,
  INDEX `IX_ORDER_DETAIL_ORDER` (`orderId` ASC) INVISIBLE,
  INDEX `FK_PRODUCT_ID` (`productId` ASC) VISIBLE,
  INDEX `IX_ORDER_DETAIL_PRODUCT` (`productId` ASC) INVISIBLE,
  CONSTRAINT `FK_ORDER_ID`
    FOREIGN KEY (`orderId`)
    REFERENCES `intcomex`.`order` (`orderId`),
  CONSTRAINT `FK_PRODUCT_ID`
    FOREIGN KEY (`productId`)
    REFERENCES `intcomex`.`product` (`productId`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
