CREATE DATABASE  IF NOT EXISTS `msvc_orden_trabajo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `msvc_orden_trabajo`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: msvc_orden_trabajo
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ordenes_bahias`
--

DROP TABLE IF EXISTS `ordenes_bahias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordenes_bahias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bahia_id` bigint NOT NULL,
  `estado_asignacion` varchar(255) DEFAULT NULL,
  `fecha_ingreso` datetime(6) DEFAULT NULL,
  `fecha_salida` datetime(6) DEFAULT NULL,
  `orden_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfjnbeuqoiyphgjiccksoyfeh9` (`orden_id`),
  CONSTRAINT `FKfjnbeuqoiyphgjiccksoyfeh9` FOREIGN KEY (`orden_id`) REFERENCES `ordenes_trabajo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordenes_bahias`
--

LOCK TABLES `ordenes_bahias` WRITE;
/*!40000 ALTER TABLE `ordenes_bahias` DISABLE KEYS */;
INSERT INTO `ordenes_bahias` VALUES (1,1,'HISTORICA','2026-10-03 22:59:36.037675','2026-10-03 22:59:53.848376',1);
/*!40000 ALTER TABLE `ordenes_bahias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordenes_trabajo`
--

DROP TABLE IF EXISTS `ordenes_trabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordenes_trabajo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cerrada` bit(1) NOT NULL,
  `cotizacion_id` bigint DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `tecnico_asignado_id` bigint DEFAULT NULL,
  `vehiculo_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordenes_trabajo`
--

LOCK TABLES `ordenes_trabajo` WRITE;
/*!40000 ALTER TABLE `ordenes_trabajo` DISABLE KEYS */;
INSERT INTO `ordenes_trabajo` VALUES (1,_binary '',1,'EN_PROGRESO',1,1);
/*!40000 ALTER TABLE `ordenes_trabajo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarea_reparacion_entity_evidencias`
--

DROP TABLE IF EXISTS `tarea_reparacion_entity_evidencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarea_reparacion_entity_evidencias` (
  `tarea_reparacion_entity_id` bigint NOT NULL,
  `fecha_captura` datetime(6) DEFAULT NULL,
  `url_foto` varchar(255) DEFAULT NULL,
  KEY `FKb5d5ng1pp9eopj1rhbxopx508` (`tarea_reparacion_entity_id`),
  CONSTRAINT `FKb5d5ng1pp9eopj1rhbxopx508` FOREIGN KEY (`tarea_reparacion_entity_id`) REFERENCES `tareas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarea_reparacion_entity_evidencias`
--

LOCK TABLES `tarea_reparacion_entity_evidencias` WRITE;
/*!40000 ALTER TABLE `tarea_reparacion_entity_evidencias` DISABLE KEYS */;
INSERT INTO `tarea_reparacion_entity_evidencias` VALUES (1,'2026-10-02 11:30:00.000000','http://miservidor.com/fotos/aceite_nuevo.jpg');
/*!40000 ALTER TABLE `tarea_reparacion_entity_evidencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tarea_reparacion_entity_repuestos_usados`
--

DROP TABLE IF EXISTS `tarea_reparacion_entity_repuestos_usados`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tarea_reparacion_entity_repuestos_usados` (
  `tarea_reparacion_entity_id` bigint NOT NULL,
  `cantidad_utilizada` int DEFAULT NULL,
  `repuesto_id` bigint DEFAULT NULL,
  KEY `FK27r02m7ydcdgodpwu6da5110f` (`tarea_reparacion_entity_id`),
  CONSTRAINT `FK27r02m7ydcdgodpwu6da5110f` FOREIGN KEY (`tarea_reparacion_entity_id`) REFERENCES `tareas` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tarea_reparacion_entity_repuestos_usados`
--

LOCK TABLES `tarea_reparacion_entity_repuestos_usados` WRITE;
/*!40000 ALTER TABLE `tarea_reparacion_entity_repuestos_usados` DISABLE KEYS */;
INSERT INTO `tarea_reparacion_entity_repuestos_usados` VALUES (1,1,99);
/*!40000 ALTER TABLE `tarea_reparacion_entity_repuestos_usados` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tareas`
--

DROP TABLE IF EXISTS `tareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tareas` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `completada` bit(1) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `orden_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgb4h2cet7ni9xcnevkubbukne` (`orden_id`),
  CONSTRAINT `FKgb4h2cet7ni9xcnevkubbukne` FOREIGN KEY (`orden_id`) REFERENCES `ordenes_trabajo` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tareas`
--

LOCK TABLES `tareas` WRITE;
/*!40000 ALTER TABLE `tareas` DISABLE KEYS */;
INSERT INTO `tareas` VALUES (1,_binary '','Cambio de aceite',1);
/*!40000 ALTER TABLE `tareas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-04  0:19:18
CREATE DATABASE  IF NOT EXISTS `msvc_bahia` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `msvc_bahia`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: msvc_bahia
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bahias`
--

DROP TABLE IF EXISTS `bahias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bahias` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `codigo` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bahias`
--

LOCK TABLES `bahias` WRITE;
/*!40000 ALTER TABLE `bahias` DISABLE KEYS */;
INSERT INTO `bahias` VALUES (1,'B-01','OCUPADA','DIAGNOSTICO'),(2,'B-01','DISPONIBLE','DIAGNOSTICO'),(3,'B-02','DISPONIBLE','ELEVADOR'),(4,'B-03','DISPONIBLE','PINTURA'),(5,'B-04','DISPONIBLE','MECANICA_GENERAL'),(6,'B-05','DISPONIBLE','ELEVADOR'),(7,'B-06','DISPONIBLE','DIAGNOSTICO'),(8,'B-07','DISPONIBLE','PINTURA'),(9,'B-08','DISPONIBLE','LAVADO'),(10,'B-09','DISPONIBLE','MECANICA_RAPIDA'),(11,'B-10','DISPONIBLE','ELEVADOR');
/*!40000 ALTER TABLE `bahias` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-04  0:19:18
CREATE DATABASE  IF NOT EXISTS `msvc_cotizacion` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `msvc_cotizacion`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: msvc_cotizacion
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cotizacion_entity_tareas`
--

DROP TABLE IF EXISTS `cotizacion_entity_tareas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cotizacion_entity_tareas` (
  `cotizacion_entity_id` bigint NOT NULL,
  `costo_estimado` double DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `tarea_id` bigint DEFAULT NULL,
  KEY `FK53evx4v6e325yxk0cjs1csvvl` (`cotizacion_entity_id`),
  CONSTRAINT `FK53evx4v6e325yxk0cjs1csvvl` FOREIGN KEY (`cotizacion_entity_id`) REFERENCES `cotizaciones` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizacion_entity_tareas`
--

LOCK TABLES `cotizacion_entity_tareas` WRITE;
/*!40000 ALTER TABLE `cotizacion_entity_tareas` DISABLE KEYS */;
INSERT INTO `cotizacion_entity_tareas` VALUES (1,150,'Cambio de aceite',1),(1,150,'Cambio de aceite y filtro',1),(2,200,'Pintura puerta izquierda',2),(2,100.5,'Pulido de faros',3),(3,80,'Revision de frenos',4),(4,450,'Cambio de kit de distribucion',5),(5,120,'Alineacion y balanceo',6),(6,600,'Reparacion de transmision',7),(7,220,'Cambio de amortiguadores delanteros',8),(8,340,'Mantenimiento preventivo mayor',9),(9,90,'Cambio de bujias',10),(10,510,'Reparacion de sistema de aire acondicionado',11);
/*!40000 ALTER TABLE `cotizacion_entity_tareas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cotizaciones`
--

DROP TABLE IF EXISTS `cotizaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cotizaciones` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `evidencia_url` varchar(255) DEFAULT NULL,
  `fecha_hora` datetime(6) DEFAULT NULL,
  `metodo_autorizacion` varchar(255) DEFAULT NULL,
  `dias_inactividad_estimados` int NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime(6) DEFAULT NULL,
  `total_estimado` double DEFAULT NULL,
  `vehiculo_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizaciones`
--

LOCK TABLES `cotizaciones` WRITE;
/*!40000 ALTER TABLE `cotizaciones` DISABLE KEYS */;
INSERT INTO `cotizaciones` VALUES (1,'http://servidor.com/firma-cliente.pdf','2026-10-02 10:00:00.000000','Firma Presencial',2,'APROBADA','2026-10-02 09:00:00.000000',150,1),(2,'http://firma.com/doc1.pdf','2026-10-01 10:00:00.000000','Firma Presencial',1,'APROBADA','2026-10-01 08:00:00.000000',150,1),(3,'http://firma.com/doc2.pdf','2026-10-02 11:15:00.000000','Firma Digital',2,'APROBADA','2026-10-02 09:30:00.000000',300.5,2),(4,'http://firma.com/doc3.pdf','2026-10-02 15:30:00.000000','Firma Presencial',1,'APROBADA','2026-10-02 14:00:00.000000',80,3),(5,'http://firma.com/doc4.pdf','2026-10-03 10:20:00.000000','Firma Digital',3,'APROBADA','2026-10-03 08:45:00.000000',450,4),(6,'http://firma.com/doc5.pdf','2026-10-03 12:00:00.000000','Firma Presencial',1,'APROBADA','2026-10-03 11:10:00.000000',120,5),(7,'http://firma.com/doc6.pdf','2026-10-04 09:00:00.000000','Firma Digital',4,'APROBADA','2026-10-04 07:30:00.000000',600,6),(8,'http://firma.com/doc7.pdf','2026-10-04 11:30:00.000000','Firma Presencial',2,'APROBADA','2026-10-04 10:00:00.000000',220,7),(9,'http://firma.com/doc8.pdf','2026-10-05 15:00:00.000000','Firma Digital',2,'APROBADA','2026-10-05 13:20:00.000000',340,8),(10,'http://firma.com/doc9.pdf','2026-10-05 17:15:00.000000','Firma Presencial',1,'APROBADA','2026-10-05 16:00:00.000000',90,9),(11,'http://firma.com/doc10.pdf','2026-10-06 09:45:00.000000','Firma Digital',3,'APROBADA','2026-10-06 08:00:00.000000',510,10);
/*!40000 ALTER TABLE `cotizaciones` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-04  0:19:18
CREATE DATABASE  IF NOT EXISTS `msvc_vehiculo` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `msvc_vehiculo`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: msvc_vehiculo
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `vehiculos`
--

DROP TABLE IF EXISTS `vehiculos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehiculos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cliente_id` bigint DEFAULT NULL,
  `kilometraje_valor` int DEFAULT NULL,
  `marca` varchar(255) DEFAULT NULL,
  `modelo` varchar(255) DEFAULT NULL,
  `placa_valor` varchar(255) DEFAULT NULL,
  `vin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKjw6uikh5gbgkysjcq5xspg169` (`placa_valor`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehiculos`
--

LOCK TABLES `vehiculos` WRITE;
/*!40000 ALTER TABLE `vehiculos` DISABLE KEYS */;
INSERT INTO `vehiculos` VALUES (1,1,45000,'Toyota','Corolla','ABC-123','VIN987654321'),(2,1,45000,'Toyota','Corolla','ABC-111','VIN000000001'),(3,2,60000,'Nissan','Sentra','XYZ-222','VIN000000002'),(4,3,32000,'Honda','Civic','DEF-333','VIN000000003'),(5,4,85000,'Ford','Fiesta','LMN-444','VIN000000004'),(6,5,15000,'Chevrolet','Spark','PQR-555','VIN000000005'),(7,6,54000,'Hyundai','Tucson','GHI-666','VIN000000006'),(8,7,120000,'Kia','Sportage','JKL-777','VIN000000007'),(9,8,23000,'Mazda','CX-5','STU-888','VIN000000008'),(10,9,78000,'Volkswagen','Jetta','VWX-999','VIN000000009'),(11,10,10000,'Suzuki','Swift','YZA-000','VIN000000010');
/*!40000 ALTER TABLE `vehiculos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-04  0:19:18
CREATE DATABASE  IF NOT EXISTS `msvc_tecnico` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `msvc_tecnico`;
-- MySQL dump 10.13  Distrib 8.0.46, for Win64 (x86_64)
--
-- Host: localhost    Database: msvc_tecnico
-- ------------------------------------------------------
-- Server version	8.0.46

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tecnico_entity_especialidades`
--

DROP TABLE IF EXISTS `tecnico_entity_especialidades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tecnico_entity_especialidades` (
  `tecnico_entity_id` bigint NOT NULL,
  `especialidades` varchar(255) DEFAULT NULL,
  KEY `FKsd7iddkkp6ru4yr37alg98adh` (`tecnico_entity_id`),
  CONSTRAINT `FKsd7iddkkp6ru4yr37alg98adh` FOREIGN KEY (`tecnico_entity_id`) REFERENCES `tecnicos` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnico_entity_especialidades`
--

LOCK TABLES `tecnico_entity_especialidades` WRITE;
/*!40000 ALTER TABLE `tecnico_entity_especialidades` DISABLE KEYS */;
INSERT INTO `tecnico_entity_especialidades` VALUES (1,'Motor'),(1,'Frenos'),(1,'Frenos'),(1,'Motor'),(2,'Pintura'),(2,'Carroceria'),(3,'Diagnostico_Electronico'),(4,'Mecanica_Rapida'),(4,'Aceite'),(5,'Transmision'),(6,'Frenos'),(6,'Suspension'),(7,'Motor'),(8,'Aire_Acondicionado'),(9,'Pintura'),(10,'Diagnostico_Electronico');
/*!40000 ALTER TABLE `tecnico_entity_especialidades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnicos`
--

DROP TABLE IF EXISTS `tecnicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tecnicos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `dni` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `nombre_completo` varchar(255) DEFAULT NULL,
  `hora_fin` time DEFAULT NULL,
  `hora_inicio` time DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnicos`
--

LOCK TABLES `tecnicos` WRITE;
/*!40000 ALTER TABLE `tecnicos` DISABLE KEYS */;
INSERT INTO `tecnicos` VALUES (1,'76543210','DISPONIBLE','Carlos Perez','17:00:00','08:00:00'),(2,'11111111','DISPONIBLE','Carlos Martinez','17:00:00','08:00:00'),(3,'22222222','DISPONIBLE','Juan Perez','17:00:00','08:00:00'),(4,'33333333','DISPONIBLE','Roberto Gomez','18:00:00','09:00:00'),(5,'44444444','DISPONIBLE','Luis Fernandez','16:00:00','07:00:00'),(6,'55555555','DISPONIBLE','Miguel Sanchez','17:00:00','08:00:00'),(7,'66666666','DISPONIBLE','Pedro Ramirez','19:00:00','10:00:00'),(8,'77777777','DISPONIBLE','Jorge Torres','17:00:00','08:00:00'),(9,'88888888','DISPONIBLE','Andres Suarez','18:00:00','09:00:00'),(10,'99999999','DISPONIBLE','Fernando Ruiz','17:00:00','08:00:00'),(11,'10101010','DISPONIBLE','Ricardo Silva','16:00:00','07:00:00');
/*!40000 ALTER TABLE `tecnicos` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-10-04  0:19:19
