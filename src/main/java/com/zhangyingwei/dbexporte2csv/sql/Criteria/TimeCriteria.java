package com.zhangyingwei.dbexporte2csv.sql.Criteria;

import com.zhangyingwei.dbexporte2csv.sql.SqlCriteria;

/**
 * Created by zhangyw on 2016/9/22.
 */
public class TimeCriteria implements SqlCriteria{
    public String criteria() {
        return " and createtime>'2016-08-05 21:29:00'";
    }
}
