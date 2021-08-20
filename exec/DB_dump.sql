-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: i5a603.p.ssafy.io    Database: donas
-- ------------------------------------------------------
-- Server version	8.0.26-0ubuntu0.20.04.2

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
-- Table structure for table `Alarm`
--

DROP TABLE IF EXISTS `Alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Alarm` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `article_id` bigint DEFAULT NULL,
  `confirm` int NOT NULL DEFAULT '1',
  `contents` varchar(200) NOT NULL,
  `send_name` varchar(255) NOT NULL,
  `send_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `receive_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkp9lbdkyyl6prf9kxjmij6upl` (`receive_id`),
  CONSTRAINT `FKkp9lbdkyyl6prf9kxjmij6upl` FOREIGN KEY (`receive_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=85 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Alarm`
--

LOCK TABLES `Alarm` WRITE;
/*!40000 ALTER TABLE `Alarm` DISABLE KEYS */;
INSERT INTO `Alarm` VALUES (1,-1,0,'조정원님이 회원님을 팔로우하기 시작했습니다.','조정원','2021-08-20 04:47:32',1),(2,-1,0,'조정원님이 회원님을 팔로우하기 시작했습니다.','조정원','2021-08-20 04:47:39',2),(3,-1,1,'조정원님이 회원님을 팔로우하기 시작했습니다.','조정원','2021-08-20 04:47:50',4),(4,-1,0,'조정원님이 회원님을 팔로우하기 시작했습니다.','조정원','2021-08-20 04:47:56',3),(5,-1,0,'조정원님이 회원님을 팔로우하기 시작했습니다.','조정원','2021-08-20 04:48:01',5),(6,-1,1,'조정원님이 회원님을 팔로우하기 시작했습니다.','조정원','2021-08-20 04:48:30',6),(7,-1,1,'김싸피님이 회원님을 팔로우하기 시작했습니다.','김싸피','2021-08-20 04:48:55',16),(8,-1,0,'김싸피님이 회원님을 팔로우하기 시작했습니다.','김싸피','2021-08-20 04:48:58',1),(9,-1,1,'김싸피님이 회원님을 팔로우하기 시작했습니다.','김싸피','2021-08-20 04:49:05',6),(10,-1,0,'김싸피님이 회원님을 팔로우하기 시작했습니다.','김싸피','2021-08-20 04:49:13',5),(11,-1,0,'김싸피님이 회원님을 팔로우하기 시작했습니다.','김싸피','2021-08-20 04:49:14',5),(12,-1,1,'김싸피님이 회원님을 팔로우하기 시작했습니다.','김싸피','2021-08-20 04:49:19',4),(13,-1,0,'김싸피님이 회원님을 팔로우하기 시작했습니다.','김싸피','2021-08-20 04:49:25',5),(14,-1,1,'이싸피님이 회원님을 팔로우하기 시작했습니다.','이싸피','2021-08-20 04:50:32',16),(15,-1,1,'이싸피님이 회원님을 팔로우하기 시작했습니다.','이싸피','2021-08-20 04:50:35',6),(16,-1,0,'이싸피님이 회원님을 팔로우하기 시작했습니다.','이싸피','2021-08-20 04:50:43',2),(17,-1,0,'이싸피님이 회원님을 팔로우하기 시작했습니다.','이싸피','2021-08-20 04:50:45',1),(18,-1,0,'이싸피님이 회원님을 팔로우하기 시작했습니다.','이싸피','2021-08-20 04:51:08',3),(19,-1,0,'이싸피님이 회원님을 팔로우하기 시작했습니다.','이싸피','2021-08-20 04:51:15',5),(20,-1,1,'이싸피님이 회원님을 팔로우하기 시작했습니다.','이싸피','2021-08-20 04:51:21',8),(21,-1,1,'박싸피님이 회원님을 팔로우하기 시작했습니다.','박싸피','2021-08-20 04:51:50',16),(22,-1,0,'박싸피님이 회원님을 팔로우하기 시작했습니다.','박싸피','2021-08-20 04:51:53',1),(23,-1,0,'박싸피님이 회원님을 팔로우하기 시작했습니다.','박싸피','2021-08-20 04:51:55',2),(24,-1,1,'박싸피님이 회원님을 팔로우하기 시작했습니다.','박싸피','2021-08-20 04:51:59',4),(25,-1,0,'박싸피님이 회원님을 팔로우하기 시작했습니다.','박싸피','2021-08-20 04:52:07',5),(26,-1,1,'박싸피님이 회원님을 팔로우하기 시작했습니다.','박싸피','2021-08-20 04:52:52',8),(27,-1,1,'박싸피님이 회원님을 팔로우하기 시작했습니다.','박싸피','2021-08-20 04:53:08',6),(28,-1,1,'최싸피님이 회원님을 팔로우하기 시작했습니다.','최싸피','2021-08-20 04:53:51',16),(29,-1,0,'최싸피님이 회원님을 팔로우하기 시작했습니다.','최싸피','2021-08-20 04:53:53',2),(30,-1,0,'최싸피님이 회원님을 팔로우하기 시작했습니다.','최싸피','2021-08-20 04:53:55',1),(31,-1,1,'최싸피님이 회원님을 팔로우하기 시작했습니다.','최싸피','2021-08-20 04:53:58',4),(32,-1,0,'최싸피님이 회원님을 팔로우하기 시작했습니다.','최싸피','2021-08-20 04:54:00',3),(33,-1,1,'최싸피님이 회원님을 팔로우하기 시작했습니다.','최싸피','2021-08-20 04:54:27',8),(34,-1,1,'최싸피님이 회원님을 팔로우하기 시작했습니다.','최싸피','2021-08-20 04:54:31',9),(35,-1,1,'최싸피님이 회원님을 팔로우하기 시작했습니다.','최싸피','2021-08-20 04:54:46',6),(36,-1,0,'조싸피님이 회원님을 팔로우하기 시작했습니다.','조싸피','2021-08-20 04:55:44',5),(37,-1,1,'조싸피님이 회원님을 팔로우하기 시작했습니다.','조싸피','2021-08-20 04:55:47',16),(38,-1,0,'조싸피님이 회원님을 팔로우하기 시작했습니다.','조싸피','2021-08-20 04:55:49',1),(39,-1,0,'조싸피님이 회원님을 팔로우하기 시작했습니다.','조싸피','2021-08-20 04:55:53',2),(40,-1,0,'조싸피님이 회원님을 팔로우하기 시작했습니다.','조싸피','2021-08-20 04:55:55',3),(41,-1,1,'조싸피님이 회원님을 팔로우하기 시작했습니다.','조싸피','2021-08-20 04:55:57',4),(42,-1,1,'조싸피님이 회원님을 팔로우하기 시작했습니다.','조싸피','2021-08-20 04:56:20',9),(43,-1,1,'김규일님이 회원님을 팔로우하기 시작했습니다.','김규일','2021-08-20 05:12:13',16),(44,-1,0,'김규일님이 회원님을 팔로우하기 시작했습니다.','김규일','2021-08-20 05:12:16',2),(45,-1,1,'김규일님이 회원님을 팔로우하기 시작했습니다.','김규일','2021-08-20 05:12:19',4),(46,-1,0,'김규일님이 회원님을 팔로우하기 시작했습니다.','김규일','2021-08-20 05:12:23',3),(47,-1,0,'김규일님이 회원님을 팔로우하기 시작했습니다.','김규일','2021-08-20 05:12:25',5),(48,-1,1,'김규일님이 회원님을 팔로우하기 시작했습니다.','김규일','2021-08-20 05:12:28',8),(49,-1,0,'진주희님이 회원님을 팔로우하기 시작했습니다.','진주희','2021-08-20 05:47:53',16),(50,-1,0,'진주희님이 회원님을 팔로우하기 시작했습니다.','진주희','2021-08-20 05:47:56',1),(51,-1,0,'진주희님이 회원님을 팔로우하기 시작했습니다.','진주희','2021-08-20 05:48:35',2),(52,-1,1,'진주희님이 회원님을 팔로우하기 시작했습니다.','진주희','2021-08-20 05:48:38',6),(53,-1,0,'진주희님이 회원님을 팔로우하기 시작했습니다.','진주희','2021-08-20 05:48:44',3),(54,-1,0,'진주희님이 회원님을 팔로우하기 시작했습니다.','진주희','2021-08-20 05:48:57',1),(55,-1,1,'진주희님이 회원님을 팔로우하기 시작했습니다.','진주희','2021-08-20 05:49:56',4),(56,-1,0,'진주희님이 회원님을 팔로우하기 시작했습니다.','진주희','2021-08-20 05:50:00',3),(57,-1,1,'진주희님이 회원님을 팔로우하기 시작했습니다.','진주희','2021-08-20 05:50:03',8),(58,-1,0,'김규일님이 회원님을 팔로우하기 시작했습니다.','김규일','2021-08-20 06:11:31',19),(59,-1,0,'최싸피님이 회원님을 팔로우하기 시작했습니다.','최싸피','2021-08-20 06:22:34',19),(60,-1,0,'김싸피님이 회원님을 팔로우하기 시작했습니다.','김싸피','2021-08-20 06:23:13',19),(61,-1,0,'진주희님이 회원님을 팔로우하기 시작했습니다.','진주희','2021-08-20 06:23:59',2),(62,-1,0,'진주희님이 회원님을 팔로우하기 시작했습니다.','진주희','2021-08-20 06:24:00',2),(63,-1,1,'진주희님이 회원님을 팔로우하기 시작했습니다.','진주희','2021-08-20 06:24:04',5),(64,-1,0,'조정원님이 회원님을 팔로우하기 시작했습니다.','조정원','2021-08-20 06:27:05',19),(65,4,1,'김싸피님이 \"매일  아침 기상 인증\"퀘스트의 게시물을 좋아합니다.','김싸피','2021-08-20 07:02:02',2),(66,2,0,'김규일님이 \"서울 맛집 투어\"퀘스트의 게시물을 좋아합니다.','김규일','2021-08-20 07:02:35',1),(67,6,0,'김규일님이 \"미라클 런닝\"퀘스트의 게시물을 좋아합니다.','김규일','2021-08-20 07:03:38',18),(68,6,0,'김규일님이 \"미라클 런닝\"퀘스트의 게시물에 댓글을 남겼습니다.','김규일','2021-08-20 07:03:48',18),(69,-1,0,'코치님님이 회원님을 팔로우하기 시작했습니다.','코치님','2021-08-20 07:07:33',19),(70,-1,0,'코치님님이 회원님을 팔로우하기 시작했습니다.','코치님','2021-08-20 07:07:38',5),(71,-1,1,'코치님님이 회원님을 팔로우하기 시작했습니다.','코치님','2021-08-20 07:07:41',3),(72,-1,1,'코치님님이 회원님을 팔로우하기 시작했습니다.','코치님','2021-08-20 07:07:46',1),(73,-1,1,'코치님님이 회원님을 팔로우하기 시작했습니다.','코치님','2021-08-20 07:14:15',6),(74,-1,1,'최싸피님이 회원님을 팔로우하기 시작했습니다.','최싸피','2021-08-20 07:26:10',17),(75,-1,1,'정싸피님이 회원님을 팔로우하기 시작했습니다.','정싸피','2021-08-20 07:26:42',17),(76,-1,1,'컨설턴트님님이 회원님을 팔로우하기 시작했습니다.','컨설턴트님','2021-08-20 07:27:19',2),(77,-1,1,'컨설턴트님님이 회원님을 팔로우하기 시작했습니다.','컨설턴트님','2021-08-20 07:27:22',4),(78,-1,0,'코치님님이 회원님을 팔로우하기 시작했습니다.','코치님','2021-08-20 07:27:43',18),(79,8,0,'코치님님이 \"미라클 런닝\"퀘스트의 게시물을 좋아합니다.','코치님','2021-08-20 07:33:24',18),(80,8,0,'코치님님이 \"미라클 런닝\"퀘스트의 게시물에 댓글을 남겼습니다.','코치님','2021-08-20 07:33:41',18),(81,7,0,'코치님님이 \"하루에 한번 하늘 보기\"퀘스트의 게시물에 댓글을 남겼습니다.','코치님','2021-08-20 07:33:55',18),(82,7,0,'코치님님이 \"하루에 한번 하늘 보기\"퀘스트의 게시물을 좋아합니다.','코치님','2021-08-20 07:33:56',18),(83,7,0,'컨설턴트님님이 \"하루에 한번 하늘 보기\"퀘스트의 게시물을 좋아합니다.','컨설턴트님','2021-08-20 07:34:27',18),(84,6,0,'코치님님이 \"미라클 런닝\"퀘스트의 게시물을 좋아합니다.','코치님','2021-08-20 07:34:56',18);
/*!40000 ALTER TABLE `Alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Article`
--

DROP TABLE IF EXISTS `Article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Article` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `image` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `updated_at` datetime DEFAULT NULL,
  `quest_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmbxt6osasfykvngbuc1qny29g` (`quest_id`),
  KEY `FKkpi7de60p3npqbdh8yi0taf7x` (`user_id`),
  CONSTRAINT `FKkpi7de60p3npqbdh8yi0taf7x` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FKmbxt6osasfykvngbuc1qny29g` FOREIGN KEY (`quest_id`) REFERENCES `Quest` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Article`
--

LOCK TABLES `Article` WRITE;
/*!40000 ALTER TABLE `Article` DISABLE KEYS */;
INSERT INTO `Article` VALUES (1,'똠똠 - 강남역\n쌀국수 맛있어요!','2021-08-20 06:06:29','https://donas.s3.ap-northeast-2.amazonaws.com/KakaoTalk_20210820_060516687.jpg','R',NULL,5,19),(2,'인증합니다','2021-08-20 06:08:13','https://donas.s3.ap-northeast-2.amazonaws.com/5%EC%8B%9C.jpg','R',NULL,5,1),(3,'뿌빳뽕 커리 \n맛있어요 \n','2021-08-20 06:10:04','https://donas.s3.ap-northeast-2.amazonaws.com/122E3E9D-8281-499A-A059-6A3B74C5A251_1_102_o.jpeg','R',NULL,5,16),(4,'인증합니다!','2021-08-20 06:50:09','https://donas.s3.ap-northeast-2.amazonaws.com/%EB%8B%AC%EB%A6%AC%EA%B8%B0.jpg','R',NULL,8,2),(5,'일찍 일어났습니다 ~','2021-08-20 06:50:58','https://donas.s3.ap-northeast-2.amazonaws.com/5%EC%8B%9C.jpg','R',NULL,8,1),(6,'달리기 인증','2021-08-20 07:03:27','https://donas.s3.ap-northeast-2.amazonaws.com/%EB%8B%AC%EB%A6%AC%EA%B8%B0.jpg','P',NULL,2,18),(7,'파란 하늘 비슷한!','2021-08-20 07:29:46','https://donas.s3.ap-northeast-2.amazonaws.com/5%EC%8B%9C.jpg','P',NULL,4,18),(8,'인증합니다','2021-08-20 07:32:39','https://donas.s3.ap-northeast-2.amazonaws.com/KakaoTalk_20210820_073153926.jpg','P',NULL,2,18);
/*!40000 ALTER TABLE `Article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Cash`
--

DROP TABLE IF EXISTS `Cash`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Cash` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `accountNum` varchar(255) NOT NULL,
  `amount` bigint NOT NULL,
  `bank` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `time` datetime DEFAULT CURRENT_TIMESTAMP,
  `charity_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgj8qe94wx769fqnfcrt1wv6s6` (`charity_id`),
  KEY `FK8sxs40jdkyagudf9smot33ct3` (`user_id`),
  CONSTRAINT `FK8sxs40jdkyagudf9smot33ct3` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FKgj8qe94wx769fqnfcrt1wv6s6` FOREIGN KEY (`charity_id`) REFERENCES `Charity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Cash`
--

LOCK TABLES `Cash` WRITE;
/*!40000 ALTER TABLE `Cash` DISABLE KEYS */;
/*!40000 ALTER TABLE `Cash` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Charity`
--

