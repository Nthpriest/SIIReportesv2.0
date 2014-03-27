SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `DBSIIReportes` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `DBSIIReportes` ;

-- -----------------------------------------------------
-- Table `DBSIIReportes`.`Equipos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBSIIReportes`.`Equipos` (
  `idEquipos` VARCHAR(15) NOT NULL,
  `area` VARCHAR(20) NULL,
  PRIMARY KEY (`idEquipos`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBSIIReportes`.`Empleados`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBSIIReportes`.`Empleados` (
  `idEmpleados` VARCHAR(15) NOT NULL,
  `nombre` VARCHAR(15) NULL,
  `aPaterno` VARCHAR(15) NULL,
  `aMaterno` VARCHAR(15) NULL,
  `direccion` VARCHAR(45) NULL,
  `telefono` VARCHAR(15) NULL,
  `email` VARCHAR(25) NULL,
  PRIMARY KEY (`idEmpleados`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `DBSIIReportes`.`Solicitudes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `DBSIIReportes`.`Solicitudes` (
  `folio` INT NOT NULL,
  `Equipo` VARCHAR(15) NULL,
  `Empleado` VARCHAR(15) NULL,
  `problema` VARCHAR(35) NULL,
  `observaciones` VARCHAR(100) NULL,
  `fecha` VARCHAR(8) NULL,
  PRIMARY KEY (`folio`),
  INDEX `idEquipo_idx` (`Equipo` ASC),
  INDEX `idEmpleado_idx` (`Empleado` ASC),
  CONSTRAINT `idEquipo`
    FOREIGN KEY (`Equipo`)
    REFERENCES `DBSIIReportes`.`Equipos` (`idEquipos`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idEmpleado`
    FOREIGN KEY (`Empleado`)
    REFERENCES `DBSIIReportes`.`Empleados` (`idEmpleados`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
