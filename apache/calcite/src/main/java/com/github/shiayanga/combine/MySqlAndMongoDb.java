package com.github.shiayanga.combine;

import com.alibaba.fastjson2.JSON;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import org.apache.calcite.adapter.jdbc.JdbcSchema;
import org.apache.calcite.adapter.mongodb.MongoSchemaFactory;
import org.apache.calcite.jdbc.CalciteConnection;
import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaPlus;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class MySqlAndMongoDb {
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

        // mysql
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setUrl("jdbc:mysql://192.168.0.110:3306/lcdp");
        mysqlDataSource.setUser("lcdp");
        mysqlDataSource.setPassword("eG6wGyTw!mnsCV");

        JdbcSchema schema = JdbcSchema.create(rootSchema, "lcdp", mysqlDataSource, null, "lcdp");
        rootSchema.add("lcdp", schema);

        // MongoDB
        HashMap<String, Object> map = new HashMap<>();
        map.put("host", "192.168.0.100:28017");
        map.put("database", "lotdb");
        map.put("SCRAM-SHA-1", "SCRAM-SHA-1");
        map.put("username", "lotdb");
        map.put("authDatabase", "lotdb");
        map.put("password", "Tg$vvhEj5vzQ");
        map.put("authMechanism", "SCRAM-SHA-1");

        Schema mongoSchema = new MongoSchemaFactory().create(rootSchema, "lotdb", map);
        rootSchema.add("lotdb", mongoSchema);

        Statement statement = calciteConnection.createStatement();

        /*
         * Calcite包含MongoDB的适配器，这是一个文档存储，用于存储由大致相当于JSON文档的数据组成的文档。
         * 要将MongoDB数据公开给Calcite，将为每个文档集合创建一个表，其中包含一个名为_MAP的列：从文档标识符到其数据的映射。
         */
        String sql = "select l.*,af.form_name" +
                "     from " +
                "     (" +
                "           select" +
                "               CAST(_MAP ['formFillId']        AS varchar (50))    AS formFillId," +
                "               CAST(_MAP ['formId']            AS varchar (50))    AS formId," +
                "               CAST(_MAP ['tenantId']          AS varchar (50))    AS tenantId," +
                "               CAST(_MAP ['submitSsoUserId']   AS varchar (50))    AS submitSsoUserId," +
                "               CAST(_MAP ['createTime']        AS TIMESTAMP)    AS createTime," +
                "               CAST(_MAP ['submitUserName']    AS varchar (50))    AS submitUserName" +
                "           from lotdb.form_fill_2302231149381209018" +
                "     ) l " +
                "     join lcdp.amp_form as af on l.formId = af.form_id";
        /*
         * 上边的sql中，lotdb是MongoDB在当前查询中的别名，lcdp是MySQL在当前查询中的别名
         */
        ResultSet resultSet = statement.executeQuery(sql);

        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i= 1;i <= columnCount;i++){
            String columnLabel = metaData.getColumnLabel(i);
            String columnTypeName = metaData.getColumnTypeName(i);
            System.out.println(columnLabel + "\t" + columnTypeName);
        }

        List<String[]> dataResult = getDataResult(resultSet);
        for (String[] strings : dataResult) {
            System.out.println(JSON.toJSONString(strings));
        }

        /*while (resultSet.next()) {
            String submitUserName = resultSet.getString("submitUserName");
            String form_name = resultSet.getString("form_name");
            String formFillId = resultSet.getString("formFillId");
            String createTime = resultSet.getString("createTime");
            System.out.println("填写人：" +submitUserName + "\t 填写时间：" + createTime + "\t 表单名：" + form_name + "\t 表单填写ID：" + formFillId);
        }*/


    }

    private static List<String[]> getDataResult(ResultSet rs) {
        List<String[]> list = new LinkedList<>();
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                String[] row = new String[columnCount];
                for (int j = 0; j < columnCount; j++) {
                    int columnType = metaData.getColumnType(j + 1);
                    switch (columnType) {
                        case Types.DATE:
                            if (rs.getDate(j + 1) != null) {
                                row[j] = rs.getDate(j + 1).toString();
                            }
                            break;
                        case Types.BOOLEAN:
                            row[j] = rs.getBoolean(j + 1) ? "true" : "false";
                            break;
                        default:
                            if (metaData.getColumnTypeName(j + 1).toLowerCase().equalsIgnoreCase("blob")) {
                                row[j] = rs.getBlob(j + 1) == null ? "" : rs.getBlob(j + 1).toString();
                            } else {
                                row[j] = rs.getString(j + 1);
                            }
                            break;
                    }
                }
                list.add(row);
            }
        } catch (Exception e) {
           e.printStackTrace();
        }
        return list;
    }
}
