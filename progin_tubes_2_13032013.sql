SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `progin` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `progin` ;

-- -----------------------------------------------------
-- Table `progin`.`accounts`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progin`.`accounts` (
  `idaccounts` INT NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(30) NULL ,
  `password` VARCHAR(255) NULL ,
  `email` VARCHAR(30) NULL ,
  `nama_lengkap` VARCHAR(30) NULL ,
  `tgl_lahir` DATE NULL ,
  `avatar` VARCHAR(255) NULL ,
  PRIMARY KEY (`idaccounts`) ,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `progin`.`kategori`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progin`.`kategori` (
  `idkategori` INT NOT NULL AUTO_INCREMENT ,
  `nama` VARCHAR(30) NULL ,
  PRIMARY KEY (`idkategori`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `progin`.`tugas`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progin`.`tugas` (
  `idtugas` INT NOT NULL AUTO_INCREMENT ,
  `attachment` VARCHAR(30) NULL ,
  `deadline` DATE NULL ,
  `status_selesai` TINYINT(1) NULL ,
  `kategori_idkategori` INT NOT NULL ,
  PRIMARY KEY (`idtugas`) ,
  INDEX `fk_tugas_kategori1` (`kategori_idkategori` ASC) ,
  CONSTRAINT `fk_tugas_kategori1`
    FOREIGN KEY (`kategori_idkategori` )
    REFERENCES `progin`.`kategori` (`idkategori` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `progin`.`komentar`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progin`.`komentar` (
  `idkomentar` INT NOT NULL AUTO_INCREMENT ,
  `isi` VARCHAR(511) NULL ,
  `tugas_idtugas` INT NOT NULL ,
  `accounts_idaccounts` INT NOT NULL ,
  `CREATED` DATETIME NULL ,
  PRIMARY KEY (`idkomentar`) ,
  INDEX `fk_komentar_tugas1` (`tugas_idtugas` ASC) ,
  INDEX `fk_komentar_accounts1` (`accounts_idaccounts` ASC) ,
  CONSTRAINT `fk_komentar_tugas1`
    FOREIGN KEY (`tugas_idtugas` )
    REFERENCES `progin`.`tugas` (`idtugas` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_komentar_accounts1`
    FOREIGN KEY (`accounts_idaccounts` )
    REFERENCES `progin`.`accounts` (`idaccounts` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `progin`.`tag`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progin`.`tag` (
  `idtag` INT NOT NULL AUTO_INCREMENT ,
  `nama` VARCHAR(30) NULL ,
  PRIMARY KEY (`idtag`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `progin`.`tugas_has_tag`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progin`.`tugas_has_tag` (
  `tugas_idtugas` INT NOT NULL ,
  `tag_idtag` INT NOT NULL ,
  PRIMARY KEY (`tugas_idtugas`, `tag_idtag`) ,
  INDEX `fk_tugas_has_tag_tag1` (`tag_idtag` ASC) ,
  INDEX `fk_tugas_has_tag_tugas1` (`tugas_idtugas` ASC) ,
  CONSTRAINT `fk_tugas_has_tag_tugas1`
    FOREIGN KEY (`tugas_idtugas` )
    REFERENCES `progin`.`tugas` (`idtugas` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tugas_has_tag_tag1`
    FOREIGN KEY (`tag_idtag` )
    REFERENCES `progin`.`tag` (`idtag` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `progin`.`accounts_has_tugas`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `progin`.`accounts_has_tugas` (
  `accounts_idaccounts` INT NOT NULL ,
  `tugas_idtugas` INT NOT NULL ,
  `pembuat` TINYINT(1) NULL ,
  PRIMARY KEY (`accounts_idaccounts`, `tugas_idtugas`) ,
  INDEX `fk_accounts_has_tugas_tugas1` (`tugas_idtugas` ASC) ,
  INDEX `fk_accounts_has_tugas_accounts1` (`accounts_idaccounts` ASC) ,
  CONSTRAINT `fk_accounts_has_tugas_accounts1`
    FOREIGN KEY (`accounts_idaccounts` )
    REFERENCES `progin`.`accounts` (`idaccounts` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_accounts_has_tugas_tugas1`
    FOREIGN KEY (`tugas_idtugas` )
    REFERENCES `progin`.`tugas` (`idtugas` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
