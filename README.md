# dbexporte2csv
一个数据库表导出为cvs文件的小程序

##配置文件说明
```
jdbc.url=jdbc:mysql://localhost:3306/hermes
jdbc.username=username
jdbc.password=password
#list
tablelist.mycriteria=t_sys_rss_article,t_sys_rss_website
tablelist.timecriteria=t_user_userinfo
tablelist.allcriteria=t_user_rss_site
#basepath
basepath=csvfile/
```

##程序执行方法
```
CriteriaMap.registe("mycriteria",new MyCriteria());
CriteriaMap.registe("timecriteria",new TimeCriteria());
CriteriaMap.registe("allcriteria",new AllCriteria());
CsvExporter exporter = new CsvExporter();
exporter.export();
```
