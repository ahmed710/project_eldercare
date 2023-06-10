/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author iheb
 */
public class DataSource {
    
    private Connection cnx;
    private static DataSource instance;
    private final String URL = "jdbc:mysql://localhost:3306/eldercare_db";
    private final String USERNAME = "root";
    private final String PASSWORD = "";
    
    private DataSource(){

        try{
            cnx=DriverManager.getConnection(URL,USERNAME,PASSWORD);
            System.out.println("Connected");
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
        public static DataSource getinstance(){
            if(instance == null)
                instance = new DataSource();
            return instance;
        }
        
        public Connection getCnx(){
            return cnx;
        }
}

