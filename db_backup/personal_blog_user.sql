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
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_on` datetime(6) DEFAULT NULL,
  `updated_on` datetime(6) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `email_address` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile_number` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `pincode` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `file_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK30xgra9ipatdffg6h20r8h830` (`file_id`),
  CONSTRAINT `FK952xk4gwwayowi095slee1jnm` FOREIGN KEY (`file_id`) REFERENCES `file_entity` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'2025-08-22 06:05:31.401480','2025-08-26 10:42:15.904188','Flat 12B, Green Valley Apartments, Baner Road','Pune','gowtham.k@technogenesis.in','Gowtham','Kathirvel','6787849162','12345678','411045','Maharashtra',8),(2,'2025-08-22 06:05:51.688074','2025-08-22 06:05:51.688128','D-45, Saket, Near PVR Saket','South Delhi','priya.verma@example.com','Priya','Verma','9123456780','Secure#456','110017','Delhi',NULL),(3,'2025-08-22 06:06:28.191632','2025-08-22 06:06:28.191675','12 MG Road, Brigade Towers','Bengaluru','rohan.mehta@example.com','Rohan','Mehta','9988776655','Qwerty@789','560001','Karnataka',NULL),(4,'2025-08-22 06:06:37.449295','2025-08-22 06:06:37.449340','201, Shanti Residency, Satellite','Ahmedabad','sneha.patel@example.com','Sneha','Patel','9001122334','Welcome@123','380015','Gujarat',NULL),(5,'2025-08-22 06:06:51.782918','2025-08-22 06:06:51.782960','Flat 5C, Jubilee Hills, Road No. 36','Hyderabad','aditya.rao@example.com','Aditya','Rao','8765432109','Admin#999','500033','Telangana',NULL),(6,'2025-08-22 06:07:00.846648','2025-08-22 06:07:00.846690','H.No. 123, Model Town Extension','Ludhiana','neha.kapoor@example.com','Neha','Kapoor','9345678901','Neha@321','141002','Punjab',NULL),(7,'2025-08-22 06:07:11.038624','2025-08-22 06:07:11.038697','B-56, Malviya Nagar','Jaipur','vikram.singh@example.com','Vikram','Singh','9456123789','King@007','302017','Rajasthan',NULL),(8,'2025-08-22 06:07:24.092665','2025-08-22 06:07:24.092735','Villa 9, Marine Drive','Kochi','kavya.nair@example.com','Kavya','Nair','9098765432','Kavya#456','682031','Kerala',NULL),(9,'2025-08-22 06:07:33.306354','2025-08-22 06:07:33.306401','23, Hazratganj Market','Lucknow','manish.gupta@example.com','Manish','Gupta','9234567810','Luck@2023','226001','Uttar Pradesh',NULL),(10,'2025-08-22 06:07:41.128335','2025-08-22 06:07:41.128402','Plot 78, Benz Circle','Vijayawada','ananya.reddy@example.com','Ananya','Reddy','9356789012','Hello@555','520010','Andhra Pradesh',NULL),(11,'2025-08-25 11:10:59.183194','2025-08-25 11:10:59.183254','6/8,Vaiyathan, Vikkiramangalam','Madurai','gowtham.kg@technogenesis.in','Gowtham','Kathirvel','9566721028','12345678','625207','Tamil Nadu',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-08-26 18:08:03
