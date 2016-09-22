package com.zhangyingwei.dbexporte2csv.sql;

import org.apache.log4j.Logger;

/**
 * Created by zhangyw on 2016/9/22.
 */
public class SqlHandler {
    private Logger logger = Logger.getLogger(SqlHandler.class);
    public String parseSql(String tableName,SqlCriteria criteria){
        String sql = "select * from "+tableName+" where "+criteria.criteria();
        logger.info(sql);
        return sql;
    }
}
