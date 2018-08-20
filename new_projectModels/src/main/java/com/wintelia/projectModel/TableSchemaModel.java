package com.wintelia.projectModel;

import java.io.Serializable;

public class TableSchemaModel implements Serializable {
    /**
     * 数据库名称
     */
    private String tableschema;
    /**
     * 表名
     */
    private String tablename;
    /**
     * 列名
     */
    private String columnname;
    /**
     * 列类型
     */
    private String columntype;
    /**
     * 数据类型
     */
    private String datatype;
    /**
     * 列序号
     */
    private String ordinalposition;
    /**
     * 列主外键
     */
    private String columnkey;
    /**
     * 描述
     */
    private String columncomment;

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

    public String getColumnname() {
        return columnname;
    }

    public void setColumnname(String columnname) {
        this.columnname = columnname;
    }

    public String getColumntype() {
        return columntype;
    }

    public void setColumntype(String columntype) {
        this.columntype = columntype;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }

    public String getOrdinalposition() {
        return ordinalposition;
    }

    public void setOrdinalposition(String ordinalposition) {
        this.ordinalposition = ordinalposition;
    }

    public String getColumnkey() {
        return columnkey;
    }

    public void setColumnkey(String columnkey) {
        this.columnkey = columnkey;
    }

    public String getColumncomment() {
        return columncomment;
    }

    public void setColumncomment(String columncomment) {
        this.columncomment = columncomment;
    }
}
