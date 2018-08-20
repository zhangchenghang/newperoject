package com.wintelia.baseService.impl;

import com.wintelia.baseService.DataBaseOperateService;
import com.wintelia.projectDao.DataBaseInfoDao;
import com.wintelia.projectModel.DatabaseModel;
import com.wintelia.projectModel.TableSchemaModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataBaseOperateServiceImpl implements DataBaseOperateService {

    @Autowired
    private  DataBaseInfoDao dataBaseInfoDao;

    @Override
    public List<DatabaseModel> GetAllTables() {
        return dataBaseInfoDao.GetAllTables();
    }

    @Override
    public List<TableSchemaModel> GetTableSchema(String tablename) {
        return dataBaseInfoDao.GetTableSchemaInfo(tablename);
    }
}
