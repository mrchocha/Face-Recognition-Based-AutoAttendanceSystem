/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.mysql.jdbc.Connection;
//import java.beans.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
//import static java.sql.ResultSet.TYPE_SCROLL_SENSITIVE;
import java.sql.SQLException;
//import javax.naming.spi.DirStateFactory.Result;
//import java.beans.*;
import java.sql.*;
/**
 *
 * @author rahul
 */
public class ConnectDatabase {

    public Statement stm;

    public ResultSet rs;
    public Connection con;
    private final String driver = "org.mysql.Driver";

    private final String root = "jdbc:mysql://localhost:3306/database_1";
    private final String usr = "root";
    private final String pass = "";

    public void connect() {
        try {
            System.setProperty("jdbc.driver", driver);
            con = (Connection) DriverManager.getConnection(root, usr, pass);
            System.out.println("OK!");
        } catch (SQLException e) {
            System.out.println("ERROR : " + e);
        }
    }
    
    public void disconnect(){
        try{
            con.close();
        }catch(SQLException e){
            System.out.println("ERROR:" + e);
        }
    }
    public void executeSQL(String SQL){
        try{
            stm =  con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = stm.executeQuery(SQL);
        }catch(SQLException e){
            System.out.println("eRROR:" + e);
        }
    }
    public void execute(String SQL){
        try{
            stm =  con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            int rs = stm.executeUpdate(SQL);
        }catch(SQLException e){
            System.out.println("ERrOR:" + e);
        }
    }
    public static void main(String[] args) {
        ConnectDatabase connect = new ConnectDatabase();
        connect.connect();
    }
    
}