-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 18-11-2021 a las 00:56:14
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.4.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `acme`
--
CREATE DATABASE IF NOT EXISTS `ejemploclase` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `ejemploclase`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprasdetalles`
--

DROP TABLE IF EXISTS `comprasdetalles`;
CREATE TABLE `comprasdetalles` (
  `numerocompra` int(60) NOT NULL,
  `codigoproducto` int(60) NOT NULL,
  `cantidad` int(60) NOT NULL,
  `precio` float NOT NULL,
  `subtotal` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comprasencabezado`
--

DROP TABLE IF EXISTS `comprasencabezado`;
CREATE TABLE `comprasencabezado` (
  `numero` int(60) NOT NULL,
  `fecha` datetime NOT NULL,
  `total` int(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
COMMIT;

-- Estructura de tabla para la tabla `sequence`
--

CREATE TABLE `sequence` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `sequence`
--

INSERT INTO `sequence` (`id`) VALUES
(31);


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

DELIMITER $$


CREATE DEFINER=`root`@`localhost` PROCEDURE `gestionarCompras` (IN `p_total` INT, IN `p_datos` VARCHAR(100))  BEGIN
Declare identificador int;
Set AUTOCOMMIT = 0;    

START TRANSACTION;

select id into identificador from sequence for update;

insert into comprasencabezado values(identificador,sysdate(),p_total);

 select replace(p_datos,'-',identificador) into p_datos;
 call gestionarinserciondinamica('comprasdetalles',p_datos);

 Update sequence set id = id + 1;

COMMIT WORK;


END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `gestionarinserciondinamica` (IN `p_tabla` VARCHAR(100), IN `p_datos` VARCHAR(10000))  BEGIN

DECLARE i  INT;
DECLARE fila  VARCHAR(255);
Declare longitudFila int;

Set i = 1;

loop_filas:LOOP

	Select SPLIT_STR(p_datos, '@', i) into fila;
    select length(fila) into longitudFila;
    
     if (longitudFila = 0) THEN

        leave loop_filas;
        
      else  
    
    	SET @sentenciaSQL = CONCAT( "INSERT INTO ", p_tabla ," VALUES (", fila, ");" );
      PREPARE insertar FROM @sentenciaSQL;
      EXECUTE insertar;
    
    end if;
	
    set i = i + 1;
End loop;
End$$

--
-- Funciones
--
CREATE DEFINER=`root`@`localhost` FUNCTION `SPLIT_STR` (`x` VARCHAR(255), `delim` VARCHAR(12), `pos` INT) RETURNS VARCHAR(255) CHARSET utf8mb4 RETURN REPLACE(SUBSTRING(SUBSTRING_INDEX(x, delim, pos),
       LENGTH(SUBSTRING_INDEX(x, delim, pos -1)) + 1),
       delim, '')$$

DELIMITER ;