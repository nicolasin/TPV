-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 12-04-2018 a las 15:18:32
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `tpv`
--
CREATE DATABASE IF NOT EXISTS `tpv` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish_ci;
USE `tpv`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Alergenos`
--

CREATE TABLE IF NOT EXISTS `Alergenos` (
`id` int(10) unsigned NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Alergenos`
--

INSERT INTO `Alergenos` (`id`, `nombre`) VALUES
(1, 'Lacteo'),
(2, 'Huevo'),
(3, 'Crustaceo'),
(4, 'Pescado'),
(5, 'Frutos Secos'),
(6, 'Cacahuete'),
(7, 'Trigo'),
(8, 'Soja');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Categorias`
--

CREATE TABLE IF NOT EXISTS `Categorias` (
`id` int(20) unsigned NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Categorias`
--

INSERT INTO `Categorias` (`id`, `nombre`) VALUES
(1, 'Comidas'),
(2, 'Bebidas'),
(3, 'Con Alcohol'),
(4, 'Sin Alcohol'),
(5, 'Snack'),
(6, 'Cocktel'),
(7, 'Cafe'),
(8, 'Vino'),
(9, 'Cerveza'),
(10, 'Picante');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `CategoriasProductos`
--

CREATE TABLE IF NOT EXISTS `CategoriasProductos` (
  `idProducto` bigint(20) NOT NULL,
  `idCategoria` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Componentes`
--

CREATE TABLE IF NOT EXISTS `Componentes` (
`id` int(10) unsigned NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci,
  `stock` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Componentes`
--

INSERT INTO `Componentes` (`id`, `nombre`, `descripcion`, `stock`) VALUES
(1, 'Huevo', 'Huevos de gallina', 10),
(2, 'Mayonesa 1L', 'mayonesa realizada con leche', 15),
(3, 'Frutos Secos 1Kg', 'Paquete de comestibels para tomar con als copas', 3),
(4, 'Atún', 'pescado azul rico rico', 27),
(5, 'Pan Bimbo', 'pan bimbo suave para sandwisch', 200),
(11, 'Patata Asadas', 'Pues estas patatas asadas estan ricas d e cojones', 200);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ComponentesAlergenos`
--

CREATE TABLE IF NOT EXISTS `ComponentesAlergenos` (
  `idComponentes` bigint(20) NOT NULL,
  `idAlergenos` bigint(20) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ComponentesProductos`
--

CREATE TABLE IF NOT EXISTS `ComponentesProductos` (
`id` int(10) unsigned NOT NULL,
  `idProducto` int(10) unsigned NOT NULL,
  `idComponente` int(10) unsigned NOT NULL,
  `cantidad` double NOT NULL DEFAULT '0'
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `ComponentesProductos`
--

INSERT INTO `ComponentesProductos` (`id`, `idProducto`, `idComponente`, `cantidad`) VALUES
(1, 1, 1, 1),
(2, 1, 4, 0.1),
(3, 1, 2, 0.1),
(4, 2, 1, 4),
(5, 3, 1, 1),
(6, 3, 2, 0.1),
(7, 3, 4, 0.1),
(8, 3, 5, 2),
(12, 2, 11, 0.5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Descuentos`
--

CREATE TABLE IF NOT EXISTS `Descuentos` (
`id` int(10) unsigned NOT NULL,
  `nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL,
  `porcentaje` double NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Descuentos`
--

INSERT INTO `Descuentos` (`id`, `nombre`, `porcentaje`, `descripcion`) VALUES
(1, 'Sin Descuento', 0, 'Ningún Tipo de Descuento'),
(2, 'Direccion', 100, 'Directores y Subdirectores del Hotel'),
(3, 'Administracion', 50, 'Trabajadores de la seccion administracion'),
(4, 'Clientes MP', 25, 'Clientes que están alojados como media pension'),
(5, 'Propietarios', 100, 'Para los propietarios de la cadena'),
(6, 'Regalo', 100, 'Regalo o presenta hacia algún cliente');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FormasDePago`
--

CREATE TABLE IF NOT EXISTS `FormasDePago` (
`id` int(10) unsigned NOT NULL,
  `Nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `FormasDePago`
--

INSERT INTO `FormasDePago` (`id`, `Nombre`) VALUES
(1, 'VISA'),
(2, 'AMERICAN_EXPRESS'),
(3, 'PAYPAL'),
(4, 'BITCOINS'),
(5, 'CASH');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `hibernate_sequence`
--

CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(21),
(21);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Mesas`
--

CREATE TABLE IF NOT EXISTS `Mesas` (
`id` int(10) unsigned NOT NULL,
  `nombre` varchar(20) COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Mesas`
--

INSERT INTO `Mesas` (`id`, `nombre`) VALUES
(1, 'BARRA1'),
(2, 'BARRA2'),
(3, 'BARRA3'),
(4, 'BARRA4'),
(5, 'BARRA5'),
(6, 'BARRA6'),
(7, 'MESA1'),
(8, 'MESA2'),
(9, 'MESA3'),
(10, 'MESA4'),
(11, 'MESA5'),
(12, 'MESA6'),
(13, 'MESA7'),
(14, 'MESA8');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Pedidos`
--

CREATE TABLE IF NOT EXISTS `Pedidos` (
`id` int(10) unsigned NOT NULL,
  `fecha` date NOT NULL,
  `total` double NOT NULL,
  `idFormaPago` int(10) unsigned DEFAULT NULL,
  `idDescuento` int(10) unsigned NOT NULL,
  `idMesa` int(10) unsigned DEFAULT NULL,
  `pagado` tinyint(1) NOT NULL,
  `descripcion` text COLLATE utf8_spanish_ci
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Pedidos`
--

INSERT INTO `Pedidos` (`id`, `fecha`, `total`, `idFormaPago`, `idDescuento`, `idMesa`, `pagado`, `descripcion`) VALUES
(7, '2018-04-10', 14.5, 1, 3, 1, 0, 'La visa fallaba y no se le pudo hacer el cargo, pendiente de cobro al cliente Javier Martinez Soria'),
(16, '2018-04-11', 38, 1, 3, 11, 1, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PedidosProductos`
--

CREATE TABLE IF NOT EXISTS `PedidosProductos` (
`id` int(10) unsigned NOT NULL,
  `idProducto` int(10) unsigned NOT NULL,
  `idPedido` int(10) unsigned NOT NULL,
  `Cantidad` int(10) unsigned NOT NULL DEFAULT '1'
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `PedidosProductos`
--

INSERT INTO `PedidosProductos` (`id`, `idProducto`, `idPedido`, `Cantidad`) VALUES
(1, 1, 7, 3),
(2, 2, 7, 1),
(3, 3, 7, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Productos`
--

CREATE TABLE IF NOT EXISTS `Productos` (
`id` int(10) unsigned NOT NULL,
  `nombre` varchar(30) COLLATE utf8_spanish_ci NOT NULL,
  `precio` double NOT NULL DEFAULT '0',
  `descripcion` text COLLATE utf8_spanish_ci
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `Productos`
--

INSERT INTO `Productos` (`id`, `nombre`, `precio`, `descripcion`) VALUES
(1, 'Huevo Relleno', 3.5, 'Huevos con Mayonesa y atun'),
(2, 'Tortilla de Patata', 10, 'Tortilla de patatas con huevo y patatas asadas'),
(3, 'Sandwisch de Atun', 8, 'Sandwisc de atun y mayonesa');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Alergenos`
--
ALTER TABLE `Alergenos`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Categorias`
--
ALTER TABLE `Categorias`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `CategoriasProductos`
--
ALTER TABLE `CategoriasProductos`
 ADD PRIMARY KEY (`idProducto`,`idCategoria`), ADD KEY `FKb7iy6hfiv3q5alrujcray1ve7` (`idCategoria`);

--
-- Indices de la tabla `Componentes`
--
ALTER TABLE `Componentes`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ComponentesAlergenos`
--
ALTER TABLE `ComponentesAlergenos`
 ADD PRIMARY KEY (`idComponentes`,`idAlergenos`), ADD KEY `FKmx73xhhh8c1tkrnjvt8rlt6ls` (`idAlergenos`);

--
-- Indices de la tabla `ComponentesProductos`
--
ALTER TABLE `ComponentesProductos`
 ADD PRIMARY KEY (`id`), ADD KEY `idProducto` (`idProducto`,`idComponente`), ADD KEY `FKdjfeysk6bgehwscfnvt1kkndv` (`idComponente`);

--
-- Indices de la tabla `Descuentos`
--
ALTER TABLE `Descuentos`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `FormasDePago`
--
ALTER TABLE `FormasDePago`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Mesas`
--
ALTER TABLE `Mesas`
 ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `Pedidos`
--
ALTER TABLE `Pedidos`
 ADD PRIMARY KEY (`id`), ADD KEY `idFormaPago` (`idFormaPago`,`idMesa`), ADD KEY `idDescuento` (`idDescuento`), ADD KEY `FK1wai7xpkq9q2ngeppr8c58edp` (`idMesa`);

--
-- Indices de la tabla `PedidosProductos`
--
ALTER TABLE `PedidosProductos`
 ADD PRIMARY KEY (`id`), ADD KEY `idProducto` (`idProducto`,`idPedido`), ADD KEY `FKblu1ayk5gbgv4al9jnh5c7606` (`idPedido`);

--
-- Indices de la tabla `Productos`
--
ALTER TABLE `Productos`
 ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Alergenos`
--
ALTER TABLE `Alergenos`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=21;
--
-- AUTO_INCREMENT de la tabla `Categorias`
--
ALTER TABLE `Categorias`
MODIFY `id` int(20) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `Componentes`
--
ALTER TABLE `Componentes`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT de la tabla `ComponentesProductos`
--
ALTER TABLE `ComponentesProductos`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT de la tabla `Descuentos`
--
ALTER TABLE `Descuentos`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT de la tabla `FormasDePago`
--
ALTER TABLE `FormasDePago`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `Mesas`
--
ALTER TABLE `Mesas`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- AUTO_INCREMENT de la tabla `Pedidos`
--
ALTER TABLE `Pedidos`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT de la tabla `PedidosProductos`
--
ALTER TABLE `PedidosProductos`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `Productos`
--
ALTER TABLE `Productos`
MODIFY `id` int(10) unsigned NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `ComponentesProductos`
--
ALTER TABLE `ComponentesProductos`
ADD CONSTRAINT `FK8nfyi92sjhdlwnwt1qwiioype` FOREIGN KEY (`idProducto`) REFERENCES `Productos` (`id`),
ADD CONSTRAINT `FKdjfeysk6bgehwscfnvt1kkndv` FOREIGN KEY (`idComponente`) REFERENCES `Componentes` (`id`);

--
-- Filtros para la tabla `Pedidos`
--
ALTER TABLE `Pedidos`
ADD CONSTRAINT `FK1wai7xpkq9q2ngeppr8c58edp` FOREIGN KEY (`idMesa`) REFERENCES `Mesas` (`id`),
ADD CONSTRAINT `FK2te6vom355m65ynylkauuche2` FOREIGN KEY (`idFormaPago`) REFERENCES `FormasdePago` (`id`),
ADD CONSTRAINT `FK36af0hu1ekemxu9bi95ot5ey4` FOREIGN KEY (`idFormaPago`) REFERENCES `FormasDePago` (`id`),
ADD CONSTRAINT `FK3bunmjejxjg531542epg8ykqy` FOREIGN KEY (`idDescuento`) REFERENCES `Descuentos` (`id`);

--
-- Filtros para la tabla `PedidosProductos`
--
ALTER TABLE `PedidosProductos`
ADD CONSTRAINT `FK7gp3k7fb6nyxjhlm1pwnr5wa3` FOREIGN KEY (`idProducto`) REFERENCES `Productos` (`id`),
ADD CONSTRAINT `FKblu1ayk5gbgv4al9jnh5c7606` FOREIGN KEY (`idPedido`) REFERENCES `Pedidos` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
