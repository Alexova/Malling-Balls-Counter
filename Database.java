/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;


import com.mysql.fabric.jdbc.FabricMySQLDriver;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexova
 */
public class Database {
    private Connection connection;
    private Driver driver;
    private Statement statement;
    
    
    private static final String URL = "jdbc:mysql://localhost:3306/ball_counter";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    
    public Database(){
        try {
            driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, USER,PASSWORD);
            
            statement = connection.createStatement();
        
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Connection getConnection(){return connection;}
    public void insert(int count){
        long time = System.currentTimeMillis();
        String query = "INSERT INTO data(time, count) VALUES('" + time + "', '" + count + "');";
        System.out.print(query);
        try {
            statement.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
