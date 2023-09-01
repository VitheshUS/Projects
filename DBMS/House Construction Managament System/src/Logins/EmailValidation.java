/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logins;

import java.util.regex.Pattern;

/**
 *
 * @author VITHESH
 */
public class EmailValidation {
    public static boolean patternMatches(String emailAddress, String regexPattern) {
    return Pattern.compile(regexPattern)
      .matcher(emailAddress)
      .matches();  
}
    public static boolean patterncontact(String contact,String regexpattern)
    {
        return Pattern.compile(regexpattern)
      .matcher(contact)
      .matches(); 
    }
}
