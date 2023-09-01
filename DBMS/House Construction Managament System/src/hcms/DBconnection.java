/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hcms;


import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.*;
import javax.naming.spi.DirStateFactory.Result;
import java.sql.*;

/**
 *
 * @author VITHESH
 */
public class DBconnection{
    
    public static Connection con=null;
    public static Connection getconnnection()
    {
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL","root","vithesh@69");
            System.out.println("Connection established");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBconnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return con;
    }
}
