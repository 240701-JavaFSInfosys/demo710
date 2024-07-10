package com.revature.Util;

import org.h2.tools.RunScript;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A Singleton is a "design pattern" in Java (a common, boilerplate solution
 * within Java)
 * The Singleton guarantees that there exists only a single copy
 * of some object within our app.
 * Our entire application only needs a single database connection
 * for the entire app.
 * So, this class is intended to keep track of, and instantiate,
 * that single connection.
 *
 * DDL data definition
 * DQL data query
 * DML data manipulation
 * DCL data control
 * TCL transcation control
 */
public class ConnectionSingleton {

    private static Connection conn;
    private static final String url = "jdbc:h2:./h2/db";
    private static final String user = "sa";
    private static final String password = "sa";

    /**
     * By convention, a singleton has a private constructor to prevent developers
     * from attempting to instantiate the class with the default constructor.
     */
    private ConnectionSingleton(){

    }
    /**
     * A singleton has a method that will be used to get its object, and instantiate
     * it the very first time
     */
    public static Connection getConnection(){

        if(conn == null){
            try {
                conn = DriverManager.getConnection(url, user, password);
                FileReader sqlReader = new FileReader("src/main/resources/data.sql");
                RunScript.execute(conn, sqlReader);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return conn;
    }
}
