package com.github.shiayanga.mongodb;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoDatabase;
import com.mongodb.internal.connection.MongoCredentialWithCache;
import org.apache.calcite.adapter.mongodb.MongoSchema;
import org.apache.calcite.adapter.mongodb.MongoSchemaFactory;
import org.apache.calcite.adapter.mongodb.MongoTable;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;
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
        properties.setProperty("lex","JAVA");
        properties.setProperty("remarks","true");
        properties.setProperty("parserFactory","org.apache.calcite.sql.parser.impl.SqlParserImpl#FACTORY");
        Connection connection = DriverManager.getConnection("jdbc:calcite:", properties);
        CalciteConnection calciteConnection = connection.unwrap(CalciteConnection.class);
        SchemaPlus rootSchema = calciteConnection.getRootSchema();



        HashMap<String, Object> map = new HashMap<>();
        map.put("host","192.168.0.255:27017");
        map.put("database","qqq");
        map.put("SCRAM-SHA-1","SCRAM-SHA-1");
        map.put("username","qqq");
        map.put("authDatabase","qqq");
        map.put("password","123453456");
        map.put("authMechanism","SCRAM-SHA-1");

        Schema mongoSchema = new MongoSchemaFactory().create(rootSchema, "qqq", map);
        rootSchema.add("qqq",mongoSchema);

        Statement statement = calciteConnection.createStatement();
        ResultSet resultSet = statement.executeQuery("select " +
                "CAST(_MAP ['formFillId'] AS varchar (50)) AS formFillId," +
                "CAST(_MAP ['formId'] AS varchar (50)) AS formId," +
                "CAST(_MAP ['tenantId'] AS varchar (50)) AS tenantId," +
                "CAST(_MAP ['submitSsoUserId'] AS varchar (50)) AS submitSsoUserId," +
                "CAST(_MAP ['submitUserName'] AS varchar (50)) AS submitUserName " +
                "from lotdb.form_fill_2302231149381209018");

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
