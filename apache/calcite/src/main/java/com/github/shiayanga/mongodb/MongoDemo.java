package com.github.shiayanga.mongodb;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import com.mongodb.internal.connection.MongoCredentialWithCache;
import org.apache.calcite.adapter.mongodb.MongoSchema;
import org.apache.calcite.adapter.mongodb.MongoSchemaFactory;
import org.apache.calcite.adapter.mongodb.MongoTable;
import org.apache.calcite.config.Lex;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
import org.apache.calcite.schema.Table;
import org.apache.commons.lang3.StringUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Objects;
import java.util.Properties;

public class MongoDemo {
    public static void main(String[] args) throws Exception{
        // 驱动程序
        Class.forName("org.apache.calcite.jdbc.Driver");
        Class.forName("com.mysql.jdbc.Driver");

        Properties properties = new Properties();
        properties.setProperty("lex", Lex.MYSQL.toString());
        properties.setProperty("remarks","true");
        properties.setProperty("parserFactory","org.apache.calcite.sql.parser.impl.SqlParserImpl#FACTORY");
        Connection connection = DriverManager.getConnection("jdbc:calcite:", properties);
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        SchemaPlus rootSchema = calciteConnection.getRootSchema();



        HashMap<String, Object> map = new HashMap<>();
        map.put("host","192.168.0.100:28017");
        map.put("database","lotdb");
        map.put("SCRAM-SHA-1","SCRAM-SHA-1");
        map.put("username","lotdb");
        map.put("authDatabase","lotdb");
        map.put("password","Tg$vvhEj5vzQ");
        map.put("authMechanism","SCRAM-SHA-1");

        Schema mongoSchema = new MongoSchemaFactory().create(rootSchema, "lotdb", map);
        rootSchema.add("lotdb",mongoSchema);
        Table schemaTable = mongoSchema.getTable("form_fill_2302231149381209018");
        Schema.TableType jdbcTableType = schemaTable.getJdbcTableType();
        Statement statement = calciteConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from lotdb.form_fill_2302231149381209018");

        while (resultSet.next()) {

            String screen_id = resultSet.getString("formFillId");
            String name = resultSet.getString("formId");
            String screen_config = resultSet.getString("tenantId");
            String screen_publish_url = resultSet.getString("submitSsoUserId");
            String screen_publish_config = resultSet.getString("submitUserName");
            if (StringUtils.isEmpty(screen_id)){
                continue;
            }
            System.out.println(screen_id + "\t" + name + "\t" + screen_config + "\t" + screen_config + "\t" + screen_publish_url + "\t" +
                    screen_publish_config);

        }
        statement.close();
        connection.close();
    }



}
