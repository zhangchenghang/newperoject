<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.wintelia.projectDao.${tablename}Dao">
    <resultMap id="${tablename}map" type="com.wintelia.projectModel.${tablename}Model">
        <#list  columns as column>
        <#if column.extend??>
            <id column="${column.lowerName}" property="${column.lowerName}"/>
        <#else>
             <result column="${column.lowerName}" property="${column.lowerName}"/>
        </#if>
        </#list>
    </resultMap>

    <select id="Get${tablename}" resultMap="${tablename}map">
        SELECT
        <#list  columns as column>${column.lowerName},</#list>
        FROM
          ${tablename}
        WHERE
        1=1
    </select>

    <insert id="Add${tablename}" parameterType="com.wintelia.projectModel.${tablename}Model">
        INSERT INTO  ${tablename}(
        <#list  columns as column>${column.lowerName},</#list>
        )
        VALUES
        (
        <#list  columns as column>#${'{'}${column.lowerName}${'}'},</#list>
        )
    </insert>

    <select id="GetSingle${tablename}Data" resultType="com.wintelia.projectModel.${tablename}Model" parameterType="int">
        SELECT
        <#list  columns as column>${column.lowerName},</#list>
        FROM
    ${tablename}
        WHERE
    </select>
</mapper>