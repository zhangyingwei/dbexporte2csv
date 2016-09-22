package com.zhangyingwei.dbexporte2csv.sql.conf;

import com.zhangyingwei.dbexporte2csv.sql.SqlCriteria;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangyw on 2016/9/22.
 */
public class CriteriaMap {
    private static Logger logger = Logger.getLogger(CriteriaMap.class);
    private static Map<String,SqlCriteria> criteriaMap = new HashMap<String, SqlCriteria>();
    public static void registe(String key,SqlCriteria criteria){
        criteriaMap.put(key,criteria);
        logger.info("registe:key->"+key);
    }
    public static SqlCriteria get(String key){
        return criteriaMap.get(key);
    }
}
