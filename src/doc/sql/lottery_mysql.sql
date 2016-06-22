CREATE TABLE `t_award_level` (
  `lottery_type` int(11) NOT NULL,
  `prize_level` varchar(255) NOT NULL,
  `extra_prize` bigint(20) DEFAULT NULL,
  `level_name` varchar(255) NOT NULL,
  `prize` bigint(20) NOT NULL,
  PRIMARY KEY (`lottery_type`,`prize_level`)
)

--初始合买用户
insert into `user_info` (`userno`, `agency_no`, `alias`, `email`, `idcard`, `last_login_time`, `passwd`, `phoneno`, `photo_url`, `qq`, `real_name`, `register_time`, `sex`, `status`, `terminal_type`, `username`) values('123456','0','0','lottery@sample.com','220622198310032015','2014-05-19 10:18:47','123456','15901528135','http://www.baidu.com','572914691','冯钦云','2014-05-19 10:18:47',NULL,'1',NULL,'allof');
insert into `user_account` (`userno`, `balance`, `draw_balance`, `freeze`, `lasttrade_time`, `last_trade_amt`, `last_freeze`, `mac`, `total_balance`, `total_bet_amt`, `totalgiveamt`, `total_prize_amt`, `username`) values('123456','0','0','0','2014-05-19 10:18:47','0','0',NULL,'0','0','0','0','allof');



--20150618 fengqinyun  ticket 增加字段
alter table ticket add column order_amount decimal(19,2) NOT NULL DEFAULT 0.00;




--20150623 liuhongxing ticket增加字段
alter table ticket add column ticket_end_time timestamp(6) NOT NULL DEFAULT '2010-01-01 00:00:00';

--20150721 liuhongxing lottery_order增加字段
alter table lottery_order add column code_filter int(2) NOT NULL DEFAULT 0;

--20150807 fengqinyun ticket 增加 serialId 字段
alter table ticket add column serial_id VARCHAR(4000)  DEFAULT '0';

create index idx_ticket_agentid on ticket(agent_id);

--20150830 fengqinyun 票算奖
alter table user_account_detail add column order_prize_amount decimal(19,2)  NULL DEFAULT 0.00;
alter table user_account_detail add column order_prize_type int(5)  NULL DEFAULT 0;
alter table user_account_detail add column orderid varchar(255)  NULL DEFAULT 0;

create index idx_uaccountdetail_orderid on user_account_detail(orderid);


-- 20151122 冯钦云
alter table ticket modify machine_code varchar(200) default '0';
alter table ticket modify sell_run_code varchar(200) default '0';
--20151125 冯钦云
create index idx_ticketprinttime on ticket(print_time);
--20151126 冯钦云
create index idx_merchantorderstatus on merchant_order(order_status);

--20160114 fengqinyun ticket增加字段
alter table ticket add column agent_end_time timestamp(6)  NULL DEFAULT '2010-01-01 00:00:00';
alter table lottery_order add column agent_end_time timestamp(6)  NULL DEFAULT '2010-01-01 00:00:00';

--20160325 fengqinyun lottery_order 增加字段
alter table lottery_order add COLUMN  preferential_amount decimal(19) not null default '0';
alter table lottery_order add COLUMN  memo VARCHAR(255) default '';

--20160416 fengqinyun 优化ticket,order

alter table ticket add index idx_st_lo_te_de ( status , lottery_type , terminal_id , dead_line )
alter table lottery_order add index idx_userno_order_result_status ( userno , order_result_status )


ALTER TABLE ticket ADD INDEX IDX_STATUS_CREATE_TIME (STATUS,CREATE_TIME)


CREATE INDEX idx_transaction_status on user_transaction(STATUS)
CREATE INDEX idx_transaction_chanel on user_transaction(channel)