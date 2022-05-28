package com.icode.designpattern.connection;

import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * 获取数据库连接
 *
 * @author caiyq
 * @date 2021/11/22 22:54
 */
public class ConnectionTest {

    @Test
    public void testConnection() throws Exception {
        Driver driver = new com.mysql.jdbc.Driver();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "admin");
        Connection connection = driver.connect(url, properties);
        System.out.println(connection);
    }

    @Test
    public void testConnection2() throws Exception {
        Class<?> clazz = Class.forName("com.mysql.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        String url = "jdbc:mysql://localhost:3306/test";
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "admin");
        Connection connection = driver.connect(url, properties);
        System.out.println(connection);
    }

    @Test
    public void testConnection3() throws Exception {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "admin";

        Class<?> clazz = Class.forName(driverName);
        Driver driver = (Driver) clazz.newInstance();
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test
    public void testConnection4() throws Exception {
        String driverName = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/test";
        String user = "root";
        String password = "admin";

        Class.forName(driverName);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }

    @Test
    public void testConnection5() throws Exception {
        InputStream in = ConnectionTest.class.getClassLoader().getResourceAsStream("jdbc.properties");
        Properties conf = new Properties();
        conf.load(in);

        String driverName = conf.getProperty("driverClassName");
        String url = conf.getProperty("url");
        String user = conf.getProperty("user");
        String password = conf.getProperty("password");

        Class.forName(driverName);
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
