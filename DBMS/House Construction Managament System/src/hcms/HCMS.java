/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hcms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Logins.SignupPage;
import users.front1;
import Logins.usersignup;

/**
 *
 * @author VITHESH
 */
public class HCMS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        SignupPage sp=new SignupPage();
       /*Connection con;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?zeroDateTimeBehavior=CONVERT_TO_NULL","root","vithesh@69");
            System.out.println("done");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(HCMS.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(HCMS.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
}
