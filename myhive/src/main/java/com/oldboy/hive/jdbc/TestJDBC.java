package com.oldboy.hive.jdbc;

import org.junit.Test;

import java.sql.*;

public class TestJDBC {
    public static void main(String[] args) throws Exception{
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        String url = "jdbc:hive2://192.168.139.101:10000/big12";
        Connection conn = DriverManager.getConnection(url);
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("select * from duowan limit 10");
        while (rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String pass = rs.getString(3);
            String mail = rs.getString(4);
            String nickname = rs.getString(5);
            System.out.println(id + "/" + name + "/" + pass + "/" + mail + "/" + nickname);
        }
        rs.close();
        conn.close();
    }

    @Test
    public void testInsert() throws Exception{
        Class.forName("org.apache.hive.jdbc.HiveDriver");
        String url = "jdbc:hive2://192.168.139.101:10000/big12";
        Connection conn = DriverManager.getConnection(url);
        Statement st = conn.createStatement();
        boolean b = st.execute("insert into users2 values(2,'tomas',30)");
        if (b){
            System.out.println("ok");
        }
    }
}
