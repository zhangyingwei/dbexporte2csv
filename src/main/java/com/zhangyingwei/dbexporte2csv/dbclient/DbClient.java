package com.zhangyingwei.dbexporte2csv.dbclient;

import org.apache.log4j.Logger;
import java.sql.*;
import java.util.*;

/**
 * Created by zhangyw on 2016/9/21.
 */
public class DbClient {
    private Logger logger = Logger.getLogger(DbClient.class);
    private Connection connection;
    private String url;
    private String username;
    private String password;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DbClient(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        logger.info("DbClient init by: url->"+url+" username->"+username+" password->"+password);
        this.getConnection();
    }

    private void getConnection(){
        try {
            this.connection = DriverManager.getConnection(this.url,this.username,this.password);
            logger.info("get connection");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert(String sql){
        this.executeStatement(sql);
    }
    public void delete(String sql){
        this.executeStatement(sql);
    }
    public void update(String sql){
        this.executeStatement(sql);
    }
    public List<Map<String,Object>> select(String sql){
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Map<String,Object>> list = null;
        try {
            statement = this.connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            list = this.getKVFromResultSet(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(resultSet);
            this.close(statement);
        }
        return list;
    }
    private List getKVFromResultSet(ResultSet resultSet){
        List<Map<String,Object>> list = new ArrayList<Map<String, Object>>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            while(resultSet.next()){
                Map<String,Object> map = new LinkedHashMap<String, Object>();
                int count = metaData.getColumnCount();
                for(int i = 1;i<=count;i++){
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    map.put(columnName,value);
                }
                list.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void executeStatement(String sql){
        PreparedStatement statement = null;
        try {
            statement = this.connection.prepareStatement(sql);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            this.close(statement);
        }
    }

    private void close(Statement statement){
        if(statement!=null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void close(ResultSet resultSet){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private void close(Connection connection){
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
