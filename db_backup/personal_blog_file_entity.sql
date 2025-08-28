CREATE DATABASE  IF NOT EXISTS `personal_blog` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `personal_blog`;
-- MySQL dump 10.13  Distrib 8.0.31, for macos12 (x86_64)
--
-- Host: localhost    Database: personal_blog
-- ------------------------------------------------------
-- Server version	8.4.5

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
-- Table structure for table `file_entity`
--

DROP TABLE IF EXISTS `file_entity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `file_entity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `file_name` varchar(255) DEFAULT NULL,
  `file_path` varchar(255) DEFAULT NULL,
  `file_size` bigint DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file_entity`
--

LOCK TABLES `file_entity` WRITE;
/*!40000 ALTER TABLE `file_entity` DISABLE KEYS */;
INSERT INTO `file_entity` VALUES (1,'2025-08-22 06:25:15.596729','2025-08-22 06:25:15.596760','1755843915591_my-profile-design.jpeg','uploads/1755843915591_my-profile-design.jpeg',24576,'image/jpeg'),(2,'2025-08-22 06:25:50.913241','2025-08-22 06:25:50.913297','1755843950909_my-profile-design.jpeg','uploads/1755843950909_my-profile-design.jpeg',24576,'image/jpeg'),(3,'2025-08-22 06:27:06.879289','2025-08-22 06:27:06.879319','1755844026876_my-profile-design.jpeg','uploads/1755844026876_my-profile-design.jpeg',24576,'image/jpeg'),(4,'2025-08-22 06:27:28.255340','2025-08-22 06:27:28.255403','1755844048248_my-profile-design.jpeg','uploads/1755844048248_my-profile-design.jpeg',24576,'image/jpeg'),(5,'2025-08-22 06:30:25.198371','2025-08-22 06:30:25.198400','1755844225196_my-profile-design.jpeg','uploads/1755844225196_my-profile-design.jpeg',24576,'image/jpeg'),(6,'2025-08-25 13:21:45.772318','2025-08-25 13:21:45.772388','1756128105682_user.png','uploads/1756128105682_user.png',15244,'image/png'),(7,'2025-08-25 13:22:42.294851','2025-08-25 13:22:42.294892','1756128162292_file-1648126695-TCJiVhMiXk.png','uploads/1756128162292_file-1648126695-TCJiVhMiXk.png',94469,'image/png'),(8,'2025-08-26 10:42:15.888027','2025-08-26 10:42:15.888053','1756204935879_user.png','uploads/1756204935879_user.png',15244,'image/png');
/*!40000 ALTER TABLE `file_entity` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-26 18:08:04
