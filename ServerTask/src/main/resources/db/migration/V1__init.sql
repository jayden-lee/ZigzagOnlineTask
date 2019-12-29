CREATE TABLE `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `name` varchar(255) NOT NULL,
    `department` varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `meetingroom` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `name` varchar(255) NOT NULL,
   `type` varchar(255) NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `reservation` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT,
   `user_id` bigint(20) NOT NULL,
   `meetingroom_id` bigint(20) NOT NULL,
   `start_dt` datetime NOT NULL,
   `end_dt` datetime NOT NULL,
   PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE INDEX idx_reservation_dt ON `reservation` (`start_dt`, `end_dt`);

INSERT INTO `meetingroom` (`name`, `type`) VALUES ('5-1', 'FOUR');
INSERT INTO `meetingroom` (`name`, `type`) VALUES ('5-2', 'SIX');
INSERT INTO `meetingroom` (`name`, `type`) VALUES ('5-3', 'EIGHT');
INSERT INTO `meetingroom` (`name`, `type`) VALUES ('6-1', 'EIGHT');
INSERT INTO `meetingroom` (`name`, `type`) VALUES ('6-2', 'EIGHT');

INSERT INTO `user` (`department`, `name`) VALUES ('dev', 'jayden');
INSERT INTO `user` (`department`, `name`) VALUES ('planning', 'ella');
INSERT INTO `user` (`department`, `name`) VALUES ('planning', 'robb');
INSERT INTO `user` (`department`, `name`) VALUES ('dev', 'kelly');
INSERT INTO `user` (`department`, `name`) VALUES ('design', 'cia');
INSERT INTO `user` (`department`, `name`) VALUES ('hr', 'bill');