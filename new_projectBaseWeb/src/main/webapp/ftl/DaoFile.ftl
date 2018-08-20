package com.wintelia.projectDao;

import com.wintelia.projectModel.${tablename}Model;

import java.util.List;

public interface ${tablename}Dao {

    /**
     * 得到当前数据库的所有数据表
     * @return  所有数据集合
     */
    List<${tablename}Model> GetAll${tablename}Data();

    /**
    * 按照ID查询单条数据
    * @param id
    * @return  单条数据
    */
     ${tablename}Model GetSingle${tablename}Data(int id);

    /**
    * 按照ID查询单条数据
    * @param id
    * @return  单条数据
    */
    int  Add${tablename}(${tablename}Model model);

    }
