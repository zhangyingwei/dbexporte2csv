package com.zhangyingwei.dbexporte2csv.csv;


import com.zhangyingwei.dbexporte2csv.common.Common;
import com.zhangyingwei.dbexporte2csv.dbclient.DbClient;
import com.zhangyingwei.dbexporte2csv.sql.SqlCriteria;
import com.zhangyingwei.dbexporte2csv.sql.SqlHandler;
import com.zhangyingwei.dbexporte2csv.sql.conf.CriteriaMap;
import com.zhangyw.utils.file.FileUtil;
import com.zhangyw.utils.properties.PropertiesUtil;
import org.apache.log4j.Logger;

import java.util.*;

/**
 * Created by zhangyw on 2016/9/22.
 */
public class CsvExporter {
    private Logger logger = Logger.getLogger(CsvExporter.class);
    private DbClient client;
    private SqlHandler sqlHandler;
    String url = PropertiesUtil.get(Common.CONF_PATH,"jdbc.url");
    String username = PropertiesUtil.get(Common.CONF_PATH,"jdbc.username");
    String password = PropertiesUtil.get(Common.CONF_PATH,"jdbc.password");
    public CsvExporter() {
        this.client = new DbClient(url,username,password);
        this.sqlHandler = new SqlHandler();
    }

    public void export(){
        Map<String,List<String>> tableMap = this.getTablesMap();
        for(String key:tableMap.keySet()){
            SqlCriteria criteria = CriteriaMap.get(key);
            List<String> tables = tableMap.get(key);
            for(String table:tables){
                String data = this.selectData(table,criteria);
                this.data2CvsFile(PropertiesUtil.get(Common.CONF_PATH,"basepath")+table+Common.FILE_SUFFIX,data);
            }
        }
    }
    private Map<String,List<String>> getTablesMap(){
        Map<String,List<String>> result = new HashMap<String, List<String>>();
        Map<String, String> properties = PropertiesUtil.getByPrefix(Common.CONF_PATH,Common.TABLELIST_PREFIX);
        for(String key:properties.keySet()){
            result.put(key.split("\\.")[1], Arrays.asList(properties.get(key).split(",")));
        }
        logger.info("tableMap:"+result);
        return result;
    }
    public String selectData(String tableName, SqlCriteria criteria){
        List<Map<String,Object>> dataList =  this.client.select(sqlHandler.parseSql(tableName, criteria));
        StringBuffer result = new StringBuffer();
        for(Map<String,Object> map:dataList){
            Object[] keys = map.keySet().toArray();
            for(int i = 0;i<keys.length;i++){
                result.append(map.get(keys[i]));
                if(i+1<keys.length){
                    result.append(",");
                }
            }
            result.append("\n");
        }
        logger.info("data size:"+result.length());
        return result.toString();
    }
    public void data2CvsFile(String filePath,String dataStr){
        logger.info("export file:"+filePath);
        FileUtil.write(filePath,dataStr);
        logger.info(filePath+" finish");
    }
}
