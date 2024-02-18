package com.github.shiayanga.mysql;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class MySqlDemo {
    public static void main(String[] args) throws Exception {
        // 驱动程序
        Class.forName("org.apache.calcite.jdbc.Driver");
        Class.forName("com.mysql.jdbc.Driver");

        Properties properties = new Properties();
        properties.setProperty("lex", "JAVA");
        properties.setProperty("remarks", "true");
        properties.setProperty("parserFactory", "org.apache.calcite.sql.parser.impl.SqlParserImpl#FACTORY");
        Connection connection = DriverManager.getConnection("jdbc:calcite:", properties);
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        SchemaPlus rootSchema = calciteConnection.getRootSchema();
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl("jdbc:mysql://192.168.0.110:3306/lcdp");
        mysqlDataSource.setUser("lcdp");
        mysqlDataSource.setPassword("eG6wGyTw!mnsCV");

        JdbcSchema schema = JdbcSchema.create(rootSchema, "lcdp", mysqlDataSource, null, "lcdp");
        rootSchema.add("lcdp", schema);

        Statement statement = calciteConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from lcdp.lcdp_screens_screen");
        while (resultSet.next()) {
            String screen_id = resultSet.getString("screen_id");
            String name = resultSet.getString("screen_name");
            String screen_config = resultSet.getString("screen_config");
            String screen_publish_url = resultSet.getString("screen_publish_url");
            String screen_publish_config = resultSet.getString("screen_publish_config");
            String tenant_id = resultSet.getString("tenant_id");
            String create_user_id = resultSet.getString("create_user_id");
            String create_time = resultSet.getString("create_time");
            if (StringUtils.isEmpty(screen_id)) {
                continue;
            }
            System.out.println(screen_id + "\t" + name + "\t" + screen_config + "\t" + screen_config + "\t" + screen_publish_url + "\t" +
                    screen_publish_config + "\t" + tenant_id + "\t" + create_user_id + "\t" + create_time);
        }
        statement.close();
        connection.close();

    }
}
