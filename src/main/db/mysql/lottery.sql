-- DROP TABLE IF EXISTS `auto_join`;
CREATE TABLE `auto_join` (
  `id` bigint(20) NOT NULL,
  `userno` varchar(255) DEFAULT NULL,
  `lottype` int(11) DEFAULT NULL,
  `starter` varchar(255) DEFAULT NULL,
  `times` int(3) DEFAULT NULL,
  `joinAmt` bigint(10) DEFAULT NULL,
  `joinTimes` int(3) DEFAULT NULL,
  `failNum` int(3) DEFAULT NULL,
  `createTime` timestamp(6) NULL DEFAULT NULL,
  `updatetime` timestamp(6) NULL DEFAULT NULL,
  `autoJoinState` int(1) DEFAULT NULL,
  `forceJoin` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `auto_join_detail`;

CREATE TABLE `auto_join_detail` (
  `id` bigint(20) NOT NULL,
  `autoJoinId` bigint(20) DEFAULT NULL,
  `caseLotBuyId` varchar(255) DEFAULT NULL,
  `userno` varchar(255) DEFAULT NULL,
  `caselotId` varchar(255) DEFAULT NULL,
  `joinamt` bigint(10) DEFAULT NULL,
  `state` int(1) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `createTime` timestamp(6) NULL DEFAULT NULL,
  `LOTTYPE` bigint(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `award_level`;
CREATE TABLE `award_level` (
  `lotterytype` int(11) NOT NULL,
  `prizelevel` varchar(255) NOT NULL,
  `extraprize` bigint(20) DEFAULT NULL,
  `levelname` varchar(255) NOT NULL,
  `prize` bigint(20) NOT NULL,
  PRIMARY KEY (`lotterytype`,`prizelevel`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `dc_race`;

CREATE TABLE `dc_race` (
  `id` bigint(20) NOT NULL,
  `away_team` varchar(255) DEFAULT NULL,
  `bcsfp_result` varchar(255) DEFAULT NULL,
  `bf_result` varchar(255) DEFAULT NULL,
  `catch_id` int(11) DEFAULT NULL,
  `create_time` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `dc_type` int(11) DEFAULT NULL,
  `end_sale_time` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `ext` varchar(255) DEFAULT NULL,
  `fx_id` int(11) DEFAULT NULL,
  `half_score` varchar(255) DEFAULT NULL,
  `handicap` varchar(255) DEFAULT NULL,
  `home_team` varchar(255) DEFAULT NULL,
  `jqs_result` varchar(255) DEFAULT NULL,
  `match_date` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `match_name` varchar(255) DEFAULT NULL,
  `match_num` int(11) DEFAULT NULL,
  `phase` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `prize_status` int(11) DEFAULT NULL,
  `prize_time` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `sf_result` varchar(255) DEFAULT NULL,
  `sfp_result` varchar(255) DEFAULT NULL,
  `sp_bcsfp` varchar(255) DEFAULT NULL,
  `sp_bf` varchar(255) DEFAULT NULL,
  `sp_jqs` varchar(255) DEFAULT NULL,
  `sp_sf` varchar(255) DEFAULT NULL,
  `sp_sfp` varchar(255) DEFAULT NULL,
  `sp_sxds` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `sxds_result` varchar(255) DEFAULT NULL,
  `update_time` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `whole_socre` varchar(255) DEFAULT NULL,
  `result_from` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_un_dcrace_p_m` (`phase`,`match_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `give_activity`;

CREATE TABLE `give_activity` (
  `id` bigint(20) NOT NULL,
  `create_time` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `description` varchar(255) DEFAULT NULL,
  `end_time` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `isfirst_send` int(11) DEFAULT NULL,
  `is_percentage` int(11) DEFAULT NULL,
  `istop_limit` int(11) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `send_money` decimal(19,2) DEFAULT NULL,
  `send_type` int(11) DEFAULT NULL,
  `start_time` timestamp(6) NULL DEFAULT '0000-00-00 00:00:00.000000',
  `status` int(11) DEFAULT NULL,
  `top_limit` decimal(19,2) DEFAULT NULL,
  `TYPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `id_generator`;

CREATE TABLE `id_generator` (
  `id` bigint(20) NOT NULL,
  `seqid` bigint(20) DEFAULT NULL,
  `seqname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `jclq_race`;

CREATE TABLE `jclq_race` (
  `match_num` varchar(255) NOT NULL,
  `away_team` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `dynamic_draw_status` int(11) DEFAULT NULL,
  `dynamic_handicap` varchar(255) DEFAULT NULL,
  `dynamic_preset_score` varchar(255) DEFAULT NULL,
  `dynamic_sale_dxf_status` int(11) DEFAULT NULL,
  `dynamic_sale_rfsf_status` int(11) DEFAULT NULL,
  `dynamic_sale_sf_status` int(11) DEFAULT NULL,
  `dynamic_sale_sfc_status` int(11) DEFAULT NULL,
  `end_sale_time` timestamp(6) NULL DEFAULT NULL,
  `ext` varchar(255) DEFAULT NULL,
  `final_score` varchar(255) DEFAULT NULL,
  `first_quarter` varchar(255) DEFAULT NULL,
  `fourth_quarter` varchar(255) DEFAULT NULL,
  `fx_id` int(11) DEFAULT NULL,
  `home_team` varchar(255) DEFAULT NULL,
  `match_date` timestamp(6) NULL DEFAULT NULL,
  `match_name` varchar(255) DEFAULT NULL,
  `official_date` timestamp(6) NULL DEFAULT NULL,
  `official_num` varchar(255) DEFAULT NULL,
  `official_weekday` varchar(255) DEFAULT NULL,
  `phase` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `prize_dxf` varchar(255) DEFAULT NULL,
  `prize_rfsf` varchar(255) DEFAULT NULL,
  `prize_sf` varchar(255) DEFAULT NULL,
  `prize_sfc` varchar(255) DEFAULT NULL,
  `prize_status` int(11) DEFAULT NULL,
  `second_quarter` varchar(255) DEFAULT NULL,
  `static_draw_status` int(11) DEFAULT NULL,
  `static_handicap` varchar(255) DEFAULT NULL,
  `static_preset_score` varchar(255) DEFAULT NULL,
  `static_sale_dxf_status` int(11) DEFAULT NULL,
  `static_sale_rfsf_status` int(11) DEFAULT NULL,
  `static_sale_sf_status` int(11) DEFAULT NULL,
  `static_sale_sfc_status` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `third_quarter` varchar(255) DEFAULT NULL,
  `update_time` timestamp(6) NULL DEFAULT NULL,
  `dg_static_sale_sf_status` int(10) DEFAULT '0',
  `dg_static_sale_rfsf_status` int(10) DEFAULT '0',
  `dg_static_sale_dxf_status` int(10) DEFAULT '0',
  `dg_static_sale_sfc_status` int(10) DEFAULT '0',
  `result_from` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`match_num`),
  KEY `jclq_race_phase` (`phase`),
  KEY `idx_jclq_status_time` (`status`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `jczq_race`;

CREATE TABLE `jczq_race` (
  `match_num` varchar(255) NOT NULL,
  `away_team` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `dynamic_draw_status` int(11) DEFAULT NULL,
  `dynamic_sale_bf_status` int(11) DEFAULT NULL,
  `dynamic_sale_bqc_status` int(11) DEFAULT NULL,
  `dynamic_sale_jqs_status` int(11) DEFAULT NULL,
  `dynamic_sale_spf_status` int(11) DEFAULT NULL,
  `dynamic_sale_spf_wrq_status` int(11) DEFAULT NULL,
  `end_sale_time` timestamp(6) NULL DEFAULT NULL,
  `ext` varchar(255) DEFAULT NULL,
  `final_score` varchar(255) DEFAULT NULL,
  `first_half` varchar(255) DEFAULT NULL,
  `handicap` varchar(255) DEFAULT NULL,
  `home_team` varchar(255) DEFAULT NULL,
  `match_date` timestamp(6) NULL DEFAULT NULL,
  `match_name` varchar(255) DEFAULT NULL,
  `official_date` timestamp(6) NULL DEFAULT NULL,
  `official_num` varchar(255) DEFAULT NULL,
  `official_weekday` varchar(255) DEFAULT NULL,
  `phase` varchar(255) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `prize_bf` varchar(255) DEFAULT NULL,
  `prize_bqc` varchar(255) DEFAULT NULL,
  `prize_jqs` varchar(255) DEFAULT NULL,
  `prize_spf` varchar(255) DEFAULT NULL,
  `prize_spf_wrq` varchar(255) DEFAULT NULL,
  `prize_status` int(11) DEFAULT NULL,
  `second_half` varchar(255) DEFAULT NULL,
  `static_draw_status` int(11) DEFAULT NULL,
  `static_sale_bf_status` int(11) DEFAULT NULL,
  `static_sale_bqc_status` int(11) DEFAULT NULL,
  `static_sale_jqs_status` int(11) DEFAULT NULL,
  `static_sale_spf_status` int(11) DEFAULT NULL,
  `static_sale_spf_wrq_status` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `update_time` timestamp(6) NULL DEFAULT NULL,
  `dg_static_sale_spf_status` int(10) DEFAULT '0',
  `dg_static_sale_jqs_status` int(10) DEFAULT '0',
  `dg_static_sale_bqc_status` int(10) DEFAULT '0',
  `dg_static_sale_spf_wrq_status` int(10) DEFAULT '0',
  `dg_static_sale_bf_status` int(10) DEFAULT '0',
  `result_from` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`match_num`),
  KEY `jczq_race_phase` (`phase`),
  KEY `idx_jczq_status_time` (`status`,`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lottery_addprize_strategy`;

CREATE TABLE `lottery_addprize_strategy` (
  `lottery_type` int(10) NOT NULL,
  `prize_level` varchar(200) NOT NULL,
  `start_phase` varchar(200) NOT NULL,
  `end_phase` varchar(200) NOT NULL,
  `status` int(10) NOT NULL DEFAULT '0',
  `add_amt` int(10) NOT NULL DEFAULT '0',
  PRIMARY KEY (`lottery_type`,`prize_level`,`start_phase`,`end_phase`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lottery_caselot`;

CREATE TABLE `lottery_caselot` (
  `id` varchar(255) NOT NULL,
  `buyAmtByFollower` bigint(20) NOT NULL,
  `buyAmtByStarter` bigint(20) DEFAULT NULL,
  `caselotinfo` varchar(255) DEFAULT NULL,
  `commisionRatio` int(11) DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `displayState` int(11) DEFAULT NULL,
  `displayStateMemo` varchar(255) DEFAULT NULL,
  `endTime` timestamp(6) NULL DEFAULT NULL,
  `isExchanged` int(11) DEFAULT NULL,
  `lotsType` int(11) DEFAULT NULL,
  `lottype` int(11) DEFAULT NULL,
  `minAmt` bigint(20) DEFAULT NULL,
  `orderid` varchar(255) DEFAULT NULL,
  `participantCount` bigint(20) DEFAULT NULL,
  `phaseNo` varchar(255) DEFAULT NULL,
  `safeAmt` bigint(20) DEFAULT NULL,
  `sortState` int(11) DEFAULT NULL,
  `startTime` timestamp(6) NULL DEFAULT NULL,
  `starter` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `totalAmt` bigint(20) DEFAULT NULL,
  `visibility` int(11) DEFAULT NULL,
  `winBigAmt` bigint(20) DEFAULT NULL,
  `winDetail` varchar(255) DEFAULT NULL,
  `winPreAmt` bigint(20) DEFAULT NULL,
  `winStartTime` timestamp(6) NULL DEFAULT NULL,
  `finish_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_case_lot_time` (`startTime`),
  KEY `idx_lottery_caselot_l_s_t` (`lottype`,`state`,`startTime`),
  KEY `idx_lottery_caselot_l_ss_t` (`sortState`,`startTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lottery_caselot_buy`;

CREATE TABLE `lottery_caselot_buy` (
  `id` varchar(255) NOT NULL,
  `buyDrawAmt` decimal(19,2) DEFAULT NULL,
  `buyTime` timestamp(6) NULL DEFAULT NULL,
  `buyType` int(11) DEFAULT NULL,
  `caselotId` varchar(255) DEFAULT NULL,
  `commisionPrizeAmt` decimal(19,2) DEFAULT NULL,
  `flag` int(11) DEFAULT NULL,
  `freezDrawAmt` decimal(19,2) DEFAULT NULL,
  `freezeSafeAmt` decimal(19,2) DEFAULT NULL,
  `isExchanged` int(11) DEFAULT NULL,
  `lottype` int(11) DEFAULT NULL,
  `num` decimal(19,2) DEFAULT NULL,
  `phaseNo` varchar(255) DEFAULT NULL,
  `prizeAmt` decimal(19,2) DEFAULT NULL,
  `safeAmt` decimal(19,2) DEFAULT NULL,
  `userno` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_caselot_case_buy_id` (`caselotId`),
  KEY `idx_caselot_buy_userno` (`userno`),
  KEY `idx_caselot_buy_time` (`buyTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lottery_chase`;

CREATE TABLE `lottery_chase` (
  `id` varchar(255) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `batch_num` int(11) DEFAULT NULL,
  `begin_phase` varchar(60) DEFAULT NULL,
  `bet_code` longtext NOT NULL,
  `bet_num` int(11) DEFAULT NULL,
  `cancel_by` int(11) DEFAULT NULL,
  `change_time` timestamp(6) NULL DEFAULT NULL,
  `chase_detail` varchar(255) DEFAULT NULL,
  `chase_type` int(11) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `end_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `last_phase` varchar(60) DEFAULT NULL,
  `lottery_type` int(11) NOT NULL,
  `prize_total` decimal(19,2) DEFAULT NULL,
  `remain_amount` decimal(19,2) DEFAULT NULL,
  `remain_num` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `total_amount` decimal(19,2) DEFAULT NULL,
  `userno` varchar(255) NOT NULL,
  `already_prize` decimal(19,2) DEFAULT '0.00',
  `multiple` int(10) DEFAULT '1',
  `addition` int(2) DEFAULT NULL,
  `end_phase` varchar(200) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `memo` varchar(4000) DEFAULT NULL,
  `buy_agencyno` varchar(200) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lottery_chase_buy`;

CREATE TABLE `lottery_chase_buy` (
  `id` varchar(255) NOT NULL,
  `chaseid` varchar(255) NOT NULL,
  `orderid` varchar(255) DEFAULT NULL,
  `lottery_type` int(10) NOT NULL,
  `phase` varchar(200) NOT NULL,
  `status` int(10) DEFAULT NULL,
  `chase_index` int(10) DEFAULT '0',
  `phase_start_time` datetime(6) DEFAULT NULL,
  `phase_end_time` datetime(6) DEFAULT NULL,
  `prize` decimal(19,2) DEFAULT '0.00',
  `multiple` int(10) DEFAULT '1',
  `remain_num` int(10) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT '0.00',
  `remain_amount` decimal(19,2) DEFAULT '0.00',
  `memo` varchar(4000) DEFAULT NULL,
  `addition` int(2) DEFAULT NULL,
  `finish_time` datetime(6) DEFAULT NULL,
  `userno` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `chase_type` int(10) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `bet_code` longtext NOT NULL,
  `order_result_status` int(2) DEFAULT NULL,
  `order_status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `chase_buy_orderid` (`orderid`),
  KEY `chase_buy_lottery_phase` (`lottery_type`,`phase`),
  KEY `chase_buy_chaseid` (`chaseid`),
  KEY `lottery_chase_buy_createtime` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `lottery_draw_amount`;

CREATE TABLE `lottery_draw_amount` (
  `id` varchar(255) NOT NULL,
  `bank_address` varchar(255) DEFAULT NULL,
  `bank_id` varchar(255) DEFAULT NULL,
  `bank_type` varchar(255) DEFAULT NULL,
  `batch_id` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `draw_amount` decimal(19,2) NOT NULL,
  `draw_type` int(11) DEFAULT NULL,
  `fee` decimal(19,2) DEFAULT '0.00',
  `finish_time` timestamp(6) NULL DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `real_amount` decimal(19,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `userno` varchar(255) NOT NULL,
  `operate_type` int(11) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idx_draw_mount_userno` (`userno`),
  KEY `idx_draw_mount_ctime` (`create_time`),
  KEY `idx_draw_mount_bid` (`batch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lottery_logic_machine`;

CREATE TABLE `lottery_logic_machine` (
  `id` bigint(20) NOT NULL,
  `terminal_type` int(10) NOT NULL,
  `lottery_type` int(10) NOT NULL,
  `terminal_id` int(10) NOT NULL,
  `start_id` bigint(20) NOT NULL,
  `end_id` bigint(20) NOT NULL,
  `current_id` bigint(20) DEFAULT '0',
  `status` int(3) DEFAULT '0',
  `switch_time` datetime(6) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `currentdate` varchar(200) DEFAULT NULL,
  `phase` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`,`terminal_type`,`lottery_type`),
  KEY `logic_machine_tid` (`terminal_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lottery_order`;

CREATE TABLE `lottery_order` (
  `id` varchar(255) NOT NULL,
  `account_type` int(11) NOT NULL,
  `adddition` int(11) DEFAULT NULL,
  `agencyno` varchar(255) DEFAULT NULL,
  `amount` decimal(19,2) NOT NULL,
  `amt` decimal(19,2) NOT NULL,
  `bet_type` int(11) NOT NULL,
  `big_posttaxprize` decimal(19,2) DEFAULT '0.00',
  `byuserno` varchar(255) NOT NULL,
  `chase_id` varchar(255) DEFAULT NULL,
  `content` longtext NOT NULL,
  `dead_line` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `draw_time` timestamp(6) NULL DEFAULT NULL,
  `first_matchnum` bigint(20) DEFAULT '0',
  `hemaiid` varchar(255) DEFAULT NULL,
  `is_exchanged` int(3) DEFAULT NULL,
  `last_matchnum` bigint(20) DEFAULT '0',
  `lottery_type` int(11) NOT NULL,
  `match_nums` text,
  `multiple` int(11) NOT NULL,
  `order_result_status` int(3) DEFAULT NULL,
  `order_status` int(11) NOT NULL,
  `pay_status` int(11) NOT NULL,
  `phase` varchar(255) NOT NULL,
  `pretax_prize` decimal(19,2) DEFAULT '0.00',
  `print_time` timestamp(6) NULL DEFAULT NULL,
  `prize_detail` varchar(255) DEFAULT NULL,
  `process_time` timestamp(6) NULL DEFAULT NULL,
  `receive_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `reward_time` timestamp(6) NULL DEFAULT NULL,
  `small_posttaxprize` decimal(19,2) DEFAULT '0.00',
  `terminalid` varchar(255) DEFAULT NULL,
  `terminal_type_id` int(11) DEFAULT NULL,
  `userno` varchar(255) NOT NULL,
  `wincode` varchar(255) DEFAULT NULL,
  `play_type_str` varchar(4000) DEFAULT NULL,
  `total_prize` decimal(19,2) DEFAULT '0.00',
  `add_prize` decimal(19,2) DEFAULT '0.00',
  `user_name` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `buy_agencyno` varchar(200) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `IDX_LOTTERY_ORDER_DEAD_LINE` (`dead_line`),
  KEY `IDX_LOTTERY_ORDER_RECIVETIME` (`receive_time`),
  KEY `IDX_LOTTERY_ORDER_PHASE` (`lottery_type`,`phase`),
  KEY `IDX_LOTTERY_ORDER_userno` (`userno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `lottery_phase`;


CREATE TABLE `lottery_phase` (
  `id` bigint(20) NOT NULL,
  `add_pool_amount` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `draw_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end_sale_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `end_ticket_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `for_current` int(11) NOT NULL,
  `for_sale` int(11) NOT NULL,
  `hemai_end_time` timestamp(6) NULL DEFAULT NULL,
  `lottery_type` int(11) NOT NULL,
  `phase` varchar(255) NOT NULL,
  `phase_encash_status` int(11) DEFAULT NULL,
  `phase_status` int(11) NOT NULL,
  `phase_time_status` int(11) NOT NULL,
  `pool_amount` varchar(255) DEFAULT NULL,
  `prize_detail` varchar(255) DEFAULT NULL,
  `sale_amount` varchar(255) DEFAULT NULL,
  `start_sale_time` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `switch_time` timestamp(6) NULL DEFAULT NULL,
  `terminal_status` int(11) NOT NULL,
  `wincode` varchar(255) DEFAULT NULL,
  `draw_data_from` varchar(200) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `IDX_UN_LOTTERY_TYPE_PHASE` (`lottery_type`,`phase`),
  KEY `idx_lottery_phase_ctime` (`for_current`,`end_sale_time`),
  KEY `idx_lottery_phase_ttime` (`terminal_status`,`start_sale_time`),
  KEY `idx_lottery_phase_ptime` (`phase_status`,`end_ticket_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lottery_terminal_config`;

CREATE TABLE `lottery_terminal_config` (
  `id` bigint(20) NOT NULL,
  `lottery_type` int(10) DEFAULT NULL,
  `is_enabled` int(5) NOT NULL,
  `is_check_enabled` int(5) NOT NULL,
  `is_paused` int(5) NOT NULL,
  `allot_forbid_period` varchar(4000) DEFAULT NULL,
  `send_forbid_period` varchar(4000) DEFAULT NULL,
  `check_forbid_period` varchar(4000) DEFAULT NULL,
  `comment` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `lottery_terminal_config_lotteru` (`lottery_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `lottery_ticket_config`;

CREATE TABLE `lottery_ticket_config` (
  `id` bigint(20) NOT NULL,
  `batch_count` int(11) DEFAULT NULL,
  `batch_time` bigint(20) DEFAULT NULL,
  `beginsaleallot_backward` bigint(20) DEFAULT NULL,
  `beginsale_forward` bigint(20) DEFAULT NULL,
  `drawbackward` bigint(20) DEFAULT NULL,
  `endsaleallot_forward` bigint(20) DEFAULT NULL,
  `endsale_forward` bigint(20) DEFAULT NULL,
  `endticket_timeout` bigint(20) DEFAULT NULL,
  `lottery_type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `lottery_type` (`lottery_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `lottype_config`;

CREATE TABLE `lottype_config` (
  `LOTTERY_TYPE` int(11) NOT NULL,
  `AUTOENCASH` int(11) DEFAULT NULL,
  `DRAW_TIME` varchar(255) DEFAULT NULL,
  `END_SALE_TIME` varchar(255) DEFAULT NULL,
  `HEMAI_ENDT_IME` varchar(255) DEFAULT NULL,
  `LOTCENTERISVALID` int(11) DEFAULT NULL,
  `ONPRIZE` int(11) DEFAULT NULL,
  `PRE_PHASE_AMT` int(11) DEFAULT NULL,
  `PRE_PHASE_NUM` int(11) DEFAULT NULL,
  `START_SALE_TIME` varchar(255) DEFAULT NULL,
  `STATE` int(11) DEFAULT NULL,
  PRIMARY KEY (`LOTTERY_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `merchant`;

CREATE TABLE `merchant` (
  `merchant_code` varchar(255) NOT NULL,
  `secret_key` varchar(4000) DEFAULT NULL,
  `ip` varchar(4000) DEFAULT NULL,
  `credit_balance` decimal(19,2) DEFAULT '0.00',
  `merchant_name` varchar(255) DEFAULT NULL,
  `status` int(3) DEFAULT NULL,
  `notice_url` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`merchant_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `merchant_order`;

CREATE TABLE `merchant_order` (
  `orderid` varchar(255) NOT NULL,
  `merchant_code` varchar(255) DEFAULT NULL,
  `merchant_no` varchar(255) DEFAULT NULL,
  `lottery_type` int(10) NOT NULL,
  `phase` varchar(25) NOT NULL,
  `multiple` int(8) DEFAULT NULL,
  `play_type` int(10) DEFAULT NULL,
  `bet_code` longtext,
  `order_status` int(5) DEFAULT NULL,
  `order_result_status` int(5) DEFAULT NULL,
  `is_exchanged` int(3) DEFAULT NULL,
  `wincode` varchar(255) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT '0000-00-00 00:00:00.000000',
  `print_time` datetime(6) DEFAULT '0000-00-00 00:00:00.000000',
  `addition` int(4) DEFAULT NULL,
  `prize_detail` varchar(255) DEFAULT NULL,
  `total_prize` decimal(19,2) DEFAULT '0.00',
  `amount` decimal(19,2) DEFAULT '0.00',
  PRIMARY KEY (`orderid`),
  UNIQUE KEY `merchant_order_unique` (`merchant_code`,`merchant_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 

DROP TABLE IF EXISTS `pay_property`;
 
CREATE TABLE `pay_property` (
  `ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `config_name` varchar(255) NOT NULL,
  `config_value` varchar(4000) DEFAULT NULL,
  `pay_channel` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `PAY_PROPERTY_channel` (`pay_channel`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;
 

DROP TABLE IF EXISTS `prize_error_log`;
 
CREATE TABLE `prize_error_log` (
  `tranaction_id` varchar(255) NOT NULL,
  `after_balance` decimal(19,2) DEFAULT NULL,
  `after_draw_balance` decimal(19,2) DEFAULT NULL,
  `amt` decimal(19,2) DEFAULT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `draw_balance` decimal(19,2) DEFAULT NULL,
  `lottery_type` int(11) DEFAULT NULL,
  `order_id` varchar(255) DEFAULT NULL,
  `phase` varchar(255) DEFAULT NULL,
  `userno` varchar(255) DEFAULT NULL,
  `bet_type` int(3) DEFAULT NULL,
  PRIMARY KEY (`tranaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_member_account`;
 
CREATE TABLE `t_member_account` (
  `terminal_type` int(10) NOT NULL,
  `agent_code` varchar(200) NOT NULL,
  `terminal_name` varchar(200) DEFAULT NULL,
  `balance` decimal(19,2) DEFAULT '0.00',
  `credit_balance` decimal(19,2) DEFAULT '0.00',
  `toatal_prize` decimal(19,2) DEFAULT '0.00',
  `samll_prize` decimal(19,2) DEFAULT '0.00',
  `big_prize` decimal(19,2) DEFAULT '0.00',
  `update_time` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`terminal_type`,`agent_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `terminal`;

CREATE TABLE `terminal` (
  `id` bigint(20) NOT NULL,
  `allotforbidperiod` varchar(255) DEFAULT NULL,
  `checkForbidPeriod` varchar(255) DEFAULT NULL,
  `is_enabled` int(11) NOT NULL,
  `is_paused` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `sendforbidperiod` varchar(255) DEFAULT NULL,
  `terminal_type` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_TERMINAL_TERMINAL_TYPE` (`terminal_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `terminal_config`;

CREATE TABLE `terminal_config` (
  `id` bigint(20) NOT NULL,
  `allotforbidperiod` varchar(255) DEFAULT NULL,
  `checkForbidPeriod` varchar(255) DEFAULT NULL,
  `is_enabled` int(11) NOT NULL,
  `is_paused` int(11) NOT NULL,
  `lottery_type` int(11) NOT NULL,
  `play_type` int(11) DEFAULT NULL,
  `sendforbidperiod` varchar(255) DEFAULT NULL,
  `terminal_id` bigint(20) NOT NULL,
  `terminal_type` int(11) DEFAULT NULL,
  `terminateForward` bigint(20) DEFAULT NULL,
  `weight` int(11) NOT NULL,
  `terminal_name` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_un_terminal_config_unique` (`lottery_type`,`terminal_id`,`play_type`),
  KEY `idex_tremina_confipl` (`play_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `terminal_property`;

CREATE TABLE `terminal_property` (
  `id` bigint(20) NOT NULL,
  `terminalid` bigint(20) NOT NULL,
  `terminale_key` varchar(255) NOT NULL,
  `terminal_value` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `TERMINAL_PROPERTY_tid` (`terminalid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `ticket`;


CREATE TABLE `ticket` (
  `id` varchar(255) NOT NULL,
  `addition` int(11) DEFAULT NULL,
  `amount` decimal(19,2) NOT NULL,
  `batch_id` varchar(255) DEFAULT NULL,
  `batch_index` bigint(20) DEFAULT NULL,
  `content` varchar(255) NOT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `dead_line` timestamp(6) NOT NULL DEFAULT '0000-00-00 00:00:00.000000',
  `draw_time` timestamp(6) NULL DEFAULT NULL,
  `exchange_time` timestamp(6) NULL DEFAULT NULL,
  `external_id` varchar(255) DEFAULT NULL,
  `failure_message` varchar(255) DEFAULT NULL,
  `failure_type` int(11) DEFAULT NULL,
  `is_exchanged` int(11) DEFAULT NULL,
  `lottery_type` int(11) NOT NULL,
  `multiple` int(11) NOT NULL,
  `orderid` varchar(255) NOT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  `peilv` text,
  `phase` varchar(255) NOT NULL,
  `play_type` int(11) NOT NULL,
  `posttax_prize` decimal(19,2) DEFAULT '0.00',
  `pretax_prize` decimal(19,2) DEFAULT '0.00',
  `print_time` timestamp(6) NULL DEFAULT NULL,
  `prize_detail` varchar(255) DEFAULT NULL,
  `send_time` timestamp(6) NULL DEFAULT NULL,
  `status` int(11) NOT NULL,
  `terminal_id` bigint(20) DEFAULT NULL,
  `terminal_type` int(10) DEFAULT '0',
  `terminate_time` timestamp(6) NULL DEFAULT NULL,
  `ticket_result_status` int(11) DEFAULT NULL,
  `userno` varchar(255) NOT NULL,
  `total_prize` decimal(19,2) DEFAULT '0.00',
  `add_prize` decimal(19,2) DEFAULT '0.00',
  `user_name` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `send_time_long` bigint(32) DEFAULT '0',
  `dead_time_long` bigint(32) DEFAULT '0',
  `match_nums` text,
  `first_matchnum` bigint(20) DEFAULT '0',
  `last_matchnum` bigint(20) DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `IDX_TICKET_BATCHID` (`batch_id`),
  KEY `IDX_TICKET_CREATE_TIME` (`create_time`),
  KEY `IDX_TICKET_DEAD_LINE` (`dead_line`),
  KEY `IDX_TICKET_ORDERID` (`orderid`),
  KEY `IDX_TICKET_SEND_TIME` (`send_time`),
  KEY `IDX_TICKET_TERMINATE_TIME` (`terminate_time`),
  KEY `IDX_TICKET_TYPE_PHASE` (`lottery_type`,`phase`),
  KEY `IND_TICKET_TERMINAL_TYPE_ID` (`terminal_type`),
  KEY `ticke_send_long` (`send_time_long`),
  KEY `ticke_dead_long` (`dead_time_long`),
  KEY `idx_ticket_t_s_send` (`terminal_id`,`status`,`send_time`),
  KEY `idx_ticket_l_match` (`last_matchnum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `ticket_allot_log`;

CREATE TABLE `ticket_allot_log` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `ticket_id` varchar(255) DEFAULT NULL,
  `batch_id` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `terminal_id` int(10) DEFAULT NULL,
  `terminal_type_id` int(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_ticket_allot_log_time` (`create_time`),
  KEY `idx_ticket_allot_log_tikcet` (`ticket_id`),
  KEY `idx_ticket_allot_log_batch` (`batch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
DROP TABLE IF EXISTS `ticket_batch`;

CREATE TABLE `ticket_batch` (
  `id` varchar(255) NOT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `lottery_type` int(11) NOT NULL,
  `phase` varchar(255) NOT NULL,
  `play_type` int(11) NOT NULL,
  `send_time` timestamp(6) NULL DEFAULT NULL,
  `ticket_batch_status` int(11) NOT NULL,
  `terminal_id` bigint(20) NOT NULL,
  `terminal_type_id` bigint(20) DEFAULT NULL,
  `terminal_time` timestamp(6) NULL NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_TICKET_BATCH_TIME` (`create_time`),
  KEY `IDX_TYPE_PHASE_BATCH` (`lottery_type`,`phase`),
  KEY `idx_ticket_batch_type_statsu_time` (`lottery_type`,`ticket_batch_status`,`terminal_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `ticket_batch_send_log`
--

DROP TABLE IF EXISTS `ticket_batch_send_log`;


CREATE TABLE `ticket_batch_send_log` (
  `id` varchar(255) NOT NULL DEFAULT '',
  `batch_id` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `terminal_id` int(10) DEFAULT NULL,
  `terminal_type_id` int(10) DEFAULT NULL,
  `error_message` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_batch_send_log_time` (`create_time`),
  KEY `idx_batch_send_log_batchid` (`batch_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `user_account`
--

DROP TABLE IF EXISTS `user_account`;


CREATE TABLE `user_account` (
  `userno` varchar(255) NOT NULL,
  `balance` decimal(19,2) DEFAULT '0.00',
  `draw_balance` decimal(19,2) DEFAULT '0.00',
  `freeze` decimal(19,2) DEFAULT '0.00',
  `lasttrade_time` timestamp(6) NULL DEFAULT NULL,
  `last_trade_amt` decimal(19,2) DEFAULT '0.00',
  `last_freeze` decimal(19,2) DEFAULT '0.00',
  `mac` varchar(255) DEFAULT NULL,
  `total_balance` decimal(19,2) DEFAULT '0.00',
  `total_bet_amt` decimal(19,2) DEFAULT '0.00',
  `totalgiveamt` decimal(19,2) DEFAULT '0.00',
  `total_prize_amt` decimal(19,2) DEFAULT '0.00',
  `username` varchar(255) DEFAULT NULL,
  `total_recharge` decimal(19,2) DEFAULT '0.00',
  `give_balance` decimal(19,2) DEFAULT '0.00',
  PRIMARY KEY (`userno`),
  UNIQUE KEY `user_account_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `user_account_add`
--

DROP TABLE IF EXISTS `user_account_add`;


CREATE TABLE `user_account_add` (
  `id` varchar(255) NOT NULL,
  `userno` varchar(255) DEFAULT NULL,
  `amount` decimal(19,2) DEFAULT '0.00',
  `for_draw` int(3) DEFAULT NULL,
  `autdit_status` int(3) DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `audit_time` datetime(6) DEFAULT NULL,
  `memo` varchar(4000) DEFAULT NULL,
  `creator` varchar(255) DEFAULT NULL,
  `aduiter` varchar(255) DEFAULT NULL,
  `error_message` varchar(4000) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `is_add` int(3) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `user_account_add_username` (`username`),
  KEY `user_account_add_userno` (`userno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `user_account_detail`
--

DROP TABLE IF EXISTS `user_account_detail`;


CREATE TABLE `user_account_detail` (
  `id` varchar(255) NOT NULL,
  `amt` decimal(19,2) NOT NULL,
  `balance` decimal(19,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `account_detail_type` int(11) NOT NULL,
  `draw_balance` decimal(19,2) DEFAULT NULL,
  `freeze` decimal(19,2) DEFAULT NULL,
  `memo` varchar(255) DEFAULT NULL,
  `otherid` varchar(255) DEFAULT NULL,
  `payid` varchar(255) NOT NULL,
  `pay_type` int(11) NOT NULL,
  `userno` varchar(255) NOT NULL,
  `sign_status` int(4) DEFAULT '0',
  `finish_time` datetime(6) DEFAULT NULL,
  `lottery_type` int(10) DEFAULT '0',
  `phase` varchar(200) DEFAULT '0',
  `draw_amount` decimal(19,2) DEFAULT '0.00',
  `not_draw_amount` decimal(19,2) DEFAULT '0.00',
  `give_amount` decimal(19,2) DEFAULT '0.00',
  `agency_no` varchar(200) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UN_USER_ACCOUNT_DETAIL_TYPE` (`payid`,`pay_type`,`account_detail_type`),
  KEY `IDX_USER_ACCOUNT_DETAIL_TIME` (`create_time`),
  KEY `IDX_USER_ACCOUNT_DETAIL_USERNO` (`userno`),
  KEY `idx_account_detail_l` (`lottery_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `user_account_handsel`
--

DROP TABLE IF EXISTS `user_account_handsel`;


CREATE TABLE `user_account_handsel` (
  `userno` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `balance` decimal(19,2) NOT NULL DEFAULT '0.00',
  `total_give` decimal(19,2) NOT NULL DEFAULT '0.00',
  `last_transaction` decimal(19,2) DEFAULT '0.00',
  `payid` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`userno`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `user_achievement`
--

DROP TABLE IF EXISTS `user_achievement`;


CREATE TABLE `user_achievement` (
  `id` bigint(20) NOT NULL,
  `userno` varchar(255) DEFAULT NULL,
  `lottype` int(11) DEFAULT NULL,
  `effectiveAchievement` bigint(20) DEFAULT NULL,
  `ineffectiveAchievement` bigint(20) DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `user_achievement_detail`
--

DROP TABLE IF EXISTS `user_achievement_detail`;


CREATE TABLE `user_achievement_detail` (
  `id` bigint(20) NOT NULL,
  `userno` varchar(255) DEFAULT NULL,
  `lottype` int(11) DEFAULT NULL,
  `achievement` int(10) DEFAULT NULL,
  `achievementType` int(1) DEFAULT NULL,
  `caselotId` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_draw_bank`;

CREATE TABLE `user_draw_bank` (
  `id` varchar(255) NOT NULL,
  `bank_card` varchar(255) DEFAULT NULL,
  `bank_name` varchar(255) DEFAULT NULL,
  `bank_type` varchar(255) DEFAULT NULL,
  `branch` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `update_time` timestamp(6) NULL DEFAULT NULL,
  `userno` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_DRAW_BANK_USERNO` (`userno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `userno` varchar(255) NOT NULL,
  `agency_no` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `last_login_time` timestamp(6) NULL DEFAULT NULL,
  `passwd` varchar(255) NOT NULL,
  `phoneno` varchar(255) DEFAULT NULL,
  `photo_url` varchar(255) DEFAULT NULL,
  `qq` varchar(255) DEFAULT NULL,
  `real_name` varchar(255) DEFAULT NULL,
  `register_time` timestamp(6) NULL DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `terminal_type` int(10) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`userno`),
  UNIQUE KEY `INDEX_UN_USER_INFO` (`username`),
  UNIQUE KEY `idx_info_phoneno` (`phoneno`),
  KEY `IDX_USER_INFO` (`register_time`),
  KEY `idx_un_phoneno` (`phoneno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_rebate`;

CREATE TABLE `user_rebate` (
  `id` varchar(255) NOT NULL,
  `userno` varchar(200) NOT NULL,
  `lottery_type` int(10) NOT NULL,
  `rebate_type` int(10) NOT NULL,
  `bet_amount` bigint(20) DEFAULT '0',
  `point_location` decimal(19,2) NOT NULL,
  `is_agent` int(10) NOT NULL DEFAULT '0',
  `agency_no` varchar(200) DEFAULT '0',
  `create_time` datetime(6) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `is_paused` int(5) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `idx_user_rebat_u` (`userno`),
  KEY `idx_user_rebat_t` (`rebate_type`,`create_time`),
  KEY `idex_rebate_lottery` (`lottery_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_rebate_instant`;
CREATE TABLE `user_rebate_instant` (
  `orderid` varchar(255) NOT NULL,
  `userno` varchar(255) DEFAULT NULL,
  `amount` decimal(19,2) NOT NULL,
  `point_location` decimal(10,2) NOT NULL,
  `rebate_amount` decimal(19,2) NOT NULL,
  `create_time` datetime(6) NOT NULL,
  `bet_type` int(10) DEFAULT '0',
  `buy_amount` decimal(19,2) DEFAULT '0.00',
  `safe_amount` decimal(19,2) DEFAULT '0.00',
  PRIMARY KEY (`orderid`),
  KEY `idx_user_rebate_iu` (`userno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
DROP TABLE IF EXISTS `user_rebate_statistic`;

CREATE TABLE `user_rebate_statistic` (
  `id` bigint(20) NOT NULL,
  `agency_no` varchar(200) NOT NULL,
  `lottery_type` int(10) NOT NULL,
  `amount` decimal(19,2) NOT NULL,
  `rebate_amount` decimal(19,2) NOT NULL,
  `point_location` decimal(19,2) NOT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `belong_to` varchar(200) NOT NULL,
  `is_agent` int(5) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `statistic_lottery` int(10) NOT NULL,
  `statistic_type` int(5) NOT NULL DEFAULT '0',
  `month_num` bigint(20) DEFAULT '0',
  KEY `idx_pk_rebate_mstatistic` (`id`,`agency_no`,`lottery_type`),
  KEY `idx_rebate_mstatisticb` (`belong_to`),
  KEY `idx_rebate_mstatisticl` (`statistic_lottery`),
  KEY `idx_rebate_static_mn` (`month_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
DROP TABLE IF EXISTS `user_recharge_give`;

CREATE TABLE `user_recharge_give` (
  `id` varchar(200) NOT NULL,
  `status` int(10) NOT NULL,
  `recharge_give_type` int(2) NOT NULL,
  `recharge_amount` decimal(19,2) NOT NULL,
  `give_amount` decimal(19,2) NOT NULL,
  `start_time` datetime(6) NOT NULL,
  `finish_time` datetime(6) NOT NULL,
  `for_scope` int(2) DEFAULT '1',
  `for_limit` int(2) DEFAULT '1',
  `not_draw_perset` decimal(5,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_recharge_give_detail`;

CREATE TABLE `user_recharge_give_detail` (
  `give_id` varchar(200) NOT NULL DEFAULT '',
  `userno` varchar(200) NOT NULL DEFAULT '',
  `recharge_amount` decimal(19,2) NOT NULL DEFAULT '0.00',
  `give_amount` decimal(19,2) NOT NULL DEFAULT '0.00',
  `create_time` datetime(6) DEFAULT NULL,
  `status` int(2) NOT NULL DEFAULT '3',
  `update_time` datetime(6) DEFAULT NULL,
  `finish_time` datetime(6) DEFAULT NULL,
  `transation_id` varchar(200) NOT NULL DEFAULT '0',
  `memo` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`give_id`,`userno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_transaction`;

CREATE TABLE `user_transaction` (
  `id` varchar(255) NOT NULL,
  `amount` decimal(19,2) DEFAULT NULL,
  `channel` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `fee` decimal(19,2) DEFAULT NULL,
  `finish_time` timestamp(6) NULL DEFAULT NULL,
  `give_amount` decimal(19,2) DEFAULT '0.00',
  `give_id` varchar(255) DEFAULT NULL,
  `pay_type` varchar(255) DEFAULT NULL,
  `real_amount` decimal(19,2) DEFAULT '0.00',
  `status` int(11) NOT NULL,
  `trade_no` varchar(255) DEFAULT NULL,
  `userno` varchar(255) NOT NULL,
  `card_id` varchar(200) DEFAULT NULL,
  `passwd` varchar(200) DEFAULT NULL,
  `not_draw_perset` decimal(5,2) NOT NULL DEFAULT '0.00',
  PRIMARY KEY (`id`),
  KEY `IDX_TRANSACTION_userno` (`userno`),
  KEY `IDX_TRANSACTION_create` (`create_time`),
  KEY `IDX_TRANSACTION_tur` (`trade_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `zc_race`;

CREATE TABLE `zc_race` (
  `id` bigint(20) NOT NULL,
  `average_index` varchar(255) DEFAULT NULL,
  `away_team` varchar(255) DEFAULT NULL,
  `create_time` timestamp(6) NULL DEFAULT NULL,
  `ext` varchar(255) DEFAULT NULL,
  `final_score` varchar(255) DEFAULT NULL,
  `half_score` varchar(255) DEFAULT NULL,
  `home_team` varchar(255) DEFAULT NULL,
  `is_possibledelay` varchar(255) DEFAULT NULL,
  `lottery_type` int(11) DEFAULT NULL,
  `match_date` timestamp(6) NULL DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL,
  `match_name` varchar(255) DEFAULT NULL,
  `match_num` int(11) DEFAULT NULL,
  `phase` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `zc_type_phase_match` (`lottery_type`,`phase`,`match_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
