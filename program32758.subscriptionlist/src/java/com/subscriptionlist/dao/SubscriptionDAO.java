
package com.subscriptionlist.dao;

import com.prog32758.db.DBConnection;
import com.subscriptionlist.business.Subscription;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class SubscriptionDAO {
    public boolean registerUser(Connection conn, String firstName, String lastName, String email) {
        
        boolean success = false;
        
        PreparedStatement ps = null;
    
         try{
           
            String sql = "INSERT INTO Subscription " + "(firstName, lastName, email, subscribed) " + "Values(?,?,?,?);";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setBoolean(4, true);
            
            int numRecordsUpdated = ps.executeUpdate();
            
            if(numRecordsUpdated > 0) {
          
                success = true;
            }
            
        }
        catch(SQLException e){
            System.err.println("SQLException: " + e.getMessage());
        }
        finally{
            
           DBConnection.closeJDBCObjects(conn,ps);
            
        }
        return success;
    }
    
    
    public ArrayList<Subscription> getAllSubscriptions(Connection conn) {
        //Declare a variable to hold the list of Subscriptions
        ArrayList<Subscription> subscriptions= new ArrayList();

        //Declare JDBC obj
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql="SELECT * from mailinglist.subscription;";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();


            //Loop through the ResultSet and:
            //1) Get the values for the current record
            //2) Create a Subscription JavaBean
            //3) Set the properties of the Subscription bean to the values for current record
            //4) Add the subscription bean to the ArrayList
            while(rs.next()) {
                String email = rs.getString("email");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                boolean subscribed = rs.getBoolean("subscribed");
                LocalDateTime lastUpdated = rs.getTimestamp("LastUpdated").toLocalDateTime();
                Subscription s = new Subscription();
                s.setEmail(email);
                s.setFirstName(firstName);
                s.setLastName(lastName);
                s.setSubscribed(subscribed);
                s.setLastUpdated(lastUpdated);

                //Add the Subscription bean to the array list
                subscriptions.add(s);

            }
        }
            catch(SQLException e) {
                System.err.println("SQLException: " + e.getMessage());
            }
            finally {
                DBConnection.closeJDBCObjects(conn, ps, rs);
            }
            return subscriptions;
    }
        
        
    //Method to close all jdbc the objects
    public boolean closeAllObjects(Connection conn, PreparedStatement ps, ResultSet rs){
        boolean success = true;
        DBConnection.closeJDBCObjects(conn, ps, rs);
        return success;
    }
    
    //Another method to close the connection objects and such
    public boolean closeConnectionObject(Connection conn){
        boolean success = true;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String sql = "";
            ps = conn.prepareStatement(sql);
        }
        catch(SQLException e){
            DBConnection.closeJDBCObjects(conn, ps);
        }
        finally{
            DBConnection.closeJDBCObjects(conn, ps);
            closeAllObjects(conn, ps, rs);
        }
        return success;
    }
}
