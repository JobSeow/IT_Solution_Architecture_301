package com.itsa.gourds.masks.daos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBController {
    private static final Logger logger = LoggerFactory.getLogger(DBController.class);

    private static final DBController CONTROLLER = new DBController();

//    public static void main(String args[]) {
////        MaskDAO dao = new MaskDAO();
//        DBController controller = new DBController();
//        System.out.println(controller.connectionString);
//    }

    Properties connectionProps = new Properties();
    Connection c = null;

    private DBController() { }

    public static DBController getController() {
        return CONTROLLER;
    }

    private String getConnectionString() {
        return String.format("jdbc:postgresql://itsa.cjonjzszwl3j.ap-southeast-1.rds.amazonaws.com:5432/postgres?user=Wayneloh&password=Wayneloh");
//        return String.format("jdbc:postgresql://localhost:5432/itsa");
    }

    public Connection getConnection() {
        try {
            if (c == null || c.isClosed()) {
                createConnection();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return c;
    }

    private void createConnection() {
        try {
            c = DriverManager.getConnection(getConnectionString(), connectionProps);
        } catch (Exception e) {
            logger.debug("Error!" + e.getMessage());
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("Opened database successfully");
    }
//
//    public String getConnectionString() {
//        return String.format("jdbc:postgresql://itsa.cjonjzszwl3j.ap-southeast-1.rds.amazonaws.com:5432/postgres?user=Wayneloh&password=Wayneloh");
//    }
//
//    public Connection getConnection() {
//        Connection c = null;
//
//        try {
//            c = DriverManager.getConnection(getConnectionString());
//        } catch (Exception e) {
//            logger.debug("Error!" + e.getMessage());
//            e.printStackTrace();
//            System.err.println(e.getClass().getName() + ": " + e.getMessage());
//            System.exit(0);
//        }
//
//        System.out.println("Opened database successfully");
//        return c;
//    }
}
