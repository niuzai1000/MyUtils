package cn.wqy.DatabaseUtils;

import cn.wqy.UtilsAnnotation.UtilsAnnotation;

import java.sql.*;
import java.util.ResourceBundle;

@UtilsAnnotation("Database Utils")
public class MyDatabaseUtils {
    public static ResourceBundle resourceBundle = ResourceBundle.getBundle("DatabaseUtils.MySQL_database");

    private MyDatabaseUtils() {}

    static{
        try {
            Class.forName(resourceBundle.getString("database"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(String database) throws SQLException {
        return DriverManager.getConnection(resourceBundle.getString("url" ) + database, resourceBundle.getString("user") , resourceBundle.getString("password"));
    }
    public static void close(Connection c , Statement s , ResultSet rs) throws SQLException{
        if (rs != null) {
            rs.close();
        }
        if (s != null) {
            s.close();
        }
        if (c != null) {
            c.close();
        }
    }
    public static void close(Connection c , Statement s) throws SQLException{
        if (s != null) {
            s.close();
        }
        if (c != null) {
            c.close();
        }
    }

    public static void close(Connection c) throws SQLException{
        if (c != null) {
            c.close();
        }
    }

    public static void close(Statement s , ResultSet rs) throws SQLException{
        if (rs != null) {
            rs.close();
        }
        if (s != null) {
            s.close();
        }
    }

    public static void close(Statement s) throws SQLException{
        if (s != null) {
            s.close();
        }
    }

    public static void close(ResultSet rs) throws SQLException{
        if (rs != null) {
            rs.close();
        }
    }
}
