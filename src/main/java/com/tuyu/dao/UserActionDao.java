package com.tuyu.dao;

import com.tuyu.dto.UserActionDto;
import com.tuyu.po.UserAction;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 用户行为
 *
 * @author tuyu
 * @date 8/8/18
 * Talk is cheap, show me the code.
 */
public interface UserActionDao {

    /**
     * 新增
     * @param userAction
     * @return
     */
    int add(@Param("tableNum") String tableNum, @Param("userAction") UserAction userAction);

    /**
     * 查询100条数据
     * @return
     */
    List<UserAction> list100();


    /**
     * 查询前top N的记录
     * @param userActionDto 主要参数: beginDate,
     *                      endDate,
     *                      topN,
     *                      groupBy(聚合的字段名）
     * @return
     */
    List<Map<String, Object>> top(UserActionDto userActionDto);
}
