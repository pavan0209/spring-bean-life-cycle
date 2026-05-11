package org.pavan;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.sql.*;

public class StudentDAO {

    private String driver;
    private String url;
    private String userName;
    private String password;
    Connection con;

    public void setDriver(String driver) {
        System.out.println("setting driver");
        this.driver = driver;
    }

    public void setUrl(String url) {
        System.out.println("setting url");
        this.url = url;
    }

    public void setUserName(String userName) {
        System.out.println("setting userName");
        this.userName = userName;
    }

    public void setPassword(String password) {
        System.out.println("setting password");
        this.password = password;
    }

    @PostConstruct
    public void init() throws SQLException, ClassNotFoundException {
        System.out.println("In init method");
        createStudentDBConnection();
    }

    public void createStudentDBConnection() throws ClassNotFoundException, SQLException {

        System.out.println("creating connection...");
        // load driver
        Class.forName(driver);

        // get connection
        con = DriverManager.getConnection(url, userName, password);
    }

    public void selectAllRows() throws ClassNotFoundException, SQLException {

        System.out.println("Retrieving all students data...");
        // execute query
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM studentInfo");

        while (rs.next()) {
            int id = rs.getInt(1);
            String name = rs.getString(2);
            double fee = rs.getDouble(3);
            String branch = rs.getString(4);

            System.out.println(id + " " + name + " " + fee + " " + branch);
        }
    }

    public void deleteStudentRecord(int id) throws ClassNotFoundException, SQLException {

        // execute query
        Statement stmt = con.createStatement();

        stmt.executeUpdate("DELETE FROM studentInfo WHERE id=" + id);
        System.out.println("Record deleted with id: " + id);
    }

    @PreDestroy
    public void closeConnection() throws SQLException {
        System.out.println("Closing connection");
        // closing connection
        con.close();
    }
}
