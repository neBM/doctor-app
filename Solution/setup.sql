SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

CREATE TABLE `accessRecords` (
  `id` int NOT NULL,
  `email` varchar(50) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `event` enum('VIEW_MESSAGES','VIEW_PATIENTS','VIEW_BOOKINGS','VIEW_VISTS','VIEW_NEWVIST','VIEW_WELCOMESCREEN') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `bookings` (
  `id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `doctor` varchar(50) NOT NULL,
  `patient` varchar(50) NOT NULL,
  `timestamp` timestamp NOT NULL,
  `duration` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `messages` (
  `id` int NOT NULL,
  `to` varchar(50) NOT NULL,
  `from` varchar(50) NOT NULL,
  `message` varchar(300) NOT NULL,
  `read` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `patientDetails` (
  `patientEmail` varchar(150) NOT NULL,
  `assignedDoctor` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `users` (
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) NOT NULL,
  `firstName` varchar(100) NOT NULL,
  `lastname` varchar(100) NOT NULL,
  `phoneNumber` varchar(30) NOT NULL,
  `address` varchar(150) NOT NULL,
  `Gender` varchar(5) NOT NULL,
  `dateOfbirth` timestamp NOT NULL,
  `type` enum('DOCTOR','PATIENT') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `visitDetails` (
  `id` int NOT NULL,
  `patientEmail` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prescriptionName` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `doctor` varchar(50) DEFAULT NULL,
  `timestamp` timestamp NULL DEFAULT NULL,
  `visitNotes` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `prescriptionnQuantity` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


ALTER TABLE `accessRecords`
  ADD PRIMARY KEY (`id`),
  ADD KEY `email` (`email`) USING BTREE;

ALTER TABLE `bookings`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `patient` (`patient`,`timestamp`),
  ADD KEY `doctor` (`doctor`);

ALTER TABLE `messages`
  ADD PRIMARY KEY (`id`),
  ADD KEY `to` (`to`),
  ADD KEY `from` (`from`);

ALTER TABLE `patientDetails`
  ADD PRIMARY KEY (`patientEmail`) USING BTREE,
  ADD KEY `assigned doctor` (`assignedDoctor`),
  ADD KEY `patientEmail` (`patientEmail`) USING BTREE;

ALTER TABLE `users`
  ADD PRIMARY KEY (`email`);

ALTER TABLE `visitDetails`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `timestamp` (`timestamp`,`patientEmail`) USING BTREE,
  ADD KEY `doctor` (`doctor`),
  ADD KEY `patientEmail` (`patientEmail`) USING BTREE;


ALTER TABLE `accessRecords`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;

ALTER TABLE `visitDetails`
  MODIFY `id` int NOT NULL AUTO_INCREMENT;


ALTER TABLE `accessRecords`
  ADD CONSTRAINT `docEmail` FOREIGN KEY (`email`) REFERENCES `users` (`email`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `bookings`
  ADD CONSTRAINT `doctor` FOREIGN KEY (`doctor`) REFERENCES `users` (`email`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `patient` FOREIGN KEY (`patient`) REFERENCES `patientDetails` (`patientEmail`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `messages`
  ADD CONSTRAINT `from` FOREIGN KEY (`from`) REFERENCES `patientDetails` (`patientEmail`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `to` FOREIGN KEY (`to`) REFERENCES `users` (`email`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `patientDetails`
  ADD CONSTRAINT `assigned doctor` FOREIGN KEY (`assignedDoctor`) REFERENCES `users` (`email`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `patientDetails_ibfk_1` FOREIGN KEY (`patientEmail`) REFERENCES `users` (`email`) ON DELETE RESTRICT ON UPDATE RESTRICT;

ALTER TABLE `visitDetails`
  ADD CONSTRAINT `visitDetails_ibfk_1` FOREIGN KEY (`patientEmail`) REFERENCES `patientDetails` (`patientEmail`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  ADD CONSTRAINT `visitDetails_ibfk_2` FOREIGN KEY (`doctor`) REFERENCES `users` (`email`);
COMMIT;
