package com.zhangyingwei.dbexporte2csv.sql.Criteria;

import com.zhangyingwei.dbexporte2csv.sql.SqlCriteria;

/**
 * Created by zhangyw on 2016/9/22.
 */
public class MyCriteria implements SqlCriteria{
    public String criteria() {
        return " and id>20";
    }
}
