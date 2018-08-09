package com.tuyu.dao;

import com.tuyu.po.UserAction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
     * 查询所有
     * @return
     */
    List<UserAction> list();



}
