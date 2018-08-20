package com.wintelia.projectDao;

import com.wintelia.projectModel.SystemadminModel;

import java.util.List;

public interface SystemadminDao {

    /**
     * 得到当前数据库的所有数据表
     *
     * @return 所有数据集合
     */
    List<SystemadminModel> GetAllSystemadminData();

    /**
     * 按照ID查询单条数据
     *
     * @param userid
     *         用户编码
     * @return 单条数据
     */
    SystemadminModel GetSingleSystemadminData(int userid);

    /**
     * 按照用户名查询用户信息
     * @param username
     * @return
     */
    SystemadminModel GetSystemadminByUsername(String username);

    /**
     * 插入新的管理员信息
     *
     * @param model
     * @return
     */
    int AddSystemadmin(SystemadminModel model);

}