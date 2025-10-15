-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.4.3 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.8.0.6908
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- Dumping structure for table db_product.product
CREATE TABLE IF NOT EXISTS `product` (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `nama` varchar(255) NOT NULL,
  `harga` double NOT NULL,
  `kategori` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `sertifikasi` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table db_product.product: ~20 rows (approximately)
INSERT INTO `product` (`id`, `nama`, `harga`, `kategori`, `sertifikasi`) VALUES
	('P001', 'Batagor', 5000, 'Makanan', 'Halal'),
	('PRD001', 'Laptop ASUS', 8500000, 'Elektronik', 'SNI'),
	('PRD002', 'Mouse Wireless', 150000, 'Elektronik', 'Tidak Ada'),
	('PRD003', 'Keyboard Gaming', 450000, 'Elektronik', 'Tidak Ada'),
	('PRD004', 'Monitor 24 inch', 2200000, 'Elektronik', 'SNI'),
	('PRD005', 'Headset Gaming', 350000, 'Elektronik', 'SNI'),
	('PRD006', 'Smartphone Samsung', 4500000, 'Elektronik', 'SNI'),
	('PRD007', 'Charger USB-C', 85000, 'Elektronik', 'Eco-friendly'),
	('PRD008', 'Power Bank', 250000, 'Elektronik', 'Eco-friendly'),
	('PRD009', 'Webcam HD', 180000, 'Elektronik', 'SNI'),
	('PRD010', 'Speaker Bluetooth', 320000, 'Elektronik', 'SNI'),
	('PRD011', 'Tablet Android', 2800000, 'Elektronik', 'Eco-friendly'),
	('PRD012', 'Smartwatch', 1200000, 'Elektronik', 'Tidak Ada'),
	('PRD013', 'Flash Drive 32GB', 65000, 'Elektronik', 'SNI'),
	('PRD014', 'Hard Disk 1TB', 750000, 'Elektronik', 'SNI'),
	('PRD015', 'Router WiFi', 420000, 'Elektronik', 'SNI'),
	('PRD016', 'Kabel HDMI', 45000, 'Elektronik', 'Tidak Ada'),
	('PRD017', 'Printer Inkjet', 850000, 'Elektronik', 'SNI'),
	('PRD018', 'Scanner Document', 650000, 'Elektronik', 'SNI'),
	('PRD019', 'Cooling Pad', 130000, 'Elektronik', 'Tidak Ada'),
	('PRD020', 'Gaming Chair', 1800000, 'Elektronik', 'SNI');

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
