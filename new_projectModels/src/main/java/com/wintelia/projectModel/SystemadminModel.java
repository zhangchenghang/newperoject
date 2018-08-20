package com.wintelia.projectModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SystemadminModel implements Serializable {

    /**
     * 用户编号
     */
    private int userid;


    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    /**
     * 用户名
     */
    private String usrname;


    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    /**
     * 登录密码
     */
    private String password;


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 真实姓名
     */
    private String truthname;


    public String getTruthname() {
        return truthname;
    }

    public void setTruthname(String truthname) {
        this.truthname = truthname;
    }

    /**
     * 用户组编号
     */
    private int groupid;


    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    /**
     * 用户组名称
     */
    private String groupname;


    public String getGroupname() {
        return groupname;
    }

    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    /**
     * 创建时间
     */
    private Date createtime;


    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * 用户状态(10  正常 20 禁用)
     */
    private int userstatus;


    public int getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(int userstatus) {
        this.userstatus = userstatus;
    }
}
