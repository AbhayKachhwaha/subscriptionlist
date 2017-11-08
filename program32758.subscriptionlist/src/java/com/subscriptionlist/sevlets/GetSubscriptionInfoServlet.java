package com.subscriptionlist.sevlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetSubscriptionInfoServlet extends HttpServlet {
       
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        //Get all parameters from the request
        String email = request.getParameter("email");
        Connection conn= null;
        PreparedStatement ps= null;
        ResultSet rs= null;
        //Use JDBC to get the record the subscription with the matching email add
        try{
        //Step 1: Load the JDBC Driver
        String driver = "com.mysql.jdbc.Driver";
        Class.forName(driver);
        
        //Step 2: Establish connection
            String url="jdbc:mysql://localhost/mailinglist";
            String user="root";
            String password="sheridan";
            
            conn=DriverManager.getConnection(url,user,password);
            
        //Step 3: Execute SQL statement
            String sql="Select * from Subscription Where Email =?;";
            ps = conn.prepareStatement(sql);
            //Set the values of all placeholders (?)
            ps.setString(1, email);
            //Execute the query 
            rs= ps.executeQuery();
            
            //Step 4: Process the results
            //Normally you would use a while loop to traverse the ResultSet.Since we are guaranteed to have at most 1
            //record in the ResultSet, we can use an if statement instead
            if(rs.next()) {
                //Extract the fields for the current record
                //Generate the HTML response to send back to the client
                String emailAddress=rs.getString("email");
                String first = rs.getString("FirstName");
                String last = rs.getString("LastName");
                boolean subscribed = rs.getBoolean("Subscribed");
                LocalDateTime lastUpdated = rs.getTimestamp("LastUpdated").toLocalDateTime();
                
                response.setContentType("text/html");
                
                PrintWriter out = response.getWriter();
                try {
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<body>");
                    out.println("<h1>Subscription Information for ");
                    out.println(emailAddress + "</h1>");
                    out.println("<table style='margin-left:auto;margin-right:auto'>");
                    out.println("   <tr>");
                    out.println("       <td>First Name:</td>");
                    out.println("       <td>" +first + "</td>");
                    out.println("   </tr>");
                    out.println("   <tr>");
                    out.println("       <td>Last Name:</td>");
                    out.println("       <td>"+ last + "</td>");
                    out.println("   </tr>");
                    out.println("   <tr>");
                    out.println("       <td>Subscribed?</td>");
                    out.println("       <td>"+ subscribed + "</td>");
                    out.println("   </tr>");
                    out.println("   <tr>");
                    out.println("       <td>Last Updated:</td>");
                    out.println("       <td>" + lastUpdated.toLocalDate().toString());
                    out.println("       at"+lastUpdated.toLocalTime().toString());
                    out.println("       </td>");
                    out.println("   </tr>");
                    out.println("</table");
                    out.println("</body>");
                    out.println("</html>");
                }
                finally {
                    out.close();
                }
            }else {
                response.setContentType("text/html");
                PrintWriter out = response.getWriter();
                out.println("Could not find user");
            }
        }
        catch(ClassNotFoundException e) {
            System.err.println("Could not load driver");
        }
        catch(SQLException e) {
            System.err.println("SQLException " + e.getMessage());
        }
        finally {
            try{
                if(rs != null)  {
                    rs.close();
                }
                if(ps != null)  {
                    ps.close();
                }
                if(conn != null) {
                    conn.close();
                }
            }
            catch(SQLException e) {
                
            }
        }
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
