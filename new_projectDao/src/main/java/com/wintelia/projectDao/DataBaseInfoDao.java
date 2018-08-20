package com.wintelia.projectDao;

import com.wintelia.projectModel.DatabaseModel;
import com.wintelia.projectModel.TableSchemaModel;

import java.util.List;

public interface DataBaseInfoDao {

    /**
     * 得到当前数据库的所有数据表
     * @return  数据表集合
     */
    List<DatabaseModel> GetAllTables();

    /**
     * 查询表的结构
     * @param tablename  表名
     * @return  表的列结构
     */
    List<TableSchemaModel> GetTableSchemaInfo(String tablename);

}
