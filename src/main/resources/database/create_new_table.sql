USE milktea;
CREATE TABLE IF NOT EXISTS `customer` (
    `customerId` int NOT NULL AUTO_INCREMENT,
    `phoneNumber` varchar(20) NOT NULL,
    `name` varchar(50) NULL,
    `address` varchar(250) NULL,
    PRIMARY KEY (`customerId`)
);

CREATE TABLE IF NOT EXISTS `table` (
    `tableId` int NOT NULL AUTO_INCREMENT,
    `name` varchar(45) NOT NULL,
    `status` varchar(45) NOT NULL DEFAULT 'free' COMMENT 'free-Trống\nserving-Đang phục vụ\nreserving-Đặt trước',
    PRIMARY KEY (`tableId`)
);

CREATE TABLE IF NOT EXISTS `employee` (
    `employeeId` int NOT NULL AUTO_INCREMENT,
    `username` varchar(50) UNIQUE NOT NULL,
    `password` varchar(50) NOT NULL,
    `name` varchar(50) NOT NULL,
    `phoneNumber` varchar(20) NOT NULL DEFAULT '',
    `startDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `permission` varchar(50) NOT NULL COMMENT 'manager-quản lý\r\nstaff-nhân viên\r\ninactive-nghỉ việc',
    `salary` int NOT NULL,
    PRIMARY KEY (`employeeId`)
);

CREATE TABLE IF NOT EXISTS `food_category` (
    `foodCategoryId` int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) UNIQUE NOT NULL,
    `slug` varchar(50) NOT NULL,
    PRIMARY KEY (`foodCategoryId`)
);

CREATE TABLE IF NOT EXISTS `food_item` (
    `foodItemId` int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) UNIQUE NOT NULL,
    `description` varchar(500) NULL,
    `imagePath` varchar(50) NULL,
    `unitName` varchar(20) NOT NULL,
    `unitPrice` bigint NOT NULL,
    `foodCategoryId` int NOT NULL,
    PRIMARY KEY (`foodItemId`),
    FOREIGN KEY (`foodCategoryId`) REFERENCES `food_category` (`foodCategoryId`)
);

CREATE TABLE IF NOT EXISTS `order` (
    `orderId` int NOT NULL AUTO_INCREMENT,
    `employeeId` int NOT NULL,
    `tableId` int NULL,
    `customerId` int NULL,
    `type` varchar(45) NOT NULL DEFAULT 'local' COMMENT 'local - tại quán\nonline - đặt online',
    `status` varchar(45) NOT NULL DEFAULT 'unpaid' COMMENT 'unpaid - chưa thanh toán\npaid - đã thanh toán',
    `orderDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `payDate` TIMESTAMP NULL DEFAULT NULL,
    `paidAmount` bigint NULL DEFAULT 0,
    `totalAmount` bigint NOT NULL DEFAULT 0,
    `discount` int NOT NULL DEFAULT 0,
    CHECK(discount >= 0 AND discount <= 100),
    PRIMARY KEY (`orderId`),
    FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`),
    FOREIGN KEY (`tableId`) REFERENCES `table` (`tableId`),
    FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`)
);

CREATE TABLE IF NOT EXISTS `shipment` (
    `orderId` int NOT NULL,
    `customerId` int NOT NULL,
    `employeeId` int NOT NULL,
    `status` varchar(45) NOT NULL DEFAULT 'topay' COMMENT 'topay - chờ xác nhận\ntoship - chờ lấy hàng\ntoreceive - đang giao\ncompleted - hoàn thành\ncancelled - đã hủy',
    `shipCost` int NOT NULL DEFAULT 0,
    `startDate` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
    `endDate` TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (`orderId`),
    FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`),
    FOREIGN KEY (`orderId`) REFERENCES `order` (`orderId`),
    FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`)
);

CREATE TABLE IF NOT EXISTS `order_item` (
    `orderId` int NOT NULL,
    `foodItemId` int NOT NULL,
    `toppingId` int NOT NULL DEFAULT 1,
    `quantity` int NOT NULL DEFAULT 1,
    `foodPrice` bigint(20) NOT NULL DEFAULT 0,
    `toppingPrice` bigint(20) NOT NULL DEFAULT 0,
    `note` varchar(100) NULL,
    PRIMARY KEY (`orderId`, `foodItemId`, `toppingId`),
    FOREIGN KEY (`foodItemId`) REFERENCES `food_item` (`foodItemId`),
    FOREIGN KEY (`toppingId`) REFERENCES `food_item` (`foodItemId`),
    FOREIGN KEY (`orderId`) REFERENCES `order` (`orderId`)
);


CREATE TABLE IF NOT EXISTS `session` (
    `sessionId` int NOT NULL AUTO_INCREMENT,
    `employeeId` int NOT NULL,
    `startTime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `endTime` TIMESTAMP NULL DEFAULT NULL,
    `message` varchar(200) DEFAULT NULL,
    PRIMARY KEY (`sessionId`),
    FOREIGN KEY (`employeeId`) REFERENCES `employee` (`employeeId`)
);
