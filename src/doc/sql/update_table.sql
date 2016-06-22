 CREATE TABLE `pay_property` (
  `ID` bigint(20) NOT NULL,
  `config_name` varchar(255) NOT NULL,
  `config_value` varchar(4000) NOT NULL,
  `pay_channel` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `idx_pay_properychannle` (`pay_channel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8

CREATE TABLE `terminal_property` (
  `id` bigint(20) NOT NULL,
  `terminalid` bigint(20) NOT NULL,
  `terminale_key` varchar(255) NOT NULL,
  `terminal_value` varchar(4000) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_termina_properyid` (`terminalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8