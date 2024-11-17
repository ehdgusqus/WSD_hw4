package org.example.jsp_crud_webservice.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class JDBCUtil {
    private static Connection conn = null;
    public static Connection getConnection() {
        try {
            forName("org.mariadb.jdbc.Driver");
            if(conn == null) {
                try {
                    conn = DriverManager.getConnection("jdbc:mariadb://walab.handong.edu:3306/OSS24_22200356?user=OSS24_22200356&password=iiSh0ahh");
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return conn;
    }
    public static void close() {
        try{
            conn.close();
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }


    /*
    public static void main(String a[]){
        Connection conn = org.example.jsp_crud_webservice.util.JDBCUtil.getConnection();
        if(conn != null) {
            System.out.println("DB연결 완료");
        }
    }
    */
}
