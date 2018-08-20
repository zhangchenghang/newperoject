package com.wintelia.baseService;

import com.wintelia.projectModel.DatabaseModel;
import com.wintelia.projectModel.TableSchemaModel;

import java.util.List;

public interface DataBaseOperateService {

    List<DatabaseModel> GetAllTables();

    List<TableSchemaModel> GetTableSchema(String tablename);
}
