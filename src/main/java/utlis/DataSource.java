/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utlis;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author houcem
 */
public class DataSource {
    
    private Connection cnx;
    
    private static DataSource instance;
    
    private final String URL = "jdbc:mysql://localhost:3306/esprit";
    private final String USERNAME = "root";
    private final String PASSWORD = "";

    private DataSource() {
        try {
            cnx = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connecting !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static DataSource getInstance() {
        if(instance == null)
            instance = new DataSource();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
}
