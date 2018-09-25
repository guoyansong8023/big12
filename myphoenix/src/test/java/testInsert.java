import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class testInsert {

    @Test
    public void testInsert() throws Exception{

        Class.forName("org.apache.phoenix.jdbc.PhoenixDriver");

        String url = "jdbc:phoenix:s102,s103,s104";

        Connection conn = DriverManager.getConnection(url);

        PreparedStatement ppst = conn.prepareStatement("upsert into customers values ( ?,?,?)");

        for(int i = 1; i< 100; i++){

            ppst.setInt(1,i);
            ppst.setString(2,"tom"+i);
            ppst.setInt(3,i%30);
            ppst.execute();
        }
        conn.commit();
        ppst.close();
        conn.close();

    }
}
