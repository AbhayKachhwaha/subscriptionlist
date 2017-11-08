
package com.subscriptionlist.sevlets;

import com.prog32758.db.DBConnection;
import com.subscriptionlist.dao.SubscriptionDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class SubscribeToListServlet extends HttpServlet {
   
    
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
        //getting httprequest parameters from index.html
        String firstName = request.getParameter("first");
        String lastName = request.getParameter("last");
        String email = request.getParameter("email");
        
        //getting the context-initialization param i.e."contactPhone" from web.xml
        //using the ServletContext obj
        ServletContext sc = this.getServletContext();//Returns a reference to the ServletContext in which this servlet is running.
        String phone = sc.getInitParameter("contactPhone");
        
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Contact Phone: " + phone);
        
        //Getting dbConnection obj from servletContext which was stored in ServletContext obj in DbConnectionListener.java
        //Have to cast it to DBConnection V at this arrow because it wass stored in as ServletContext obj
        DBConnection dbConnection =  (DBConnection) sc.getAttribute("dbConnection");
        Connection conn = dbConnection.getConnection();
        
        if(!firstName.equals("") && !lastName.equals("") && !email.equals("")){
            //Use SubscriptionDAO to add user to the database
            SubscriptionDAO subcriptionDAO = new SubscriptionDAO();
            boolean result = subcriptionDAO.registerUser(conn, firstName, lastName, email);
        
            String message;
            if(result)
                message = "Welcome "+ firstName + ". The email address "+ email +" has been added to our mailing list! Please contact us at "+ phone + " for any support issues.";
            else
                message = "Could not register user. Please contact" + phone + "for support";
        
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Email Registration</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>"+message+"</h3>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
        else{
            SubscriptionDAO subcriptionDAO = new SubscriptionDAO();
            subcriptionDAO.closeConnectionObject(conn);
            
            response.setContentType("text/html");

            PrintWriter out = response.getWriter();
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Email Registration</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h3>Enter not null values in First Name, Last Name and e-mail.</h3>");
            out.println("</body>");
            out.println("</html>");
            out.close();
        }
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
