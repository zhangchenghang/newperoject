package com.wintelia.baseService.impl;

import com.wintelia.baseService.BaseUserService;
import com.wintelia.projectCommon.JedisClientPool;
import com.wintelia.projectCommon.JsonUtils;
import com.wintelia.projectDao.SystemadminDao;
import com.wintelia.projectModel.SystemadminModel;
import com.wintelia.projectModel.resultModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BaseUserServiceImpl implements BaseUserService {
    @Value("${USER_SESSION}")
    private String user_session;
    @Value("${SESSION_TIME}")
    private int session_time;
    @Autowired
    private SystemadminDao systemadminDao;

    @Autowired
    private JedisClientPool jedisClientPool;

    @Override
    public resultModel userlogin(String username, String pwd) {
        SystemadminModel userModel = systemadminDao.GetSystemadminByUsername(username);
        if (userModel == null) {
            return resultModel.build(400, "用户名不正确！");
        }
        if (!userModel.getPassword().equals(pwd)) {
            return resultModel.build(400, "密码输入错误！");
        }
        String uuid = UUID.randomUUID().toString();
        userModel.setPassword(null);
        String jsonstr = JsonUtils.objectToJson(userModel);
        jedisClientPool.set(user_session + ":" + uuid, jsonstr);
        jedisClientPool.expire(user_session + ":" + uuid, session_time);
        return resultModel.ok(uuid);
    }

    @Override
    public resultModel GetUserModelByToken(String token) {
        String json = jedisClientPool.get(user_session + ":" + token);
        if (StringUtils.isBlank(json)) {
            return resultModel.build(400, "用户登录已经过期");
        }
        //重置Session的过期时间
        jedisClientPool.expire(user_session + ":" + token, session_time);
        //把json转换成User对象
        SystemadminModel user = JsonUtils.jsonToPojo(json, SystemadminModel.class);
        return resultModel.ok(user);
    }

    @Override
    public void userloginout(String token) {
        try {
            String json = jedisClientPool.get(user_session + ":" + token);
            if (!StringUtils.isBlank(json)) {
                //重置Session的时间为过期
                jedisClientPool.expire(user_session + ":" + token, 0);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<SystemadminModel> GetAllSystemAdminList() {
        try {
            return systemadminDao.GetAllSystemadminData();
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
