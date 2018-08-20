package com.wintelia.projectModel;

import java.io.Serializable;
import java.util.Date;

public class DatabaseModel implements Serializable {

    private String tableschema;
    private String tablename;
    private String tabletype;
    private Date createtime;
    private String tablecomment;

    public String getTablecomment() {
        return tablecomment;
    }

    public void setTablecomment(String tablecomment) {
        this.tablecomment = tablecomment;
    }

    public String getTableschema() {
        return tableschema;
    }

    public void setTableschema(String tableschema) {
        this.tableschema = tableschema;
    }

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String getTabletype() {
        return tabletype;
    }

    public void setTabletype(String tabletype) {
        this.tabletype = tabletype;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
