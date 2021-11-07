CREATE DATABASE IF NOT EXISTS `support_portal` DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_general_ci;

CREATE TABLE IF NOT EXISTS `snowflake`(
    `id`          VARCHAR(32)  NOT NULL     COMMENT 'id',
    `center_id`   TINYINT(1)   NOT NULL     COMMENT 'center id',
    `worker_id`   TINYINT(1)   NOT NULL     COMMENT 'worker id',
    `ip`          VARCHAR(20)  NOT NULL     COMMENT 'ip',
    `port`        int(1)       NOT NULL     COMMENT 'port',
    `application` VARCHAR(50)  NOT NULL     COMMENT 'application name',
    `create_time` DATETIME     NOT NULL     COMMENT 'create time',
    `update_time` DATETIME     DEFAULT NULL COMMENT 'update time',
    PRIMARY KEY(`id`),
    INDEX(`application`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
