package com.wintelia.projectModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class ${tablename}Model implements Serializable {

<#list  columns as column>

<#if column.description??>
    /**
     * ${column.description}
     */
</#if>
    private ${column.dataType} ${column.lowerName};


    public ${column.dataType} get${column.upperName}() {
        return ${column.lowerName};
    }

    public void set${column.upperName}(${column.dataType} ${column.lowerName}) {
        this.${column.lowerName} = ${column.lowerName};
    }
</#list>
}
