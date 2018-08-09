-- create database
create database if not exists `my_wifi` character set 'utf8' collate 'utf8_general_ci';

create table if not exists `user_action_0` (
    `id` bigint(20) not null auto_increment comment '序号',
    `date` varchar(64) not null default '' comment '日期',
    `group_id` int(16) not null default 0 comment  '租户ID',
    `radio_mac` varchar(64) not null default '' comment 'ap的radio MAC，它不同于ap的MAC，一个ap只有一个MAC，可以有多个radio MAC',
    `user` varchar(64) not null default '' comment '用户MAC地址',
    `user_name` varchar(32) not null default '' comment '用户名（用户手机号）',
    `dev_id` bigint(10) default null default 0 comment '设备ID，AC网关',
    `host_ip` varchar(254) default null default '' comment '源IP',
    `dst_ip` varchar(254) default null default '' comment '目标IP',
    `ip_version` varchar(32) default null default '' comment 'IP类型',
    `site` varchar(254) default null default '' comment '产生日志的位置',
    `tm_type` varchar(254) default null default '' comment '产生日志的终端类型',
    `serv` varchar(254) not null default '' comment '应用类型名称',
    `app` varchar(254) not null default '' comment '具体应用名称',
    `src_port` int(5) unsigned not null default 0 comment '源端口的值',
    `serv_port` int(5) unsigned not null default 0 comment '目标端口的值',
    `net_action` varchar(32) not null default '' comment '日志的网络行为，取值分别为：记录、拒绝、发现病毒',
    `record_time` time not null comment '产生日志的时间',
    `result` text not null  comment '抓包的内容，以xml格式存储，内容中所涉及的字段解释见行为分析表结构中附表A',
    `create_time` datetime not null comment '数据首次写入时间',
    primary key(`id`)
) engine=myisam default charset='utf8' comment '用户行为表';


create table if not exists `user_action_1` (
    `id` bigint(20) not null auto_increment comment '序号',
    `date` varchar(64) not null default '' comment '日期',
    `group_id` int(16) not null default 0 comment  '租户ID',
    `radio_mac` varchar(64) not null default '' comment 'ap的radio MAC，它不同于ap的MAC，一个ap只有一个MAC，可以有多个radio MAC',
    `user` varchar(64) not null default '' comment '用户MAC地址',
    `user_name` varchar(32) not null default '' comment '用户名（用户手机号）',
    `dev_id` bigint(10) default null default 0 comment '设备ID，AC网关',
    `host_ip` varchar(254) default null default '' comment '源IP',
    `dst_ip` varchar(254) default null default '' comment '目标IP',
    `ip_version` varchar(32) default null default '' comment 'IP类型',
    `site` varchar(254) default null default '' comment '产生日志的位置',
    `tm_type` varchar(254) default null default '' comment '产生日志的终端类型',
    `serv` varchar(254) not null default '' comment '应用类型名称',
    `app` varchar(254) not null default '' comment '具体应用名称',
    `src_port` int(5) unsigned not null default 0 comment '源端口的值',
    `serv_port` int(5) unsigned not null default 0 comment '目标端口的值',
    `net_action` varchar(32) not null default '' comment '日志的网络行为，取值分别为：记录、拒绝、发现病毒',
    `record_time` time not null comment '产生日志的时间',
    `result` text not null  comment '抓包的内容，以xml格式存储，内容中所涉及的字段解释见行为分析表结构中附表A',
    `create_time` datetime not null comment '数据首次写入时间',
    primary key(`id`)
) engine=myisam default charset='utf8' comment '用户行为表';

create table if not exists `user_action_2` (
    `id` bigint(20) not null auto_increment comment '序号',
    `date` varchar(64) not null default '' comment '日期',
    `group_id` int(16) not null default 0 comment  '租户ID',
    `radio_mac` varchar(64) not null default '' comment 'ap的radio MAC，它不同于ap的MAC，一个ap只有一个MAC，可以有多个radio MAC',
    `user` varchar(64) not null default '' comment '用户MAC地址',
    `user_name` varchar(32) not null default '' comment '用户名（用户手机号）',
    `dev_id` bigint(10) default null default 0 comment '设备ID，AC网关',
    `host_ip` varchar(254) default null default '' comment '源IP',
    `dst_ip` varchar(254) default null default '' comment '目标IP',
    `ip_version` varchar(32) default null default '' comment 'IP类型',
    `site` varchar(254) default null default '' comment '产生日志的位置',
    `tm_type` varchar(254) default null default '' comment '产生日志的终端类型',
    `serv` varchar(254) not null default '' comment '应用类型名称',
    `app` varchar(254) not null default '' comment '具体应用名称',
    `src_port` int(5) unsigned not null default 0 comment '源端口的值',
    `serv_port` int(5) unsigned not null default 0 comment '目标端口的值',
    `net_action` varchar(32) not null default '' comment '日志的网络行为，取值分别为：记录、拒绝、发现病毒',
    `record_time` time not null comment '产生日志的时间',
    `result` text not null  comment '抓包的内容，以xml格式存储，内容中所涉及的字段解释见行为分析表结构中附表A',
    `create_time` datetime not null comment '数据首次写入时间',
    primary key(`id`)
) engine=myisam default charset='utf8' comment '用户行为表';