DROP TABLE IF EXISTS `Charity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Charity` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bgPicture` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `homepage` varchar(255) DEFAULT NULL,
  `name` varchar(20) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `quarter` int DEFAULT '0',
  `tag` varchar(255) DEFAULT NULL,
  `tag2` varchar(255) DEFAULT NULL,
  `total` bigint DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Charity`
--

LOCK TABLES `Charity` WRITE;
/*!40000 ALTER TABLE `Charity` DISABLE KEYS */;
INSERT INTO `Charity` VALUES (1,'https://donas.s3.ap-northeast-2.amazonaws.com/charity/sarang_bg.jpg','\"사랑의열매\"라는 빨간 열매심볼로 알려진 사회복지공동모금회는 이웃과 더불어 나누는 기쁨을, 어려운 이웃에게 희망을 주기위해 1998년 설립된 모금,배분전문 민간단체입니다.','https://chest.or.kr/base.do','사랑복지공동모금회','https://donas.s3.ap-northeast-2.amazonaws.com/charity/sarang_logo.jpg',0,'사회복지','빨간열매',0),(2,'https://donas.s3.ap-northeast-2.amazonaws.com/charity/green_bg.jpg','환경재단은 2002년 설립된 우리나라 최초의 환경 전문 공익재단으로 문화적인 접근 방식과 전문성을 통해 환경인식을 높이는 활동을 하고 있습니다. 국내외 환경운동가와 시민,환경단체들을 도왔으며 환경 전문성을 바탕으로 누구나 일상 속에서 환경문제를 가까이 하고 변화할 수 있도록 다양한 프로그램을 펼치고 있습니다.','https://blog.naver.com/greenfu','환경재단','https://donas.s3.ap-northeast-2.amazonaws.com/charity/green_logo.jpg',0,'환경','국내외',0),(3,'https://donas.s3.ap-northeast-2.amazonaws.com/charity/house_bg.jpg','우리 주변에는 아직도 썩고 무너진 집에서 살아가는 이웃들이 많이 있습니다. 이러한 환경속에서 살아가는 이웃들은 삶의 희망을 잃고, 어두운 환경속에서 많은 질병에 노출되어 있습니다. 러브하우스는 2002년부터 이러한 어려운 이웃분들의 집을 무료로 수리해드리는 봉사단체입니다.','https://cafe.daum.net/mbclovehouse','희망의 러브하우스','https://donas.s3.ap-northeast-2.amazonaws.com/charity/house_logo.jpg',0,'시민사회','주택수리',0),(4,'https://donas.s3.ap-northeast-2.amazonaws.com/charity/umbrella_bg.jpg','1948년 문을 연 초록우산 어린이재단은 1950년 6.25전쟁 고아 구호사업에 집중한 이후 현재에 이르기까지 국내외 아동복지사업, 애드보커시, 모금사업, 연구조사 등을 폭넓게 실시하고 있는 아동옹호대표기관입니다. 현재 국내 외 아동 연간 약 100만명에게 직간접 도움을 주며 그들의 미래를 열어가는 일을 하고 있습니다.','https://www.childfund.or.kr/main.do','초록우산 어린이재단','https://donas.s3.ap-northeast-2.amazonaws.com/charity/umbrella_logo.jpg',0,'어린이','국내외',0),(5,'https://donas.s3.ap-northeast-2.amazonaws.com/charity/animal_bg.jpg','동물구조119는 인간 중심 사회에서 스스로를 보호할 수 없는 동물을 비롯한 비인간생명체들을 적극적으로 보호 및 대변하며, 모든 동물의 권리와 복지를 증진시키고 생명사랑과 생명존중의 의미를 널리 알려나갑니다. 궁극적으로 인간과 동물이 평화롭게 공존하는 사회 건설을 목표로 합니다.','http://119ark.org/','사단법인 동물구조 119','https://donas.s3.ap-northeast-2.amazonaws.com/charity/animal_logo.jpg',0,'동물','생명존중',0),(6,'https://donas.s3.ap-northeast-2.amazonaws.com/charity/flower_bg.jpg','바보들꽃의 희망의 언덕 프로젝트는 2005년부터 네팔의 일하는 어린이들의 교육지원 및 인권보호를 위하여 시작된 프로젝트입니다. 네팔이주 노동자 자녀들과 일하는 어린이들이 학교에 다닐 수 있도록 지원하고 있습니다.','http://book.foolwildflower.or.kr/','바보들꽃','https://donas.s3.ap-northeast-2.amazonaws.com/charity/flower_logo.jpg',0,'해외','교육지원',0);
/*!40000 ALTER TABLE `Charity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Comment`
--

DROP TABLE IF EXISTS `Comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Comment` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `reply_to` bigint DEFAULT '0',
  `updated_at` datetime DEFAULT NULL,
  `article_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKt8868quske2jw98t2nw4ddig0` (`article_id`),
  KEY `FKhlbnnmiua9xpvfq8y1u1a15ie` (`user_id`),
  CONSTRAINT `FKhlbnnmiua9xpvfq8y1u1a15ie` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FKt8868quske2jw98t2nw4ddig0` FOREIGN KEY (`article_id`) REFERENCES `Article` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Comment`
--

LOCK TABLES `Comment` WRITE;
/*!40000 ALTER TABLE `Comment` DISABLE KEYS */;
INSERT INTO `Comment` VALUES (1,'달리기 하셨네요 !!!!','2021-08-20 07:03:48',0,'2021-08-20 07:03:48',6,1),(2,'매주 많이 뛰셨군요!!!!','2021-08-20 07:33:40',0,'2021-08-20 07:33:40',8,17),(3,'하늘색이 너무 이쁩니다!','2021-08-20 07:33:55',0,'2021-08-20 07:33:55',7,17);
/*!40000 ALTER TABLE `Comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Donation`
--

DROP TABLE IF EXISTS `Donation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Donation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` bigint NOT NULL,
  `cash_out` int NOT NULL DEFAULT '0',
  `name` varchar(255) NOT NULL,
  `time` datetime DEFAULT CURRENT_TIMESTAMP,
  `charity_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi1iqr95mj117abpcccijoo5n3` (`charity_id`),
  KEY `FKemqkouhxxkdqqv6x6muxs1ea6` (`user_id`),
  CONSTRAINT `FKemqkouhxxkdqqv6x6muxs1ea6` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FKi1iqr95mj117abpcccijoo5n3` FOREIGN KEY (`charity_id`) REFERENCES `Charity` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Donation`
--

LOCK TABLES `Donation` WRITE;
/*!40000 ALTER TABLE `Donation` DISABLE KEYS */;
/*!40000 ALTER TABLE `Donation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Follow`
--

DROP TABLE IF EXISTS `Follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Follow` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `follower_id` bigint DEFAULT NULL,
  `followee_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKatrwkyk0jcww5fbsnxv6vn2rb` (`follower_id`),
  KEY `FK7uydgooejfew47k4jf64g5nrs` (`followee_id`),
  CONSTRAINT `FK7uydgooejfew47k4jf64g5nrs` FOREIGN KEY (`followee_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FKatrwkyk0jcww5fbsnxv6vn2rb` FOREIGN KEY (`follower_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Follow`
--

LOCK TABLES `Follow` WRITE;
/*!40000 ALTER TABLE `Follow` DISABLE KEYS */;
INSERT INTO `Follow` VALUES (1,16,1),(2,16,2),(3,16,4),(4,16,3),(5,16,5),(6,16,6),(7,2,16),(8,2,1),(9,2,6),(12,2,4),(13,2,5),(14,4,16),(15,4,6),(16,4,2),(17,4,1),(18,4,3),(19,4,5),(20,4,8),(21,3,16),(22,3,1),(23,3,2),(24,3,4),(25,3,5),(26,3,8),(27,3,6),(28,5,16),(29,5,2),(30,5,1),(31,5,4),(32,5,3),(33,5,8),(34,5,9),(35,5,6),(36,8,5),(37,8,16),(38,8,1),(39,8,2),(40,8,3),(41,8,4),(42,8,9),(43,1,16),(44,1,2),(45,1,4),(46,1,3),(47,1,5),(48,1,8),(49,19,16),(52,19,6),(54,19,1),(55,19,4),(56,19,3),(57,19,8),(58,1,19),(59,5,19),(60,2,19),(62,19,2),(63,19,5),(64,16,19),(65,17,19),(66,17,5),(67,17,3),(68,17,1),(69,17,6),(70,5,17),(71,6,17),(72,18,2),(73,18,4),(74,17,18);
/*!40000 ALTER TABLE `Follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Group_quest`
--

DROP TABLE IF EXISTS `Group_quest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Group_quest` (
  `user_cnt` int DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK78qo12b52nswchowrw7n3xc1f` FOREIGN KEY (`id`) REFERENCES `Quest` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Group_quest`
--

LOCK TABLES `Group_quest` WRITE;
/*!40000 ALTER TABLE `Group_quest` DISABLE KEYS */;
INSERT INTO `Group_quest` VALUES (5,1),(3,7),(3,9),(3,10);
/*!40000 ALTER TABLE `Group_quest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Images`
--

DROP TABLE IF EXISTS `Images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Images` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `size` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Images`
--

LOCK TABLES `Images` WRITE;
/*!40000 ALTER TABLE `Images` DISABLE KEYS */;
/*!40000 ALTER TABLE `Images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Likes`
--

DROP TABLE IF EXISTS `Likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Likes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `article_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4t1tpsf0gvdxagd3re1f4sptx` (`article_id`),
  KEY `FKl43j81dlqtedb3lfngqien4f8` (`user_id`),
  CONSTRAINT `FK4t1tpsf0gvdxagd3re1f4sptx` FOREIGN KEY (`article_id`) REFERENCES `Article` (`id`),
  CONSTRAINT `FKl43j81dlqtedb3lfngqien4f8` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Likes`
--

LOCK TABLES `Likes` WRITE;
/*!40000 ALTER TABLE `Likes` DISABLE KEYS */;
INSERT INTO `Likes` VALUES (1,4,2),(2,2,1),(3,6,1),(4,8,17),(5,7,17),(6,7,18),(7,6,17);
/*!40000 ALTER TABLE `Likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Message`
--

DROP TABLE IF EXISTS `Message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Message` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `confirm` int DEFAULT '0',
  `content` varchar(255) DEFAULT NULL,
  `time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `room_id` bigint DEFAULT NULL,
  `receive_id` bigint DEFAULT NULL,
  `send_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgxhg2mnnt3n2wlt5bvy9338fc` (`room_id`),
  KEY `FK7hnerk4786q4xtensuti9h70p` (`receive_id`),
  KEY `FKt6rwrxhxk37fefvmytnbycvti` (`send_id`),
  CONSTRAINT `FK7hnerk4786q4xtensuti9h70p` FOREIGN KEY (`receive_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FKgxhg2mnnt3n2wlt5bvy9338fc` FOREIGN KEY (`room_id`) REFERENCES `Messageroom` (`id`),
  CONSTRAINT `FKt6rwrxhxk37fefvmytnbycvti` FOREIGN KEY (`send_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Message`
--

LOCK TABLES `Message` WRITE;
/*!40000 ALTER TABLE `Message` DISABLE KEYS */;
INSERT INTO `Message` VALUES (1,1,'안녕하세요 최싸피님','2021-08-20 07:17:45',1,5,17),(2,1,'반갑습니다!','2021-08-20 07:17:50',1,5,17),(3,1,'안녕하세요!','2021-08-20 07:18:10',1,17,5),(4,1,'반갑습니다','2021-08-20 07:18:13',1,17,5),(5,0,'규일님 인증 해 주세요!!!','2021-08-20 08:06:27',2,1,16),(6,0,'인증못하면 실패에요!!','2021-08-20 08:06:37',2,1,16),(7,0,'녹화용 메세지 였습니다 ㅠ ','2021-08-20 08:06:58',2,1,16);
/*!40000 ALTER TABLE `Message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Messageroom`
--

DROP TABLE IF EXISTS `Messageroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Messageroom` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `userone_id` bigint DEFAULT NULL,
  `usertwo_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh1drglf158sftcfb6cpwx6qxk` (`userone_id`),
  KEY `FKie7bjd0bfp8blb9rfpyovlsjk` (`usertwo_id`),
  CONSTRAINT `FKh1drglf158sftcfb6cpwx6qxk` FOREIGN KEY (`userone_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FKie7bjd0bfp8blb9rfpyovlsjk` FOREIGN KEY (`usertwo_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Messageroom`
--

LOCK TABLES `Messageroom` WRITE;
/*!40000 ALTER TABLE `Messageroom` DISABLE KEYS */;
INSERT INTO `Messageroom` VALUES (1,17,5),(2,16,1);
/*!40000 ALTER TABLE `Messageroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Personal_quest`
--

DROP TABLE IF EXISTS `Personal_quest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Personal_quest` (
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKc5lcxwh5x2ts7dfeciwiu2998` FOREIGN KEY (`id`) REFERENCES `Quest` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Personal_quest`
--

LOCK TABLES `Personal_quest` WRITE;
/*!40000 ALTER TABLE `Personal_quest` DISABLE KEYS */;
INSERT INTO `Personal_quest` VALUES (2),(4),(6);
/*!40000 ALTER TABLE `Personal_quest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Quest`
--

DROP TABLE IF EXISTS `Quest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Quest` (
  `DTYPE` varchar(31) NOT NULL,
  `id` bigint NOT NULL AUTO_INCREMENT,
  `certification` varchar(255) DEFAULT NULL,
  `description` varchar(255) NOT NULL,
  `finish_at` datetime DEFAULT NULL,
  `mileage` bigint NOT NULL,
  `minArticleCount` int NOT NULL,
  `percent` double NOT NULL DEFAULT '0',
  `picture` varchar(255) DEFAULT NULL,
  `start_at` datetime NOT NULL,
  `success` int NOT NULL DEFAULT '0',
  `title` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Quest`
--

LOCK TABLES `Quest` WRITE;
/*!40000 ALTER TABLE `Quest` DISABLE KEYS */;
INSERT INTO `Quest` VALUES ('G',1,'알고리즘 문제 풀어서 캡쳐해서 올린기 \n-문제를 푸는 것이 중요합니다. 성공여부는 중요하지 않습니다. \n- 매일매일 꾸준히 알고하는 싸피인이 됩시다','하루에 알고리즘 1문제 풀기 ','2021-08-19 23:59:59',2000,5,0,'https://donas.s3.ap-northeast-2.amazonaws.com/KakaoTalk_Photo_2021-08-18-22-59-19.png','2021-08-15 00:00:00',0,'1일 1알고','G'),('P',2,'매일 런닝한 사진을 게시','매일 런닝','2021-08-21 23:59:59',2000,1,0,'https://donas.s3.ap-northeast-2.amazonaws.com/%EB%8B%AC%EB%A6%AC%EA%B8%B0.jpg','2021-08-20 00:00:00',0,'미라클 런닝','P'),('R',3,'매일 새벽에 일어난 시간을 게시','매일 새벽 기상 인증','2021-08-23 23:59:59',2000,0,0,'https://donas.s3.ap-northeast-2.amazonaws.com/5%EC%8B%9C.jpg','2021-08-20 00:00:00',0,'매일 새벽 기상','R'),('P',4,'하루에 한번 밖에 나가서 하늘을 찍어 인증해 주세요.\n- 한번은 잠깐 쉬면서 하늘을 보는 여유를 가져요!','하루에 한번은 하늘을 보는 여유','2021-08-27 23:59:59',2000,4,0,'https://donas.s3.ap-northeast-2.amazonaws.com/KakaoTalk_20210818_212636133.jpg','2021-08-19 00:00:00',0,'하루에 한번 하늘 보기','P'),('R',5,'서울 추천 맛집 한곳에서 인증샷을 찍어주세요.\n- 식당이름과 위치를 인증글에 적어 주세요.','추천하고 싶은 맛집을 인증','2021-08-29 23:59:59',2000,0,0,'https://donas.s3.ap-northeast-2.amazonaws.com/%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202021-08-19%20013203.png','2021-08-18 00:00:00',0,'서울 맛집 투어','R'),('P',6,'일주일에 한번 채식 식단의 사진을 올려서 인증해 주세요','환경을 위해서 한번씩 육류 참자','2021-08-27 23:59:59',2000,1,0,'https://donas.s3.ap-northeast-2.amazonaws.com/%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202021-08-20%20060223.png','2021-08-19 00:00:00',0,'환경을 위한 채식','P'),('G',7,'여행을 갈 수 있을때를 대비해서 여행 경비 모으기.\n일주일에 한번 은행 이체 내역 찍어서 인증','여행 갈 날을 위해 준비하기','2021-09-12 23:59:59',2000,3,0,'https://donas.s3.ap-northeast-2.amazonaws.com/%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202021-08-20%20062141.png','2021-08-22 00:00:00',0,'여행 가자','G'),('R',8,'일어나는 시간이 나오도록 인증','기상인증을합시다','2021-08-23 23:59:59',2000,0,0,'https://donas.s3.ap-northeast-2.amazonaws.com/%EA%B8%B0%EB%B6%80%EB%8B%A8%EC%B2%B4.PNG','2021-08-20 00:00:00',0,'매일  아침 기상 인증','R'),('G',9,'하루에 알고리즘 한 문제 씩 풀고 풀이 인증 올리기','하루에 알고리즘 하나씩 풀기','2021-08-31 23:59:59',2000,9,0,'https://donas.s3.ap-northeast-2.amazonaws.com/%ED%99%94%EB%A9%B4%20%EC%BA%A1%EC%B2%98%202021-08-20%20071944.png','2021-08-22 00:00:00',0,'1일 1알고','G'),('G',10,'하루에 한문제씩 알고리즘 풀고 풀이 캡쳐로 인증하기','하루에 한문제씩 알고리즘 풀기','2021-08-29 23:59:59',2000,7,0,'https://donas.s3.ap-northeast-2.amazonaws.com/Screen%20Shot%202021-08-20%20at%208.00.48%20AM.png','2021-08-22 00:00:00',0,'1일 1알고','G');
/*!40000 ALTER TABLE `Quest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Quest_alarm`
--

DROP TABLE IF EXISTS `Quest_alarm`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Quest_alarm` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `confirm` int NOT NULL DEFAULT '1',
  `contents` varchar(200) NOT NULL,
  `send_name` varchar(255) NOT NULL,
  `send_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `quest_id` bigint DEFAULT NULL,
  `receive_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKooet0sfnwrw5y3icyb5kgcjho` (`quest_id`),
  KEY `FK55ejf9kdlb41r6q5np3t3qmja` (`receive_id`),
  CONSTRAINT `FK55ejf9kdlb41r6q5np3t3qmja` FOREIGN KEY (`receive_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FKooet0sfnwrw5y3icyb5kgcjho` FOREIGN KEY (`quest_id`) REFERENCES `Quest` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Quest_alarm`
--

LOCK TABLES `Quest_alarm` WRITE;
/*!40000 ALTER TABLE `Quest_alarm` DISABLE KEYS */;
INSERT INTO `Quest_alarm` VALUES (1,2,'[공동 퀘스트 요청] 1일 1알고','조정원','2021-08-20 04:58:37',1,2),(2,0,'[공동 퀘스트 요청] 1일 1알고','조정원','2021-08-20 04:58:37',1,4),(3,2,'[공동 퀘스트 요청] 1일 1알고','조정원','2021-08-20 04:58:37',1,3),(4,2,'[공동 퀘스트 요청] 1일 1알고','조정원','2021-08-20 04:58:37',1,5),(5,2,'[릴레이 퀘스트 요청] 서울 맛집 투어','진주희','2021-08-20 06:06:29',5,1),(6,2,'[릴레이 퀘스트 요청] 서울 맛집 투어','김규일','2021-08-20 06:08:13',5,16),(7,2,'[릴레이 퀘스트 요청] 서울 맛집 투어','조정원','2021-08-20 06:10:04',5,2),(8,2,'[공동 퀘스트 요청] 여행 가자','진주희','2021-08-20 06:25:40',7,2),(9,0,'[공동 퀘스트 요청] 여행 가자','진주희','2021-08-20 06:25:40',7,5),(10,2,'[릴레이 퀘스트 요청] 매일  아침 기상 인증','김싸피','2021-08-20 06:50:09',8,1),(11,0,'[릴레이 퀘스트 요청] 매일  아침 기상 인증','김규일','2021-08-20 06:50:58',8,3),(12,2,'[공동 퀘스트 요청] 1일 1알고','진주희','2021-08-20 07:23:17',9,1),(13,2,'[공동 퀘스트 요청] 1일 1알고','진주희','2021-08-20 07:23:18',9,16),(14,2,'[공동 퀘스트 요청] 1일 1알고','진주희','2021-08-20 08:03:05',10,16),(15,2,'[공동 퀘스트 요청] 1일 1알고','진주희','2021-08-20 08:03:06',10,1);
/*!40000 ALTER TABLE `Quest_alarm` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Quest_participants`
--

DROP TABLE IF EXISTS `Quest_participants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Quest_participants` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `rate` float NOT NULL DEFAULT '0',
  `success` int NOT NULL DEFAULT '0',
  `quest_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKijg1fqj9o4027iiv26on9l5qk` (`quest_id`),
  KEY `FK3t9otm4i0s27nhvjoc6t4y1hy` (`user_id`),
  CONSTRAINT `FK3t9otm4i0s27nhvjoc6t4y1hy` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`),
  CONSTRAINT `FKijg1fqj9o4027iiv26on9l5qk` FOREIGN KEY (`quest_id`) REFERENCES `Quest` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Quest_participants`
--

LOCK TABLES `Quest_participants` WRITE;
/*!40000 ALTER TABLE `Quest_participants` DISABLE KEYS */;
INSERT INTO `Quest_participants` VALUES (1,0,2,1,16),(2,0,0,2,18),(3,0,0,3,1),(4,0,0,4,19),(5,0,0,5,19),(6,0,2,1,5),(7,0,0,5,1),(8,0,0,5,16),(9,0,0,5,2),(10,0,0,6,19),(11,0,0,7,19),(12,0,2,1,2),(13,0,0,7,2),(14,0,0,8,2),(15,0,2,1,3),(16,0,0,8,1),(17,0,0,2,1),(18,0,0,2,17),(19,0,0,9,19),(20,0,0,9,1),(21,0,0,4,18),(22,0,0,10,19),(23,0,0,10,16),(24,0,0,9,16),(25,0,0,10,1);
/*!40000 ALTER TABLE `Quest_participants` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Relay_quest`
--

DROP TABLE IF EXISTS `Relay_quest`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Relay_quest` (
  `user_order` int NOT NULL,
  `target_cnt` int DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FKkhoos4ku9kx1ofciqpgn2rvix` FOREIGN KEY (`id`) REFERENCES `Quest` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Relay_quest`
--

LOCK TABLES `Relay_quest` WRITE;
/*!40000 ALTER TABLE `Relay_quest` DISABLE KEYS */;
INSERT INTO `Relay_quest` VALUES (1,2,3),(1,3,5),(1,2,8);
/*!40000 ALTER TABLE `Relay_quest` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Relay_wait`
--

DROP TABLE IF EXISTS `Relay_wait`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Relay_wait` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `relay_order` int NOT NULL,
  `relay_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK8h0pybv31hgdv28am5rjk4nua` (`relay_id`),
  KEY `FKaaf7twk373w7joppqq7x3t8np` (`user_id`),
  CONSTRAINT `FK8h0pybv31hgdv28am5rjk4nua` FOREIGN KEY (`relay_id`) REFERENCES `Relay_quest` (`id`),
  CONSTRAINT `FKaaf7twk373w7joppqq7x3t8np` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Relay_wait`
--

LOCK TABLES `Relay_wait` WRITE;
/*!40000 ALTER TABLE `Relay_wait` DISABLE KEYS */;
INSERT INTO `Relay_wait` VALUES (1,2,5,1),(2,2,5,16),(3,2,5,2),(4,2,8,1),(5,2,8,3);
/*!40000 ALTER TABLE `Relay_wait` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Search`
--

DROP TABLE IF EXISTS `Search`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Search` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) NOT NULL,
  `search_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKn5j2yi330yd254j9bdlp1kxtb` (`user_id`),
  CONSTRAINT `FKn5j2yi330yd254j9bdlp1kxtb` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Search`
--

LOCK TABLES `Search` WRITE;
/*!40000 ALTER TABLE `Search` DISABLE KEYS */;
INSERT INTO `Search` VALUES (1,'김규일','2021-08-19 19:18:22',16),(2,'김싸피','2021-08-19 19:37:56',16),(3,'김규일','2021-08-19 19:47:30',16),(4,'김싸피','2021-08-19 19:47:37',16),(5,'이싸피','2021-08-19 19:47:48',16),(6,'박싸피','2021-08-19 19:47:54',16),(7,'최싸피','2021-08-19 19:47:59',16),(8,'정싸피','2021-08-19 19:48:28',16),(9,'조정원','2021-08-19 19:48:54',2),(10,'조정원','2021-08-19 19:50:30',4),(11,'박싸피','2021-08-19 19:51:07',4),(12,'최싸피','2021-08-19 19:51:14',4),(13,'조싸피','2021-08-19 19:51:19',4),(14,'조싸피','2021-08-19 19:54:26',5),(15,'진싸피','2021-08-19 19:54:30',5),(16,'김규일','2021-08-19 19:54:35',5),(17,'정싸피','2021-08-19 19:54:45',5),(18,'진싸피','2021-08-19 19:56:19',8),(19,'조정원','2021-08-19 20:47:51',19),(20,'진주희','2021-08-19 21:22:32',5),(21,'김규일','2021-08-19 23:06:11',16);
/*!40000 ALTER TABLE `Search` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Token`
--

DROP TABLE IF EXISTS `Token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Token` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `token` varchar(200) NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiiyr9nhulmfrvje08nvravy02` (`user_id`),
  CONSTRAINT `FKiiyr9nhulmfrvje08nvravy02` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Token`
--

LOCK TABLES `Token` WRITE;
/*!40000 ALTER TABLE `Token` DISABLE KEYS */;
INSERT INTO `Token` VALUES (17,'fTI86K9iyXHkYZY3Blq2MW:APA91bGRwrKBmroLp9kVJRonhgdFYtLM6DxgkWqvBxDwV0bIg-mCJZJdvJaLRw14xA3KImnANGsCPwac3ZCUrVnD75vATgLhr91y5Nz7c5wDTFE6bi_rGmUmhsb822p_7CPDR9oYbLkt',2),(21,'fdGZnUlAyvCtfn46EfdfA1:APA91bF5yXlvi7qYWAgcQUvweAXxrFyOxtxddVVrpBVBKHcVIUxbYGp5HAts-9LyAbfjxcddMDq_HFVx0ZgRSODTESGl-mI0SZJRsiCPs76eYOi58lHJHhUahwkDQpgeIRaL830YijiJ',1),(31,'dN70JbxUm0e6dPo5B4VLn4:APA91bG1hYZauCyFCzkBKVlHsXF5EVxwj__O_gUGOjO64_-wVhMaxkvOp7iA-h4n-8yJm9DonXuVtNj2zDV6p-F--lOl7HIGs1oq19bpNXcTvnQ7rG7nlzWxmebHp4tWp7BijIIwwdVN',19),(37,'dZkUQEZjoxrMrZaRz-xjbD:APA91bHHOsP7ybpfWxY8_N27gDNUP0f1aJ2pTY3YLwNABmBWYg3jJsb3JWQSxlPy484LS8lh_UdiOo1qTnd7Ji-srcSzq45gnpJ4j6zVi99uD1GaPG3yOo6i6rcMsT5ioJfxDQEjHvqg',16),(45,'czaoUmpiv7ovubq-wx54MU:APA91bGH9k9Lnn_OlRmCFWxGzX_oCQ6Gf6hmQazhMngH3KdEQqURF2rX4cPF1vUuL2yA-Wty9SEDExXnqVmR3p_YUG2hardNy0Lk1L13t8GLRbD4S3GSZMJ9QW1gX1dCwJXZx2mt6_a8',1),(46,'ev2uHxP5FQ0vb86KAPr6iR:APA91bG8kdFWZUlW63l4W__LuPF0TT86QM1vmQolJvJERXimW-ig5t2AqIwD9Bnc9SjppsUbwkXYZk3HgjSxEvQ-45ZggFjTbsigf26-1enJ0DGf4YoYgAMuU3fr_u3K6k3bQ6VamsAq',16);
/*!40000 ALTER TABLE `Token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User`
--

DROP TABLE IF EXISTS `User`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(100) DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `joindate` datetime DEFAULT CURRENT_TIMESTAMP,
  `mileage` bigint NOT NULL DEFAULT '0',
  `nickname` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `quest_cnt` int NOT NULL DEFAULT '0',
  `quest_percent` int NOT NULL DEFAULT '0',
  `status` bit(1) NOT NULL DEFAULT b'1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User`
--

LOCK TABLES `User` WRITE;
/*!40000 ALTER TABLE `User` DISABLE KEYS */;
INSERT INTO `User` VALUES (1,NULL,'gyuil@naver.com','2021-08-19 18:51:45',30000,'김규일','qwer1234',NULL,6,0,_binary '\0'),(2,NULL,'kim@ssafy.com','2021-08-19 18:52:31',0,'김싸피','qwer1234',NULL,4,0,_binary '\0'),(3,NULL,'park@ssafy.com','2021-08-19 18:52:47',0,'박싸피','qwer1234',NULL,1,0,_binary '\0'),(4,NULL,'lee@ssafy.com','2021-08-19 18:53:01',0,'이싸피','qwer1234',NULL,0,0,_binary '\0'),(5,NULL,'choi@ssafy.com','2021-08-19 18:53:20',0,'최싸피','qwer1234',NULL,1,0,_binary '\0'),(6,NULL,'jeong@ssafy.com','2021-08-19 18:53:36',0,'정싸피','qwer1234',NULL,0,0,_binary '\0'),(7,NULL,'hyun@ssafy.com','2021-08-19 18:53:55',0,'현싸피','qwer1234',NULL,0,0,_binary '\0'),(8,NULL,'cho@ssafy.com','2021-08-19 18:54:15',0,'조싸피','qwer1234',NULL,0,0,_binary '\0'),(9,NULL,'jin@ssafy.com','2021-08-19 18:54:30',0,'진싸피','qwer1234',NULL,0,0,_binary '\0'),(10,NULL,'yang@ssafy.com','2021-08-19 18:55:04',0,'양싸피','qwer1234',NULL,0,0,_binary '\0'),(11,NULL,'jang@ssafy.com','2021-08-19 18:55:52',0,'장싸피','qwer1234',NULL,0,0,_binary '\0'),(12,NULL,'tom@ssafy.com','2021-08-19 18:56:16',0,'탐싸피','qwer1234',NULL,0,0,_binary '\0'),(13,NULL,'in@ssafy.com','2021-08-19 18:56:40',0,'인싸피','qwer1234',NULL,0,0,_binary '\0'),(14,NULL,'gyu@ssafy.com','2021-08-19 18:56:59',0,'규싸피','qwer1234',NULL,0,0,_binary '\0'),(15,NULL,'ahn@ssafy.com','2021-08-19 18:57:33',0,'안싸피','qwer1234',NULL,0,0,_binary '\0'),(16,'안녕! \n만나서 반가워 :)','jw@ssafy.com','2021-08-19 18:58:09',30000,'조정원','qwer1234','https://donas.s3.ap-northeast-2.amazonaws.com/94EA05C8-1854-4B37-9A09-D1141B1C2A74.jpeg',4,0,_binary '\0'),(17,NULL,'coach@ssafy.com','2021-08-19 19:52:51',0,'코치님','qwer1234',NULL,1,0,_binary '\0'),(18,NULL,'consultant@ssafy.com','2021-08-19 19:54:31',0,'컨설턴트님','qwer1234',NULL,2,0,_binary '\0'),(19,NULL,'jooheejin@ssafy.com','2021-08-19 20:47:24',30000,'진주희','test1234',NULL,6,0,_binary '\0');
/*!40000 ALTER TABLE `User` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `User_like_quests`
--

DROP TABLE IF EXISTS `User_like_quests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `User_like_quests` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `quest_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKo821v6pxl329op3nopcdpkr5e` (`quest_id`),
  KEY `FKxq6j9unw1vfe67fsuasfnbw8` (`user_id`),
  CONSTRAINT `FKo821v6pxl329op3nopcdpkr5e` FOREIGN KEY (`quest_id`) REFERENCES `Quest` (`id`),
  CONSTRAINT `FKxq6j9unw1vfe67fsuasfnbw8` FOREIGN KEY (`user_id`) REFERENCES `User` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `User_like_quests`
--

LOCK TABLES `User_like_quests` WRITE;
/*!40000 ALTER TABLE `User_like_quests` DISABLE KEYS */;
/*!40000 ALTER TABLE `User_like_quests` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'donas'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-20  8:53:30
