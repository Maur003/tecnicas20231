/*
Created: 5;19;2008
Modified: 5;23;2008
Model: Oracle 10g
Database: Oracle 10g
*/

-- Create tables section -------------------------------------------------

-- Table CLIENTES

CREATE TABLE CLIENTES(
  CLI_ID numeric(10,0) NOT NULL,
  TDOC_CODIGO numeric(10,0) NOT NULL,
  CLI_NOMBRE Varchar(50) NOT NULL,
  CLI_DIRECCION Varchar(50) NOT NULL,
  CLI_TELEFONO Varchar(50) NOT NULL,
  CLI_MAIL Varchar(50)
)
;

-- Add keys for table CLIENTES

ALTER TABLE CLIENTES ADD CONSTRAINT CLI_ID PRIMARY KEY (CLI_ID)
;

-- Table CUENTAS

CREATE TABLE CUENTAS(
  CUE_NUMERO Varchar(30) NOT NULL,
  CLI_ID numeric(10,0) NOT NULL,
  CUE_SALDO numeric(10,2) NOT NULL,
  CUE_ACTIVA Char(2) NOT NULL,
  CUE_CLAVE Varchar(50) NOT NULL
)
;

-- Add keys for table CUENTAS

ALTER TABLE CUENTAS ADD CONSTRAINT CUE_NUMERO PRIMARY KEY (CUE_NUMERO)
;

-- Table TIPOS_DOCUMENTOS

CREATE TABLE TIPOS_DOCUMENTOS(
  TDOC_CODIGO numeric(10,0) NOT NULL,
  TDOC_NOMBRE Varchar(50) NOT NULL
)
;

-- Add keys for table TIPOS_DOCUMENTOS

ALTER TABLE TIPOS_DOCUMENTOS ADD CONSTRAINT TDOC_CODIGO PRIMARY KEY (TDOC_CODIGO)
;

-- Table CONSIGNACIONES

CREATE TABLE CONSIGNACIONES(
  CON_CODIGO numeric(10,0),
  CUE_NUMERO Varchar(30) NOT NULL,
  USU_CEDULA numeric(10,0),
  CON_VALOR numeric(10,2) NOT NULL,
  CON_FECHA Timestamp(6) NOT NULL,
  CON_DESCRIPCION Varchar(50) NOT NULL
)
;

-- Add keys for table CONSIGNACIONES

ALTER TABLE CONSIGNACIONES ADD CONSTRAINT CON_CODIGO PRIMARY KEY (CON_CODIGO,CUE_NUMERO)
;

-- Table RETIROS

CREATE TABLE RETIROS(
  RET_CODIGO numeric(10,0) NOT NULL,
  CUE_NUMERO Varchar(30) NOT NULL,
  USU_CEDULA numeric(10,0),
  RET_VALOR numeric(10,2) NOT NULL,
  RET_FECHA Timestamp(6) NOT NULL,
  RET_DESCRIPCION Varchar(50) NOT NULL
)
;

-- Add keys for table RETIROS

ALTER TABLE RETIROS ADD CONSTRAINT RET_CODIGO PRIMARY KEY (RET_CODIGO,CUE_NUMERO)
;

-- Table USUARIOS

CREATE TABLE USUARIOS(
  USU_CEDULA numeric(10,0) NOT NULL,
  TUSU_CODIGO numeric(10,0),
  USU_NOMBRE Varchar(50) NOT NULL,
  USU_LOGIN Varchar(30) NOT NULL,
  USU_CLAVE Varchar(50) NOT NULL
)
;

-- Add keys for table USUARIOS

ALTER TABLE USUARIOS ADD CONSTRAINT USU_CEDULA PRIMARY KEY (USU_CEDULA)
;

-- Table TIPOS_USUARIOS

CREATE TABLE TIPOS_USUARIOS(
  TUSU_CODIGO numeric(10,0) NOT NULL,
  TUSU_NOMBRE Varchar(50) NOT NULL
)
;

-- Add keys for table TIPOS_USUARIOS

ALTER TABLE TIPOS_USUARIOS ADD CONSTRAINT TUSU_CODIGO PRIMARY KEY (TUSU_CODIGO)
;

-- Create relationships section ------------------------------------------------- 

ALTER TABLE CLIENTES ADD CONSTRAINT CLIENTE_TIPO_DOC FOREIGN KEY (TDOC_CODIGO) REFERENCES TIPOS_DOCUMENTOS (TDOC_CODIGO)
;

ALTER TABLE RETIROS ADD CONSTRAINT CUENTA_RETIRO FOREIGN KEY (CUE_NUMERO) REFERENCES CUENTAS (CUE_NUMERO)
;

ALTER TABLE CONSIGNACIONES ADD CONSTRAINT CUENTA_CONSIGNACION FOREIGN KEY (CUE_NUMERO) REFERENCES CUENTAS (CUE_NUMERO)
;

ALTER TABLE CONSIGNACIONES ADD CONSTRAINT USUARIO_CONSIGNACION FOREIGN KEY (USU_CEDULA) REFERENCES USUARIOS (USU_CEDULA)
;

ALTER TABLE RETIROS ADD CONSTRAINT USUARIO_RETIRO FOREIGN KEY (USU_CEDULA) REFERENCES USUARIOS (USU_CEDULA)
;

ALTER TABLE CUENTAS ADD CONSTRAINT CUENTA_CLIENTE FOREIGN KEY (CLI_ID) REFERENCES CLIENTES (CLI_ID)
;

ALTER TABLE USUARIOS ADD CONSTRAINT TUSU_USUARIO FOREIGN KEY (TUSU_CODIGO) REFERENCES TIPOS_USUARIOS (TUSU_CODIGO)
;