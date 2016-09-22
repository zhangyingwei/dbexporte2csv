package com.zhangyingwei.dbexporte2csv.main;

import com.zhangyingwei.dbexporte2csv.csv.CsvExporter;
import com.zhangyingwei.dbexporte2csv.sql.Criteria.AllCriteria;
import com.zhangyingwei.dbexporte2csv.sql.Criteria.MyCriteria;
import com.zhangyingwei.dbexporte2csv.sql.Criteria.TimeCriteria;
import com.zhangyingwei.dbexporte2csv.sql.conf.CriteriaMap;
import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by zhangyw on 2016/9/21.
 */
public class App {
    private static Logger logger = Logger.getLogger(App.class);
    public static void main(String[] args) {
        logger.info("start->"+new Date());
        CriteriaMap.registe("mycriteria",new MyCriteria());
        CriteriaMap.registe("timecriteria",new TimeCriteria());
        CriteriaMap.registe("allcriteria",new AllCriteria());
        CsvExporter exporter = new CsvExporter();
        exporter.export();
        logger.info("finish->"+new Date());
    }
}
