package com.github.shiayanga.csv;

import com.alibaba.fastjson2.JSON;
import com.google.common.collect.ImmutableMap;
import org.apache.calcite.adapter.csv.CsvSchema;
import org.apache.calcite.adapter.csv.CsvSchemaFactory;
import org.apache.calcite.adapter.csv.CsvTable;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.util.Sources;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.sql.*;
import java.util.Date;
import java.util.Properties;

public class CsvDemo {
    public static void main(String[] args) throws SQLException {
        String filePath = Sources.of(CsvDemo.class.getResource("/")).file().getAbsolutePath();

        // 构建 CsvScheme 对象，在 Calcite 中，不同数据源对应不同 Scheme
        CsvSchema csvSchema = new CsvSchema(new File(filePath), CsvTable.Flavor.SCANNABLE);

        // 构建连接 Connection
        Properties properties = new Properties();
        // 不区分 SQL 的大小写
        properties.setProperty("caseSensitive", "false");
        // 获取标准 JDBC 连接
        Connection connection = DriverManager.getConnection("jdbc:calcite:", properties);
        // 获取 Calcite 封装的连接
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        // 构建RootSchema，在Calcite中，RootSchema是所有数据源schema的parent，多个不同数据源schema可以挂在同一个RootSchema下，以实现查询不同数据源的目的
        SchemaPlus rootSchema = calciteConnection.getRootSchema();
        rootSchema.add("csv", csvSchema);

        String sql = "select * from csv.lcdp_screens_screen_202312261359";
        Statement statement = calciteConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        //光标向后移动，如果没有更多行，返回false
        while (resultSet.next()) {
            String screen_id = resultSet.getString("screen_id");
            String name = resultSet.getString("screen_name");
            String screen_config = resultSet.getString("screen_config");
            String screen_publish_url = resultSet.getString("screen_publish_url");
            String screen_publish_config = resultSet.getString("screen_publish_config");
            String tenant_id = resultSet.getString("tenant_id");
            String create_user_id = resultSet.getString("create_user_id");
            String create_time = resultSet.getString("create_time");
            if (StringUtils.isEmpty(screen_id)){
                continue;
            }
            System.out.println(screen_id + "\t" + name + "\t" + screen_config + "\t" + screen_config + "\t" + screen_publish_url + "\t" +
                    screen_publish_config + "\t" + tenant_id + "\t" + create_user_id + "\t" + create_time);

        }

    }
}
