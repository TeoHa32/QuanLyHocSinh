/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
/**

 * @author L380
 */
public class Connection {
    public static Connection getConnection(){
        Connection con = null;
        
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String url ="jdbc:mySQL://localhost:3306/qlhs";
            String username = "root";
            String password ="";
            con = (Connection) DriverManager.getConnection(url,username,password);
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
}
