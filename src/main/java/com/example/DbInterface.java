package com.example;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

class DbInterface {
    private String dbURL = "../../../updates.db";

    public void insert(String dbURL, int cases, int losses, int recovered, String date) {
        Connection conn = null;
        Statement stmt = null;

        try {
            // db parameters
            String url = "jdbc:sqlite:updates.db";
            // create a connection to the database        );
            conn = DriverManager.getConnection(url);

            // *****************************************************
            conn.setAutoCommit(false);

            stmt = conn.createStatement();
            String sql = "INSERT INTO updates (CASES,LOSSES,RECOVERED,DATE) " +
                         "VALUES (" + cases + "," + losses + "," + recovered + ",'" + date + "');";
            stmt.executeUpdate(sql);

            stmt.close();
            conn.commit();
            conn.close();
            // *****************************************************

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public Covid19Class retrieveLatest() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs, rsMaxId;
        String sql, sqlMaxId, MaxId = null;

        try {
            // db parameters
            String url = "jdbc:sqlite:updates.db";
            // create a connection to the database        );
            conn = DriverManager.getConnection(url);

            // *****************************************************
            conn.setAutoCommit(false);

            stmt = conn.createStatement();

            sqlMaxId = "SELECT MAX(id) FROM updates";
            rsMaxId = stmt.executeQuery(sqlMaxId);
            while(rsMaxId.next()) {
                MaxId = rsMaxId.getString(1);
            }
            sql = "SELECT * FROM updates WHERE id = " + MaxId + ";";
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                Covid19Class retrievalItem = new Covid19Class(rs.getString(2),  // cases
                                                            rs.getString(3),  // losses
                                                            rs.getString(4),  // recovered
                                                            rs.getString(5)); // date
                return retrievalItem;
            }

            stmt.close();
            conn.commit();
            conn.close();
            // *****************************************************

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return null;
    }

    public void setURL (String dbURL){
        this.dbURL = dbURL;
    }

    private String getURL () {
        return dbURL;
    }
}