create table if not exists `user_action_3` (
    `id` bigint(20) not null auto_increment comment '序号',
    `date` varchar(64) not null default '' comment '日期',
    `group_id` int(16) not null default 0 comment  '租户ID',
    `radio_mac` varchar(64) not null default '' comment 'ap的radio MAC，它不同于ap的MAC，一个ap只有一个MAC，可以有多个radio MAC',
    `user` varchar(64) not null default '' comment '用户MAC地址',
    `user_name` varchar(32) not null default '' comment '用户名（用户手机号）',
    `dev_id` bigint(10) default null default 0 comment '设备ID，AC网关',
    `host_ip` varchar(254) default null default '' comment '源IP',
    `dst_ip` varchar(254) default null default '' comment '目标IP',
    `ip_version` varchar(32) default null default '' comment 'IP类型',
    `site` varchar(254) default null default '' comment '产生日志的位置',
    `tm_type` varchar(254) default null default '' comment '产生日志的终端类型',
    `serv` varchar(254) not null default '' comment '应用类型名称',
    `app` varchar(254) not null default '' comment '具体应用名称',
    `src_port` int(5) unsigned not null default 0 comment '源端口的值',
    `serv_port` int(5) unsigned not null default 0 comment '目标端口的值',
    `net_action` varchar(32) not null default '' comment '日志的网络行为，取值分别为：记录、拒绝、发现病毒',
    `record_time` time not null comment '产生日志的时间',
    `result` text not null  comment '抓包的内容，以xml格式存储，内容中所涉及的字段解释见行为分析表结构中附表A',
    `create_time` datetime not null comment '数据首次写入时间',
    primary key(`id`)
) engine=myisam default charset='utf8' comment '用户行为表';

create table if not exists `user_action_4` (
    `id` bigint(20) not null auto_increment comment '序号',
    `date` varchar(64) not null default '' comment '日期',
    `group_id` int(16) not null default 0 comment  '租户ID',
    `radio_mac` varchar(64) not null default '' comment 'ap的radio MAC，它不同于ap的MAC，一个ap只有一个MAC，可以有多个radio MAC',
    `user` varchar(64) not null default '' comment '用户MAC地址',
    `user_name` varchar(32) not null default '' comment '用户名（用户手机号）',
    `dev_id` bigint(10) default null default 0 comment '设备ID，AC网关',
    `host_ip` varchar(254) default null default '' comment '源IP',
    `dst_ip` varchar(254) default null default '' comment '目标IP',
    `ip_version` varchar(32) default null default '' comment 'IP类型',
    `site` varchar(254) default null default '' comment '产生日志的位置',
    `tm_type` varchar(254) default null default '' comment '产生日志的终端类型',
    `serv` varchar(254) not null default '' comment '应用类型名称',
    `app` varchar(254) not null default '' comment '具体应用名称',
    `src_port` int(5) unsigned not null default 0 comment '源端口的值',
    `serv_port` int(5) unsigned not null default 0 comment '目标端口的值',
    `net_action` varchar(32) not null default '' comment '日志的网络行为，取值分别为：记录、拒绝、发现病毒',
    `record_time` time not null comment '产生日志的时间',
    `result` text not null  comment '抓包的内容，以xml格式存储，内容中所涉及的字段解释见行为分析表结构中附表A',
    `create_time` datetime not null comment '数据首次写入时间',
    primary key(`id`)
) engine=myisam default charset='utf8' comment '用户行为表';

create table if not exists `user_action` (
    `id` bigint(20) not null auto_increment comment '序号',
    `date` varchar(64) not null default '' comment '日期',
    `group_id` int(16) not null default 0 comment  '租户ID',
    `radio_mac` varchar(64) not null default '' comment 'ap的radio MAC，它不同于ap的MAC，一个ap只有一个MAC，可以有多个radio MAC',
    `user` varchar(64) not null default '' comment '用户MAC地址',
    `user_name` varchar(32) not null default '' comment '用户名（用户手机号）',
    `dev_id` bigint(10) default null default 0 comment '设备ID，AC网关',
    `host_ip` varchar(254) default null default '' comment '源IP',
    `dst_ip` varchar(254) default null default '' comment '目标IP',
    `ip_version` varchar(32) default null default '' comment 'IP类型',
    `site` varchar(254) default null default '' comment '产生日志的位置',
    `tm_type` varchar(254) default null default '' comment '产生日志的终端类型',
    `serv` varchar(254) not null default '' comment '应用类型名称',
    `app` varchar(254) not null default '' comment '具体应用名称',
    `src_port` int(5) unsigned not null default 0 comment '源端口的值',
    `serv_port` int(5) unsigned not null default 0 comment '目标端口的值',
    `net_action` varchar(32) not null default '' comment '日志的网络行为，取值分别为：记录、拒绝、发现病毒',
    `record_time` time not null comment '产生日志的时间',
    `result` text not null  comment '抓包的内容，以xml格式存储，内容中所涉及的字段解释见行为分析表结构中附表A',
    `create_time` datetime not null comment '数据首次写入时间',
    primary key(`id`)
) engine=merge union=(`user_action_0`, `user_action_1`, `user_action_2`, `user_action_3`, `user_action_4`)
insert_method=last default charset='utf8' comment '用户行为表';


alter table user_action_0 add index `idx_date`(`date`)
alter table user_action_1 add index `idx_date`(`date`);
alter table user_action_2 add index `idx_date`(`date`);
alter table user_action_3 add index `idx_date`(`date`);
alter table user_action_4 add index `idx_date`(`date`);
alter table user_action add index `idx_date`(`date`);