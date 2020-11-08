package sourcemaking.creational.objectpool.example;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // do something...

        // create the Connection Pool
        JDBCConnectionPool pool = new JDBCConnectionPool("org.hsqldb.jdbcDriver",
                "jdbc:hsqldb://localhost/mydb", "sa", "***");

        // get conn
        Connection conn = pool.checkOut();

        // use the conn

        // return the conn
        pool.checkIn(conn);
    }
}
