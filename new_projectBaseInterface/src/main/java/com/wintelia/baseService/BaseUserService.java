package com.wintelia.baseService;

import com.wintelia.projectModel.SystemadminModel;
import com.wintelia.projectModel.resultModel;

import java.util.List;

public interface BaseUserService {
    /**
     * 用户登录
     *
     * @param username
     * @return
     */
    resultModel userlogin(String username, String pwd);

    /**
     * 根据token得到用户登陆信息
     * @param token
     * @return
     */
    resultModel GetUserModelByToken(String token);

    /**
     * 用户安全退出
     * @param token
     */
    void  userloginout(String token);

    /**
     * 查询全部系统用户
     * @return
     */
    List<SystemadminModel> GetAllSystemAdminList();
}
