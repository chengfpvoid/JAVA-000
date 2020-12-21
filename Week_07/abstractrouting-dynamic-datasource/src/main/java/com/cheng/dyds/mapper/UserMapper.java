package com.cheng.dyds.mapper;

import com.cheng.dyds.annotation.Ds;
import com.cheng.dyds.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {

    /**
     * 新增用户
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 更新用户信息
     * @param user
     * @return
     * */
    int update(User user);

    /**
     * 根据id删除
     * @param id
     * @return
     */

    int deleteById(Long id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @Ds("slave")
    User selectById(Long id);

    /**
     * 查询所有用户信息
     * @return
     */
    @Ds("slave")
    List<User> selectAll();
}
