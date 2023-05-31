-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 124.71.166.37    Database: ebooksys
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `avatarurl` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `email_UNIQUE` (`email`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1,'admin','admin','admin@shu.edu.cn',NULL),(2,'zouguobing','zouguobing','zouguobing@shu.edu.cn',NULL);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditbooks`
--

DROP TABLE IF EXISTS `auditbooks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditbooks` (
  `bookid` int NOT NULL AUTO_INCREMENT,
  `bookname` text,
  `author` text,
  `publisher` text,
  `isbn` text,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `coverimage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `category` text,
  `likes` int DEFAULT '0',
  `favorites` int DEFAULT '0',
  `releasetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `size` bigint DEFAULT NULL,
  `isdelete` tinyint(1) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `filename` text,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `suggestions` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `userid` int DEFAULT NULL,
  PRIMARY KEY (`bookid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=155 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditbooks`
--

LOCK TABLES `auditbooks` WRITE;
/*!40000 ALTER TABLE `auditbooks` DISABLE KEYS */;
INSERT INTO `auditbooks` VALUES (147,'1123123412','1231','312','312','213321','http://124.71.166.37:9091/file/bookpic/audit/c51308f928a94133961561a4f7dabec1.jpg','123132',0,0,'2023-05-21 03:34:44','pdf',369,1,0,'4f8c71259ed9eb45e85ef871757e7885','第9次上机内容（2023-5-18）.pdf','http://124.71.166.37:9091/file/audit/809009f40c3f481d81a48a6556774529.pdf','',NULL),(150,'111','111','111','111','1111','http://124.71.166.37:9091/file/bookpic/audit/c2f08db12ff14ffb99241ed2b42b8646.png','1111',0,0,'2023-05-22 15:02:17','pdf',464,1,0,'08c76c1ab7c23c073fe06c1178133bac','教育部学籍在线验证报告_周鹏飞.pdf','http://124.71.166.37:9091/file/audit/5d729d3932fb406abcd60ab5e2c96da4.pdf','',3),(151,'2222','222','222','222','2222','http://124.71.166.37:9091/file/bookpic/audit/3fe1c81d5e7f4ea08afc12bb43a5d141.png','222',0,0,'2023-05-22 15:10:25','pdf',651,1,0,'d57f9a9c7357bf212c0575e105f5be33','教育部学籍在线验证报告_李洪辰.pdf','http://124.71.166.37:9091/file/audit/b63a127f8aeb455eb9328dc09d75e2cd.pdf','',3);
/*!40000 ALTER TABLE `auditbooks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bookUploader`
--

DROP TABLE IF EXISTS `bookUploader`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bookUploader` (
  `uploaderType` enum('system','user') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'system' COMMENT '上传者类型，system表示管理员，user表示普通用户',
  `userId` int NOT NULL,
  `bookId` int NOT NULL,
  PRIMARY KEY (`uploaderType`,`userId`,`bookId`) USING BTREE,
  KEY `bookUploader_books_id_fk` (`bookId`) USING BTREE,
  CONSTRAINT `bookUploader_books_id_fk` FOREIGN KEY (`bookId`) REFERENCES `books` (`bookid`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookUploader`
--

LOCK TABLES `bookUploader` WRITE;
/*!40000 ALTER TABLE `bookUploader` DISABLE KEYS */;
/*!40000 ALTER TABLE `bookUploader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `books` (
  `bookid` int NOT NULL AUTO_INCREMENT,
  `bookname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `publisher` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `isbn` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `coverimage` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `likes` int DEFAULT '0',
  `favorites` int DEFAULT '0',
  `releasetime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `size` bigint DEFAULT NULL,
  `isdelete` tinyint(1) DEFAULT NULL,
  `enable` tinyint(1) DEFAULT NULL,
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `filename` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `downloads` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`bookid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (60,'C++沉思录','Andrew Koenig Barbara Moo','人民邮电出版社','978-7-115-17178-8','本书基于作者在知名技术杂志发表的技术文章、世界各地发表的演讲以及斯\n坦福大学的课程讲义整理、写作而成，融聚了作者10多年C++程序生涯的真知\n灼见。','http://124.71.166.37:9091/file/bookpic/5cdfea18ee8e4e3884bf2f502c3e035e.png','计算机科学与技术',2,4,'2023-05-16 02:42:14','pdf',15841,0,1,'5a1e0f921b078977196532dd5004a998','C++沉思录.pdf','http://124.71.166.37:9091/file/8a62997913fe4a05810a9a1f913e2a3c.pdf',1),(61,'Docker经典实例','Sebastien Goasguen','中国工信出版集团','978-7-115-44656-5','本书结构明晰，示例丰富详实，是全面实用的Docker人门教程。作者全面介绍了Docker\n相关各种工具和平台，函盖网络、镜像管理、配置以及包括Kubernetes和Mesos在内的编排和\n调度生态系统，对私有云和公有云上部署的应用程序都给出了丰富实用的解决方案和示例。\n本书适合运维人员、系统管理员和开发人员阅读。','http://124.71.166.37:9091/file/bookpic/106df500b6ca457cbe334ebb78966813.png','计算机科学与技术',0,9,'2023-05-16 03:08:37','pdf',10350,0,1,'8772f3454889d86ea9a16de9616cb01f','[图灵程序设计丛书].Docker经典实例.pdf','http://124.71.166.37:9091/file/4996c92bdd5e4a4a80d0586c2c5e842c.pdf',2),(62,'算法的乐趣','王晓华','中国工信出版集团','978-7-115-38537-6','本书从一系列有趣的生活实例出发，全面介绍了构造算法的基础方法及其广泛应用，生动地展现了算\n法的趣味性和实用性。金书分为两个部分，第一部分介绍了算法的概念、常用的算法结构以及实现方法，\n第二部分介绍了算法在各个领域的应用，如物理实验、计算机图形学、数字音频处理等。其中，既有各种\n大名鼎鼎的算法，如神经网络、遗传算法、离散傅里叶变换算法及各种插值算法，也有不起眼的排序和概\n率计算算法。讲解浅显易懂而不失深度和严谨，对程序员有很大的启发意义。书中所有的示例都与生活息\n息相关，淋漓尽致地展现了算法解决问题的本质，让你爱上算法，乐在共中。\n本书适合软件开发人员、编程和算法爱好者以及计算机专业的学生阅读。','http://124.71.166.37:9091/file/bookpic/1b06388f57b34737a6d5d592917a4297.png','计算机科学与技术',0,0,'2023-05-16 05:11:35','pdf',8565,0,1,'a1b3af991e9623e0ea426761ca273bb9','算法的乐趣.revise7.pdf','http://124.71.166.37:9091/file/8ba9fa1cf753488b83b963b4a62ce557.pdf',0),(63,'第一行代码 Android 第2版','郭霖','人民邮电出版社','978-7-115-43978-9','本书被广大Android开发者誉为“Android学习第一书”。全书系统全面、循序渐进地介绍了Android\n软件开发的必备知识、经验和技巧。\n第2版基于Android7.0对第1版进行了全面更新，将所有知识点都在最新的Android系统上进行重新\n适配，使用全新的Android Studio开发工具代替之前的Eclipse,并添加了对Material Design、运行时权限、\nGradle、RecyclerView、百分比布局、OkHttp、Lambda表达式等全新知识点的详细讲解。\n本书内容通俗易懂，由栈入深，既是Android初学者的人门必备，也是Android开发者的进阶首选。','http://124.71.166.37:9091/file/bookpic/9cde69e582e242a0933d90ddca77a522.png','计算机科学与技术',0,1,'2023-05-16 05:31:54','pdf',53964,0,1,'1863da93c4aa758f57a9c6c6e60e772e','第一行代码 Android 第2版.pdf','http://124.71.166.37:9091/file/7cbefbb3bb154092bc6ca5df6d828533.pdf',0),(64,'MySQL与MariaDB学习指南','Russell J.T.Dyer','中国工信出版集团','978-7-115-43978-10','本书使读者不仅能够深人了解M小ySQL这种主流数据库，还能金面掌握开原数据库新秀\nMariaDB的使用方法。书中内容由浅至深、层层深入，从分步介绍如何安装MySQL和MariaDB,\n到以构的观鸟网站为例，详解数据库的各种操作。具体内容包括：数据库的结构；数据的插入，\n选取、更新、删除、连接和子查询；字符串函数、日期和时间函数、聚合函数与数值函数等。最后\n一个部分从更高的角度介绍数据库的管理，内容涉及用户账号及权限、数据库的备份与恢复，以\n及利用应用编程接口结合C、Perl,PHP、Python、Ruby等不同语言与数据库交互。\n本书面向想要从头开始学习并快速掌握数据库核心知识与实践方法的读者。','http://124.71.166.37:9091/file/bookpic/9879d1f9b1f9405486f778837a52cfcc.png','计算机科学与技术',0,1,'2023-05-16 06:41:04','pdf',9161,0,1,'98870a33e252f6199485f9c70b7ffb9c','[图灵程序设计丛书].MySQL与MariaDB学习指南.pdf','http://124.71.166.37:9091/file/b4c2c4f58e6c4323bde31e554e56986e.pdf',0),(65,'Go语言编程','许式伟 吕桂华','人民邮电出版社','978-7-115-29036-6','本书首先引领读者快速浏览G0语言的金貌，迅速消除读者对这门语言的陌生感，然后循序渐进地介绍\n了G0语言的面向过程和面向对象的编程语法，其中穿插了一些与共他主流语言的比较以让读者理解G0语\n言的设计动机，接着探讨了G0语言最为重要的并行编程方法，之后介绍了网络编程、工程管理、安全编程、\n开发工具等非语法相关但非常重要的内容，最后为一系列关于G0语言的文章，可以帮助读者更深入了解这\n门全新的语言。\n本书适合所有层次的开发者阅读。','http://124.71.166.37:9091/file/bookpic/e4ee26d112394c8c95cd36cd47888340.png','计算机科学与技术',0,1,'2023-05-16 09:45:59','pdf',9159,0,1,'d4828cbf6e8d727fdb84067b35aba335','Go语言编程.pdf','http://124.71.166.37:9091/file/ff5006385f154712a80d20561f570765.pdf',0),(66,'程序员的自我修养—链接、装载与库--书签目录','俞甲子等','电子工业出版社','978-7-121-08511-6','本书主要介绍系统软件的运行机制和原理，涉及在Windows和Linux两个系统平台上，一个应用程\n序在编译、链接和运行时刻所发生的各种事项，包括：代码指令是如何保存的，库文件如何与应用程序\n代码静态链接，应用程序如何被装载到内存中并开始运行，动态链接如何实现，CC++运行库的工作原\n理，以及操作系统提供的系统服务是如何被调用的。每个技术专题都配备了大量图、表和代码实例，力\n求将复杂的机制以简洁的形式表达出来。本书最后还提供了一个小巧且跨平台的C/C++运行库MiniCRT,\n综合展示了与运行库相关的各种技术。','http://124.71.166.37:9091/file/bookpic/c13d6e39a39c4ec79fe41a3145cacaf7.png','计算机科学与技术',1,2,'2023-05-16 09:49:50','pdf',31252,0,1,'5093cb6a98d1141032d4f4522bc4c4c6','程序员的自我修养—链接、装载与库--书签目录.pdf','http://124.71.166.37:9091/file/4ece4d0ab1394993ad77a9051a59d6d1.pdf',0),(67,'代码之外的功夫：程序员精进之路','Gregory T.Brown','人民邮电出版社','978-7-115-47837-5','本书虽然面向程序员，却不包含代码。在作者看来，90%的程序设计工作都不需要写代码；\n程序员不只是编程专家，其核心竞争力是利用代码这一工具解决人类社会的常见问题。以此作为出\n发点，作者精心构思了8个故事，以情景代人的方式邀请读者思考代码之外的关键问题：软件开\n发工作如何从以技术为中心转为以人为本？透过故事主人公的视角，读者能此较自己与书中角色的\n差异，发现决策过程的瑕疵，提升解决问题的综合能力。\n书中的做事涵盖程序员的整个软件开发生涯，但经过了浓缩，可供所有软件开发人员快速阅读。','http://124.71.166.37:9091/file/bookpic/213c190cac31495db75493e3a3f16265.png','计算机科学与技术',0,1,'2023-05-16 09:55:16','pdf',11630,0,1,'c66dc4aa56128c51e838080564914199','代码之外的功夫：程序员精进之路.pdf','http://124.71.166.37:9091/file/2632f680a2b1418586c3f6808597c1a6.pdf',0),(68,'算法图解','Aditya Bhargava','人民邮电出版社','978-7-115-44763-0','本书示例丰富，图文并茂，以简明易懂的方式阐释了算法，旨在帮助程序员在日常项目中更好地利用\n算法为软件开发助力。前三章介绍算法基础，包括二分查找、大O表示法、两种基本的数据结构以及递归\n等。余下的篇幅将主要介绍应用广泛的算法，具体内容包括：面对具体问题时的解决技巧，比如何时采用\n贪婪算法或动态规划：散列表的应用：图算法：K最近邻算法。\n本书适合所有程序员、计算机专业相关师生以及对算法感兴趣的读者。','http://124.71.166.37:9091/file/bookpic/f1ab2f645094419987d0a49c234ac67a.png','计算机科学与技术',0,1,'2023-05-16 09:57:22','pdf',17498,0,1,'b4a7b8c2975dc22d6ef9292c9fe5486c','算法图解.pdf','http://124.71.166.37:9091/file/32a838a7df114989b52717786fad2b16.pdf',0),(69,'Docker开发指南','Adrian Mouat','人民邮电出版社','978-7一115-44957-3','Docker 容器轻量和可移植的特性尤其适用于动态和分布式的环境，它的兴起给软件开发流\n程带来了一场革命。本书对 Docker 进行了全面讲解，包括开发、生产以至维护的整个软件生命\n周期，并对其中可能出现的一些问题进行了探讨，如软件版本差异、开发环境与生产环境的差异、\n系统安全问题，等等。\n本书适合软件开发者、运维工程师和系统管理员，尤其是对 DevOps 模式感兴趣的读者','http://124.71.166.37:9091/file/bookpic/949af9910da34dd4bb3279e5e51e63ba.jpg','图灵程序设计书',0,0,'2023-05-20 03:10:41','pdf',9580,0,1,'eac65e1a6740255c6ed201838ec9d240','[图灵程序设计丛书].Docker开发指南.pdf','http://124.71.166.37:9091/file/375bdf6300bf406f836156457d3db09f.pdf',0),(70,'MySQL与MariaDB学习指南','Russell J.T. Dyer','人民邮电出版社','978-7-115-43571-2','本书使读者不仅能够深入了解 MySQL 这种主流数据库，还能全面掌握开源数据库新秀\nMariaDB 的使用方法。书中内容由浅至深、层层深入，从分步介绍如何安装 MySQL 和 MariaDB，\n到以虚构的观鸟网站为例，详解数据库的各种操作。具体内容包括：数据库的结构 ；数据的插入、\n选取、更新、删除、连接和子查询；字符串函数、日期和时间函数、聚合函数与数值函数等。最后\n一个部分从更高的角度介绍数据库的管理，内容涉及用户账号及权限、数据库的备份与恢复，以\n及利用应用编程接口结合 C、Perl、PHP、Python、Ruby 等不同语言与数据库交互。\n本书面向想要从头开始学习并快速掌握数据库核心知识与实践方法的读者。','http://124.71.166.37:9091/file/bookpic/065784a1b53e4a73beb6ed13d457a841.jpg','图灵程序设计丛书',1,1,'2023-05-20 03:14:48','pdf',9161,0,1,'98870a33e252f6199485f9c70b7ffb9c','[图灵程序设计丛书].MySQL与MariaDB学习指南.pdf','http://124.71.166.37:9091/file/dd51e6ab92c543328c5086c1a6aaac52.pdf',0),(71,'奔跑吧,程序员','叶夫根尼·布里克曼','人民邮电出版社','978-7-115-48366-9','本书以软件工程师出身的创业者角度，全面介绍了创业公司该如何打造产品、实现技术\n和建立团队，既是为创业者打造的一份实用入门指南，又适合所有程序员系统认识 IT 行业。\n书中内容分为三部分——技术、产品和团队，详细描绘创业的原始景象，具体内容包括 ：创业\n点子、产品设计、数据与营销、技术栈的选择、整洁的代码、软件交付、创业文化、招兵买马，\n等等。\n本书适合所有程序员，尤其是准备创业的技术人员','http://124.71.166.37:9091/file/bookpic/356763f846b144a1ae72a97de054627d.jpg','图灵程序设计丛书',0,0,'2023-05-20 03:20:14','pdf',12875,0,1,'8c0ba3bddf9e337fa74d6d0563d22043','[图灵程序设计丛书].奔跑吧，程序员：从零开始打造产品、技术和团队.pdf','http://124.71.166.37:9091/file/cd895e49327240d78fc8c999566d3c82.pdf',0),(72,'Go语言编程','许式伟  吕桂华等','人民邮电出版社','978-7-115-29036-6','本书首先引领读者快速浏览 Go 语言的全貌，迅速消除读者对这门语言的陌生感，然后循序渐进地介绍\n了 Go 语言的面向过程和面向对象的编程语法，其中穿插了一些与其他主流语言的比较以让读者理解 Go 语\n言的设计动机，接着探讨了 Go 语言最为重要的并行编程方法，之后介绍了网络编程、工程管理、安全编程、\n开发工具等非语法相关但非常重要的内容，最后为一系列关于 Go 语言的文章，可以帮助读者更深入了解这\n门全新的语言。\n本书适合所有层次的开发者阅读。','http://124.71.166.37:9091/file/bookpic/7ce9eecc13d84ed5af94f6e58f032317.jpg','图灵原创',0,0,'2023-05-20 04:03:46','pdf',9159,0,1,'d4828cbf6e8d727fdb84067b35aba335','[图灵原创].Go语言编程.pdf','http://124.71.166.37:9091/file/f7d7604b817e4dc3b1f17a03244db4b1.pdf',0),(73,'0day安全:软件漏洞分析技术','王清','电子工业出版社','978-7-121-13396-1 ','本书分为 5 篇 33 章，系统、全面地介绍了 Windows 平台缓冲区溢出漏洞的分析、检测与防护。第一\n篇为漏洞 exploit 的基础理论和初级技术，可以引领读者迅速入门；第二篇在第一篇的基础上，结合国内外\n相关研究者的前沿成果，对漏洞技术从攻、防两个方面进行总结；第三篇站在安全测试者的角度，讨论了\n几类常用软件的漏洞挖掘方法与思路；第四篇则填补了本类书籍在 Windows 内核安全及相关攻防知识这个\n神秘领域的技术空白；第五篇以大量的 0 day 案例分析，来帮助读者理解前四篇的各类思想方法。\n本书可作为网络安全从业人员、黑客技术发烧友的参考指南，也可作为网络安全专业的研究生或本科\n生的指导用书','http://124.71.166.37:9091/file/bookpic/6485370eb12744cd83b42f3f8ef92e67.jpg','软件安全',0,0,'2023-05-20 04:06:27','pdf',30790,0,1,'02e5bcc5f05379b24f186d2b0af4c89a','0day2.pdf','http://124.71.166.37:9091/file/011a0157f86d4610b64beef5dbb6c80e.pdf',0),(74,'C++ Concurrency in Action','ANTHONY WILLIAMS','y Manning Publications Co. All rights reserved','9781617294693','No part of this publication may be reproduced, stored in a retrieval system, or transmitted, in \nany form or by means electronic, mechanical, photocopying, or otherwise, without prior written \npermission of the publisher.\nMany of the designations used by manufacturers and sellers to distinguish their products are \nclaimed as trademarks. Where those designations appear in the book, and Manning \nPublications was aware of a trademark claim, the designations have been printed in initial caps \nor all caps','http://124.71.166.37:9091/file/bookpic/9809b9e5f6d64ec5b73f5cd50da9b561.jpg','C++',0,1,'2023-05-20 04:09:48','pdf',6614,0,1,'964d3bbccec3e9d5a845b0bb8ba56eb8','C++ Concurrency in Action.pdf','http://124.71.166.37:9091/file/a791d4f05b5b4eabbf068fb19daf0efb.pdf',0),(75,'C++程序设计语言（第四部分：标准库）','Bjarne Stroustrup','机械工业出版社','978-7-111-54439-5','《C++程序设计语言》(原书第4版)是C++领域最经典的参考书，介绍了C++11的各项新特性和新功能。全书共分四部分。第一部分(第1～5章)是引言，包括C++的背景知识，C++语言及其标准库的简要介绍;第二部分(第6~15章)介绍C++的内置类型和基本特性，以及如何用它们构造程序﹔第三部分(第16~29章)介绍C++的抽象机制及如何用这些机制编写面向对象程序和泛型程序;第四部分(第30 ~44章)概述标准库并讨论一些兼容性问题。\n由于篇幅问题，原书中文版分两册出版，分别对应原书的第一至三部分和第四部分，这一册为第四部分。\n本书适合计算机及相关专业本科生用作C++课程的教材，也适合C++程序设计新手和开发人员阅读。\n','http://124.71.166.37:9091/file/bookpic/6813fdac290241e58d6829d71b827aad.jpg','C++',0,0,'2023-05-20 04:16:39','pdf',87955,0,1,'0beb90bb1d192394c238c44b9f21c0cb','C++ 程序设计语言：第4部分 标准库（原书第4版 (Bjarne Stroustrup) (z-lib.org).pdf','http://124.71.166.37:9091/file/7fb0d97d60934587ba81eca7eb1389f4.pdf',0),(76,'Effictive C++ 第三版','Scott Meyers','电子工业出版社','7-121-02909-x','有人说C++程序员可以分成两类，读过 EffectiveC++的和没读过的。世界顶级C++大师Scot Meyers 成名之作的第三版的确当得起这样的评价。当您读过这本书之后，就获得了迅速提升自己C++功力的一个契机。\n在国际上﹐本书所引起的反响﹐波及整个计算技术出版领域﹐余音至今未绝。几乎在所有C+书籍的推荐名单上﹐本书都会位于前三名。作者高超的技术把握力、独特的视角、诙诸轻松的写作风格、独具匠心的内容组织﹐都受到极大的推崇和仿效。这种奇特的现象﹐只能解释为人们对这本书衷心的赞美和推崇。\n这本书不是读完一遍就可以束之高阁的快餐读物，也不是用以解决手边问题的参考手册，而是需要您去反复阅读体会的，C++是真正程序员的语言，背后有着精深的思想与无与伦比的表达能力，这使得它具有类似宗教般的魅力。希望这本书能够帮助您跨越C+的重重险阻，领略高处才有的壮美风光，做一个成功而快乐的C++程序员。\n','http://124.71.166.37:9091/file/bookpic/60c384081901449cb79c8cfe460dfeeb.jpg','C++',0,0,'2023-05-20 04:28:46','pdf',59520,0,1,'80268ce054b99ed275e06cdad3339d48','Effective C++ (梅耶, 侯捷) (z-lib.org).pdf','http://124.71.166.37:9091/file/1b676cd0ab83463dbd88d0e7a9ae1287.pdf',0),(77,'flex and bison','Jobn Levine','东南大学出版社','978-7-5641-2605-6','为了满足读者对网络和软件技术知识的迫切需求，世界著名计算机图书出版机构O\'Reilly Media,Inc.授权东南大学出版社，翻译出版一批该公司久负盛名的英文经典技术专著。\nO\'Reilly Media, Inc.是世界上在 Unix、X、Internet和其他开放系统图书领域具有领导地位的出版公司，同时也是联机出版的先锋。\n从最畅销的The Whole Internet User\'s Guide & Catalog (被纽约公共图书馆评为20世纪最重要的50本书之一）到GNN（最早的Internet门户和商业网站)，再到WebSite(第一个桌面PC的Web服务器软件），O\'Reilly Media,Inc.--直处于Internet发展的最前沿。\n许多书店的反馈表明，O\'Reilly Media,Inc.是最稳定的计算机图书出版商—一每一本书都一版再版。与大多数计算机图书出版商相比，O\'Reilly Media,Inc.具有深厚的计算机专业背景，这使得O\'Reilly Media,Inc.形成了一个非常不同于其他出版商的出版方针。O\'Reilly Media,Inc.所有的编辑人员以前都是程序员，或者是顶尖级的技术专家。O\'Reilly Media，Inc.还有许多固定的作者群体——他们本身是相关领域的技术专家、咨询专家，而现在编写著作，O\'Reilly Media，Inc.依靠他们及时地推出图书。因为O\'ReillyMedia,Inc.紧密地与计算机业界联系着，所以O\'Reilly Media, Inc.知道市场上真正需要什么图书。\n','http://124.71.166.37:9091/file/bookpic/ad0d5f0061034c999939c5330eb7cfbd.jpg','编译原理',0,0,'2023-05-20 04:30:54','pdf',15092,0,1,'a1e1b0f2f67d49f385fc1c767e9da045','flex与bison中文版.pdf','http://124.71.166.37:9091/file/417ee77ed2664300b0ef4b4155a89051.pdf',0),(78,'GitHub入门与实践','大琢弘记','人民邮电出版社','978-7-115-39409-5','本书从Git的基本知识和操作方法入手，详细介绍了GitHub的各种功能，GitHub\n与其他工具或服务的集成，使用GitHub的开发流程以及如何将GitHub引入到企业中。\n在讲解GitHub的代表功能Pull Request时，本书专门搭建了供各位读者实践的仓\n库，邀请各位读者进行Pull Request并共同维护。\n本书旨在指导各位读者如何在开发现场使用GitHub进行高效开发，适合所有\n想要使用GitHub进行开发的程序员或团队阅读。','http://124.71.166.37:9091/file/bookpic/c7df40d896084e0a92b26f219162529d.jpg','GitHub',0,0,'2023-05-20 09:56:40','pdf',12811,0,1,'3939eb624aa6840f8fda5e8dab3a0c68','GitHub入门与实践 (大塚弘记) (z-lib.org).pdf','http://124.71.166.37:9091/file/fb7edab390d3444fb4b5a77e387f17cc.pdf',0),(79,'Java程序员进阶之路','沉默王⼆','网络','undefined','Java 程序员进阶之路，本专栏旨在为 Java 爱好者提供⼀个清晰详细的学习教程，专栏⻛趣幽\n默、通俗易懂，对 Java 爱好者极度友好和舒适\n\"\n，内容包括但不限于 Java 基础、Java 集合框\n架、Java IO、Java 并发编程、Java 虚拟机、Java 企业级开发（Maven、Git、SSM、Spring\nBoot）等核⼼知识点。。','http://124.71.166.37:9091/file/bookpic/2a998138b0b74a52802424bebe279134.jpg','java',0,0,'2023-05-20 09:59:32','pdf',39376,0,1,'df41d31447e8cf926d2f68b75036dcfc','Java 程序员进阶之路（暗黑版）.pdf','http://124.71.166.37:9091/file/102b9bb3bbd24ed191c1c457b866cb40.pdf',0),(80,'Java核心技术卷2高级特性','Cay S. Horstmann','Prentice Hall','9780134177878','This is the Rough Cut version of the printed book.\nFully updated to reflect Java SE 8 language changes, Core Java®, Volume II–Advanced \nFeatures, Tenth Edition, is the definitive guide to Java’s most powerful features for \nenterprise and desktop application development.\nDesigned for serious programmers, this reliable, unbiased, no-nonsense tutorial \nilluminates advanced Java language and library features with thoroughly tested code \nexamples. As in previous editions, all code is easy to understand and displays modern \nbest-practice solutions to the real world challenges faced by professional developers.\nVolume II quickly brings you up-to-speed on key Java SE 8 features and APIs. All code examples \nare updated to reflect these enhancements. Complete descriptions of new language and \nplatform features are highlighted and integrated with insightful explanations of advanced \nJava programming techniques. ','http://124.71.166.37:9091/file/bookpic/8ba0e88435df4f708f0a43d4f2fd487a.jpg','java',1,1,'2023-05-20 10:01:34','pdf',15374,0,1,'b1c1b558c1c4c17988b9c7a70174e8b4','Java核心技术卷2高级特性原书第10版.pdf','http://124.71.166.37:9091/file/384e2e9f68534153968d84efe07c5c76.pdf',0),(81,'LinuxShell脚本攻略第三版','Clif Flynt','人民邮电出版社','978-7-115-47738-5','本书结合丰富的实际案例介绍了如何利用 shell 命令实现与 Linux 操作系统的高效沟通，具体内容包括：\n各类日常任务以及如何利用 shell 命令更快速地解决问题 ；编写脚本从 Web 中挖掘数据并进行处理 ；在脚\n本中与简单的 Web API 进行交互 ；任务的执行及自动化 ；创建及维护文件和文件夹归档，利用 shell 进行\n压缩和加密。作为第 3 版，书中讲解了最新的 Linux 发行版中加入的令人激动的新特性，帮助你完成从未\n想到过的功能。\n本书适合 Linux 系统管理员和程序员阅读，是编写 Shell 脚本的绝佳参考资料。','http://124.71.166.37:9091/file/bookpic/9a6bffef97ba4a10a399150fa59280b6.jpg','图灵程序设计丛书',0,1,'2023-05-20 10:03:55','pdf',10396,0,1,'95e76c0a94518ff3f8ba8f0bf3f91a9f','Linux Shell脚本攻略.第3版.pdf','http://124.71.166.37:9091/file/9cc20df75d1641199e329ce6686df214.pdf',0),(82,'Linux/Unix系统编程手册','Michael Kerrisk','人民邮电出版社','978-7-115-32867-0','本书是介绍 Linux 与 UNIX 编程接口的权威著作。Linux 编程资深专家 Michael Kerrisk 在\n书中详细描述了 Linux/UNIX 系统编程所涉及的系统调用和库函数，并辅之以全面而清晰的代\n码示例。本书涵盖了逾 500 个系统调用及库函数，并给出逾 200 个程序示例，另含 88 张表格\n和 115 幅示意图。\n本书总共分为 64 章，主要讲解了高效读写文件，对信号、时钟和定时器的运用，创建进\n程、执行程序，编写安全的应用程序，运用 POSIX 线程技术编写多线程程序，创建和使用共\n享库，运用管道、消息队列、共享内存和信号量技术来进行进程间通信，以及运用套接字 API\n编写网络应用等内容。','http://124.71.166.37:9091/file/bookpic/a205f0d2071d4e27bf7eb0cc78dd2a1d.jpg','Linux',0,0,'2023-05-20 10:06:06','pdf',30146,0,1,'4b83f6c2f3f5c73ff81391a98679e8ce','Linux-UNIX系统编程手册（上、下册） (Michael Kerrisk) (z-lib.org).pdf','http://124.71.166.37:9091/file/4598e52eb9e34aca8a1fd0b9202fb216.pdf',0),(83,'Linux命令行与shell脚本编程大全第三版','Richard Blum Christine Bresnahan','人民邮电出版社','978-7-115-42967-4','这是一本关于 Linux 命令行与 shell 脚本编程的全方位教程，主要包括四大部分 ：Linux 命令行，shell\n脚本编程基础，高级 shell 脚本编程，如何创建实用的 shell 脚本。本书针对 Linux 系统的最新特性进行了\n全面更新，不仅涵盖了详尽的动手教程和现实世界中的实用信息，还提供了与所学内容相关的参考信息和\n背景资料。通过本书的学习，你将轻松写出自己的 shell 脚本。\n本书适合 Linux 程序设计人员阅读','http://124.71.166.37:9091/file/bookpic/7e75b3f97ea54c5aa1d39fc2419f3b62.jpg','图灵程序设计丛书',0,0,'2023-05-20 10:08:43','pdf',21954,0,1,'c71ca8a8e6158ea5dc37bc19cc0c6b30','Linux命令行与shell脚本编程大全.第3版 (布鲁姆，布雷斯纳汉) (z-lib.org).pdf','http://124.71.166.37:9091/file/e86ead73b1b840c4bc7800fb3def6033.pdf',0),(84,'Linux内核完全注解','赵烔','网络','undefined','本书对 Linux 操作系统早期内核（V0.12）全部源代码文件进行了详细全面的注释和说明，旨在让读者\n能够在尽量短的时间内对 Linux 的工作机理获得全面而深刻的理解，为进一步学习和研究现代 Linux 系统打\n下坚实的基础。虽然分析的版本很低，但该内核已能够正常编译运行，并且其中已包括了 LINUX 工作原理\n的精髓。书中首先概要地介绍了 Linux 内核发展历史，说明了各内核版本之间的主要区别和改进方面，给出\n了选择 0.12 版内核源代码作为研究对象的原因。然后给出了阅读内核源代码所需的相关基础知识，概要介\n绍了运行 Linux 系统的 PC 机硬件组成结构、编制内核使用的汇编语言和 C 语言扩展部分，并且重点说明了\n80X86 处理器在保护模式下运行的编程方法。接着介绍了内核代码概况，给出了内核源代码目录树结构，并\n依据该组织结构对所有内核程序和文件进行了详细描述和说明。为了加深读者对内核工作原理的理解，书\n中最后一章给出了多个相关运行调试试验。书中所有相关资料和信息均可从网站 www.oldlinux.org 下载。\n 本书适合作为高校计算机专业学生学习操作系统课程的辅助和实践教材，也适合 Linux 爱好者作为学习\n内核工作原理的自学参考书籍，还可作为一般技术人员开发嵌入式系统时的参考书。\n','http://124.71.166.37:9091/file/bookpic/37df9b8bf3e1444c87f803a72f9fac45.jpg','Linux',0,2,'2023-05-20 10:10:52','pdf',15519,0,1,'81261fdbebb15eb23d36e4206db35185','Linux内核完全注释2021版中文(CLK-5.0.1-WithCover).pdf','http://124.71.166.37:9091/file/93afe304dc0749bbbc8370c15bb05e5b.pdf',0),(85,'MySQL必知必会','Ben Forta','人民邮电出版社','978-7-115-19112-0','MySQL 是世界上最受欢迎的数据库管理系统之一。书中从介绍简单的数据\n检索开始，逐步深入一些复杂的内容，包括联结的使用、子查询、正则表达式\n和基于全文本的搜索、存储过程、游标、触发器、表约束，等等。通过重点突\n出的章节，条理清晰、系统而扼要地讲述了读者应该掌握的知识，使他们不经\n意间立刻功力大增。\n本书注重实用性，操作性很强，适用于广大软件开发和数据库管理人员学\n习参考','http://124.71.166.37:9091/file/bookpic/1dfab807ebf84471a693fed1607720c8.jpg','MySQL',0,0,'2023-05-20 10:12:15','pdf',5876,0,1,'68eaf713ff6d2297e4a40e8ab8519aaa','MySQL必知必会 = MySQL crash course (Ben Forta 著 刘晓霞, 钟鸣 译) (z-lib.org).pdf','http://124.71.166.37:9091/file/f41229989f4442a9a28316c30dc26778.pdf',0),(86,'ProGit','Scott Chacon and Ben Straub','网络','undefined','This chapter is about getting started with Git. We will begin at the beginning by explaining some background on \nversion control tools, then move on to how to get Git running on your system and finally how to get it set up to start \nworking with. At the end of this chapter you should understand why Git is around, why you should use it, and you \nshould be all set up to do so.','http://124.71.166.37:9091/file/bookpic/9bc4017fdb8f490d80bed79c8dfc7568.jpg','Git',0,0,'2023-05-20 10:14:33','pdf',6172,0,1,'fce87ed5bb4598732dc5bd071cd733b4','Pro Git Everything You Need to Know About Git.pdf','http://124.71.166.37:9091/file/b19bbdb080c04d7d84a226edf2076ac7.pdf',0),(87,'SpringBoot实战第五版','Craig Walls','人民邮电出版社','978-7-115-43314-5','本书以 Spring 应用程序开发为中心，全面讲解如何运用 Spring Boot 提高效率，使应用程序的开发和管\n理更加轻松有趣。作者行文亲切流畅，以大量示例讲解了Spring Boot在各类情境中的应用，内容涵盖起步依赖、\nSpring Boot CLI、Groovy、Grails、Actuator。对于 Spring Boot 开发应用中较为繁琐的内容，附录奉上整理完\n毕的表格，一目了然，方便读者查阅。\n本书适合全体 Java 开发人员','http://124.71.166.37:9091/file/bookpic/1270b74b360a4b55b8f6cd23c7810f13.jpg','SpringBoot',0,0,'2023-05-20 10:16:04','pdf',13784,0,1,'05f5c9d25c0069abd7c6afd3b455effd','Spring实战（第5版）【文字版】.pdf','http://124.71.166.37:9091/file/f8b1ee68db1644d58defaba75b3854c8.pdf',0),(92,'SQL进阶教程','mick','人民邮电出版社','978-7-115-47052-2','本书是《SQL基础教程》作者MICK为志在向中级进阶的数据库工程师编写的一本SQL技能提升指南。\n全书可分为两部分，第一部分介绍了SQL语言不同寻常的使用技巧，带领读者从SQL常见技术，比如\nCASE表达式、自连接、HAVING子句、外连接、关联子查询、EXISTS……去探索新发现。这部分不仅穿插\n讲解了这些技巧背后的逻辑和相关知识，而且辅以丰富的示例程序，旨在帮助读者提升编程水平；第二部\n分着重介绍关系数据库的发展史，把实践与理论结合起来，旨在帮助读者加深对关系数据库和SQL语言的\n理解。此外，每节末尾均设置有练习题，并在书末提供了解答，方便读者检验自己对书中知识点的掌握程度。\n　　本书适合具有半年以上SQL使用经验、已掌握SQL基础知识和技能、希望提升自己编程水平的读者阅读。','http://124.71.166.37:9091/file/bookpic/e45c3a9c7a1748d0ad787f94753614d6.jpg','SQL',0,0,'2023-05-20 12:15:15','pdf',5897,0,1,'bb7a87019cc7f23d8935d1cff7e1493c','SQL进阶教程.pdf','http://124.71.166.37:9091/file/facd008db0574d839fc446ac76356353.pdf',0),(93,'TcpSocket编程','Jesse Storimer','人民邮电出版社','978-7-115-33052-9','本书通过循序渐进的方式，从最基础的概念到高级别的 Ruby 封装器，再到更\n复杂的应用，提供了开发成熟且功能强大的应用程序所必备的知识和技巧，帮助读者\n掌握在 Ruby 语言环境下，用套接字实现项目开发的任务和技术。\n本书适合对 TCP 套接字感兴趣的读者阅读','http://124.71.166.37:9091/file/bookpic/a34ab4f3443b4e10a41fae733260c9be.jpg','计算机网络',0,0,'2023-05-20 12:18:33','pdf',4563,0,1,'8227e278dc41ededb11c525f5dcf24fd','TCP Sockets编程.pdf','http://124.71.166.37:9091/file/3c7b1b0f86964d458942dca190e6b614.pdf',0);
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commits`
--

DROP TABLE IF EXISTS `commits`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commits` (
  `id` int NOT NULL AUTO_INCREMENT,
  `bookid` int DEFAULT NULL,
  `userid` int DEFAULT NULL,
  `text` text,
  `time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commits`
--

LOCK TABLES `commits` WRITE;
/*!40000 ALTER TABLE `commits` DISABLE KEYS */;
INSERT INTO `commits` VALUES (6,68,4,'sadasda','2023-05-18 08:10:53'),(7,68,4,'测试','2023-05-18 08:12:05'),(8,68,4,'qweqw','2023-05-18 08:15:54'),(11,68,4,'12asdsa','2023-05-18 09:27:14'),(12,68,4,'2131231231','2023-05-18 17:18:58'),(13,68,4,'test','2023-05-18 17:22:17'),(14,68,4,'测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试','2023-05-18 17:34:12'),(15,68,4,'测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试测试','2023-05-18 17:37:30'),(16,68,4,'wqeqwe','2023-05-18 17:38:48'),(17,68,4,'1231231231','2023-05-18 17:40:44'),(18,68,4,'nihao','2023-05-18 17:40:49'),(19,68,4,'1231231','2023-05-18 17:47:47'),(20,68,4,'12312311','2023-05-18 17:47:51'),(21,68,4,'12312311','2023-05-18 17:47:52'),(22,68,4,'12312311','2023-05-18 17:47:53'),(23,68,4,'12312311','2023-05-18 17:47:54'),(24,68,4,'12312311','2023-05-18 17:47:55'),(25,68,4,'asdas','2023-05-18 17:48:10'),(26,68,4,'asdas','2023-05-18 17:48:36'),(27,68,4,'asdas','2023-05-18 17:48:51'),(28,68,4,'asdas','2023-05-18 17:48:52'),(29,68,4,'asdas','2023-05-18 17:48:56'),(30,64,4,'测试','2023-05-19 06:54:49'),(31,64,4,'测试测试测试','2023-05-19 06:54:55'),(32,67,4,'测试','2023-05-19 06:55:24'),(33,64,4,'123123','2023-05-19 07:35:38'),(34,64,4,'21','2023-05-19 07:47:41'),(35,64,4,'21','2023-05-19 07:47:42'),(36,64,4,'21','2023-05-19 07:47:43'),(37,64,4,'21','2023-05-19 07:47:43'),(38,64,4,'21','2023-05-19 07:47:43'),(39,64,4,'21','2023-05-19 07:47:44'),(40,64,4,'21','2023-05-19 07:47:44'),(41,64,4,'21','2023-05-19 07:47:47'),(42,64,4,'21','2023-05-19 07:47:47'),(43,64,4,'21','2023-05-19 07:47:48'),(44,64,1,'真好','2023-05-19 07:58:51'),(45,64,1,'HHHHH','2023-05-19 08:00:57'),(46,64,1,'SADASDA','2023-05-19 08:01:14'),(47,64,1,'SADASDA','2023-05-19 08:02:12'),(48,64,1,'123213','2023-05-19 08:02:24'),(49,64,1,'ASDA','2023-05-19 08:04:47'),(50,64,1,'ASD','2023-05-19 08:05:05'),(51,64,1,'ASDAAA','2023-05-19 08:05:09'),(52,64,1,'ASDAAA','2023-05-19 08:05:13'),(53,64,1,'ASDAAACE','2023-05-19 08:05:27'),(54,64,4,'12321312','2023-05-19 09:10:25'),(55,68,3,'大声道','2023-05-19 09:35:08'),(56,68,3,'传达室传达室','2023-05-19 09:35:22'),(57,68,3,'大多数','2023-05-19 09:36:37'),(58,64,4,'sadasd','2023-05-19 09:36:49'),(59,68,3,'大多数','2023-05-19 09:37:12'),(60,64,4,'测试','2023-05-19 09:38:58'),(61,68,3,'测试','2023-05-19 09:40:17'),(62,68,3,'测试','2023-05-19 09:40:23'),(63,68,3,'测试','2023-05-19 09:40:39'),(64,66,4,'1231','2023-05-20 02:23:19'),(65,66,4,'12313123213','2023-05-20 02:23:23'),(66,66,1,'123123213','2023-05-20 02:26:30'),(67,66,1,'测试\n','2023-05-20 02:26:37'),(68,70,4,'asdas','2023-05-20 10:04:59'),(69,70,4,'asdas','2023-05-20 10:04:59'),(70,74,4,'ceshi','2023-05-21 03:31:11'),(71,74,1,'测试','2023-05-21 03:37:30'),(73,74,1,'测试','2023-05-21 03:47:20'),(74,60,0,'sad','2023-05-22 07:18:00'),(75,60,0,'saddas','2023-05-22 07:18:05'),(76,60,0,'sad','2023-05-22 07:18:11'),(77,84,3,'\n','2023-05-23 07:59:02'),(78,84,3,'d','2023-05-23 07:59:15'),(79,84,3,'s','2023-05-23 07:59:50'),(80,61,1,'qwe','2023-05-23 08:01:40'),(81,61,1,'qwe','2023-05-23 08:04:18'),(82,60,1,'qweqwe','2023-05-23 08:04:52'),(83,70,1,'qwe','2023-05-23 08:22:33'),(84,84,7,'test','2023-05-23 09:14:24'),(85,84,8,'qqwqe','2023-05-23 10:30:01'),(87,80,9,'sadasdjadha','2023-05-25 08:14:50');
/*!40000 ALTER TABLE `commits` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `relationship`
--

DROP TABLE IF EXISTS `relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `relationship` (
  `bookid` int NOT NULL,
  `isowned` tinyint DEFAULT '0' COMMENT '是否拥有',
  `isfavour` tinyint DEFAULT '0' COMMENT '是否收藏',
  `userid` int NOT NULL,
  `isuploader` tinyint DEFAULT '0' COMMENT '是否是上传者',
  `score` int DEFAULT NULL COMMENT '用户评分',
  `islike` tinyint DEFAULT '0' COMMENT '是否点赞',
  PRIMARY KEY (`bookid`,`userid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `relationship`
--

LOCK TABLES `relationship` WRITE;
/*!40000 ALTER TABLE `relationship` DISABLE KEYS */;
INSERT INTO `relationship` VALUES (60,1,0,1,1,5,1),(60,0,1,3,0,NULL,0),(60,0,1,9,0,NULL,0),(61,1,1,1,0,2,0),(61,0,1,3,0,NULL,0),(61,0,1,4,1,NULL,0),(62,0,0,3,0,NULL,0),(63,0,1,3,0,NULL,0),(64,0,1,3,0,NULL,0),(64,0,1,4,0,NULL,NULL),(65,0,1,3,0,NULL,0),(66,0,0,1,0,NULL,0),(66,0,1,3,0,NULL,0),(66,0,1,4,0,NULL,1),(67,0,1,3,0,NULL,0),(68,0,0,3,0,NULL,NULL),(68,0,1,8,0,NULL,0),(70,0,1,1,0,4,0),(70,0,0,4,0,NULL,1),(74,0,0,1,0,4,0),(74,0,0,4,0,2,0),(74,0,1,9,0,NULL,0),(80,0,0,1,0,NULL,0),(80,0,1,9,0,4,1),(81,0,0,2,0,NULL,0),(81,0,1,4,0,NULL,0),(84,0,1,7,0,NULL,0),(84,0,1,8,0,NULL,0),(93,0,0,9,0,4,0);
/*!40000 ALTER TABLE `relationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `test`
--

DROP TABLE IF EXISTS `test`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `test` (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `test`
--

LOCK TABLES `test` WRITE;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` VALUES ('lhc','lhc');
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `avatarurl` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `points` int DEFAULT '20',
  `freedownload` int DEFAULT '2',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'lhc','lhc','lhc@shu.edu.com','19109682868','http://124.71.166.37:9091/file/avartar/819a8ee01b564de19546f3941a861aef.jpg',187,2),(2,'yjr1','yjr1','yjr@shu.edu.cn','17774154541',NULL,0,2),(3,'zpf','zpf','zpf@shu.edu.cn','17698266260','http://124.71.166.37:9091/file/avartar/c282c9572c4745dfbe8ef34519a84e9f.jpg',0,2),(4,'zpf2','zpf2','zpf@qq.com','19109682868','http://124.71.166.37:9091/file/avartar/79adb2a4eca04fafa0b5c35c0b3a7bad.jpg',250,2),(9,'test','test','example@qq.com','19100000000','http://124.71.166.37:9091/file/avartar/219c2a067b604bdfac27a9c91a47ddea.jpg',15,2);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-31  9:30:41
