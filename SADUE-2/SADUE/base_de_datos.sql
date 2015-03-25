/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 60003
Source Host           : localhost:3306
Source Database       : base_de_datos

Target Server Type    : MYSQL
Target Server Version : 60003
File Encoding         : 65001

Date: 2015-03-18 10:46:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `apartados`
-- ----------------------------
DROP TABLE IF EXISTS `apartados`;
CREATE TABLE `apartados` (
  `apartadoId` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCliente` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellidoCliente` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `direccionCliente` varchar(60) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `telefono` int(11) DEFAULT NULL,
  `noTarjeta` int(11) DEFAULT NULL,
  `fecha` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `totalPagar` double DEFAULT NULL,
  `cuenta` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `deuda` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`apartadoId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- ----------------------------
-- Records of apartados
-- ----------------------------

-- ----------------------------
-- Table structure for `escuelas`
-- ----------------------------
DROP TABLE IF EXISTS `escuelas`;
CREATE TABLE `escuelas` (
  `escuelaId` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(60) COLLATE latin1_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`escuelaId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- ----------------------------
-- Records of escuelas
-- ----------------------------

-- ----------------------------
-- Table structure for `productos`
-- ----------------------------
DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `productoId` int(11) DEFAULT NULL,
  `nombre` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `talla` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `escuela` varchar(60) COLLATE latin1_spanish_ci DEFAULT NULL,
  `existencia` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- ----------------------------
-- Records of productos
-- ----------------------------

-- ----------------------------
-- Table structure for `productosapartados`
-- ----------------------------
DROP TABLE IF EXISTS `productosapartados`;
CREATE TABLE `productosapartados` (
  `apartadoId` int(11) DEFAULT NULL,
  `productoId` int(11) DEFAULT NULL,
  `nombre` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `talla` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `escuela` varchar(60) COLLATE latin1_spanish_ci DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- ----------------------------
-- Records of productosapartados
-- ----------------------------

-- ----------------------------
-- Table structure for `productosvendidos`
-- ----------------------------
DROP TABLE IF EXISTS `productosvendidos`;
CREATE TABLE `productosvendidos` (
  `productoId` int(11) DEFAULT NULL,
  `ventaId` int(11) DEFAULT NULL,
  `nombre` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `precio` double DEFAULT NULL,
  `talla` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `escuela` varchar(60) COLLATE latin1_spanish_ci DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- ----------------------------
-- Records of productosvendidos
-- ----------------------------

-- ----------------------------
-- Table structure for `usuarios`
-- ----------------------------
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `usuarioId` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `password` varchar(15) COLLATE latin1_spanish_ci DEFAULT NULL,
  `tipoUsuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`usuarioId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
INSERT INTO `usuarios` VALUES ('1', 'eva', '12345', '1');
INSERT INTO `usuarios` VALUES ('2', 'geral', '12345', '1');

-- ----------------------------
-- Table structure for `ventas`
-- ----------------------------
DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas` (
  `ventaId` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCliente` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellidoClient` varchar(40) COLLATE latin1_spanish_ci DEFAULT NULL,
  `fechaInicio` varchar(20) COLLATE latin1_spanish_ci DEFAULT NULL,
  `tipoPago` int(11) DEFAULT NULL,
  `pagoTotal` double DEFAULT NULL,
  PRIMARY KEY (`ventaId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- ----------------------------
-- Records of ventas
-- ----------------------------
