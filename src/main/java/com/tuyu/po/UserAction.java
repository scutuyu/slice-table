package com.tuyu.po;

import lombok.Data;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;

/**
 * 用户行为数据
 *
 * @author tuyu
 * @date 8/8/18
 * Talk is cheap, show me the code.
 */
@Data
public class UserAction implements Serializable {

    private static final long serialVersionUID = 1L;
    /** 记录号 */
    private Long id;
    /** 日期 */
    private String date;
    /** 租户ID */
    private Integer groupId;
    /** ap的radio MAC，它不同于ap的MAC，一个ap只有一个MAC，可以有多个radio MAC */
    private String radioMac;
    /** 用户MAC地址 */
    private String user;
    /** 用户名（用户手机号） */
    private String userName;
    /** 设备ID，AC网关 */
    private Long devId;
    /** 源IP */
    private String hostIp;
    /** 目标IP */
    private String dstIp;
    /** IP类型 */
    private String ipVersion;
    /** 产生日志的位置 */
    private String site;
    /** 产生日志的终端类型 */
    private String tmType;
    /** 应用类型名称 */
    private String serv;
    /** 具体应用名称 */
    private String app;
    /** 源端口的值 */
    private Integer srcPort;
    /** 目标端口的值 */
    private Integer servPort;
    /** 日志的网络行为，取值分别为：记录、拒绝、发现病毒 */
    private String netAction;
    /** 产生日志的时间 */
    private Time recordTime;
    /** 抓包的内容，以xml格式存储，内容中所涉及的字段解释见行为分析表结构中附表A */
    private String result;
    /** 数据首次写入时间 */
    private Timestamp createTime;
}
