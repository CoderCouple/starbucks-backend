CREATE DATABASE `starbucks` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `starbucks`;

CREATE TABLE `User`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT,
    `guid`        varchar(40)  DEFAULT NULL,
    `firstName`   varchar(200) DEFAULT NULL,
    `lastName`    varchar(200) DEFAULT NULL,
    `email`       varchar(200) DEFAULT NULL,
    `password`    varchar(200) DEFAULT NULL,
    `dateOfBirth` date         DEFAULT NULL,
    `isActive`    tinyint(1)   DEFAULT '1',
    `created`     datetime     DEFAULT CURRENT_TIMESTAMP,
    `updated`     datetime     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_Email` (`email`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 33
  DEFAULT CHARSET = latin1;

CREATE TABLE `Product`
(
    `id`            int(11)                                                       NOT NULL AUTO_INCREMENT,
    `type`          enum ('CAPPUCCINO','ESPRESSO','MOCHAS','MACCHIATOS','LATTES') NOT NULL DEFAULT 'LATTES',
    `name`          varchar(200)                                                           DEFAULT NULL,
    `cost`          float(10, 2)                                                           DEFAULT NULL,
    `totalQuantity` int(11)                                                                DEFAULT NULL,
    `isActive`      tinyint(1)                                                             DEFAULT '1',
    `created`       datetime                                                               DEFAULT CURRENT_TIMESTAMP,
    `updated`       datetime                                                               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 12
  DEFAULT CHARSET = latin1;

CREATE TABLE `Ping`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `data` varchar(200) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = latin1;

CREATE TABLE `Order`
(
    `id`            int(11)                                          NOT NULL AUTO_INCREMENT,
    `transactionId` varchar(40)                                               DEFAULT NULL,
    `userId`        int(11)                                                   DEFAULT NULL,
    `status`        enum ('PLACED','BLOCKED','FULFILLED','CANCELED') NOT NULL DEFAULT 'PLACED',
    `total`         float(10, 2)                                              DEFAULT NULL,
    `purchaseDate`  datetime                                                  DEFAULT NULL,
    `created`       datetime                                                  DEFAULT CURRENT_TIMESTAMP,
    `updated`       datetime                                                  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_TransactionId` (`transactionId`),
    KEY `userId` (`userId`),
    CONSTRAINT `order_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `User` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = latin1;

CREATE TABLE `LineItem`
(
    `id`        int(11) NOT NULL AUTO_INCREMENT,
    `orderId`   int(11)      DEFAULT NULL,
    `productId` int(11)      DEFAULT NULL,
    `quantity`  varchar(200) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `product_id` (`productId`),
    KEY `order_id` (`orderId`),
    CONSTRAINT `lineitem_ibfk_1` FOREIGN KEY (`productId`) REFERENCES `Product` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `lineitem_ibfk_2` FOREIGN KEY (`orderId`) REFERENCES `Order` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB
  AUTO_INCREMENT = 22
  DEFAULT CHARSET = latin1;

INSERT INTO Ping (data) VALUES ('Pong!');


