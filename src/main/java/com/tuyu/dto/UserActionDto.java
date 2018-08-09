package com.tuyu.dto;

import com.tuyu.po.UserAction;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户行为
 * @author tuyu
 * @date 8/9/18
 * Talk is cheap, show me the code.
 */
@Data
public class UserActionDto extends UserAction implements Serializable {
    private static final long serialVersionUId = 1L;

    /** 开始时间 */
    private String beginDate;
    /** 截止时间 */
    private String endDate;
    /** 前top N */
    private int topN;
    /** 聚合字段名 */
    private String groupBy;

}
